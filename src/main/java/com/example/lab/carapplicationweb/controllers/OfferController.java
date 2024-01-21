package com.example.lab.carapplicationweb.controllers;

import com.example.lab.carapplicationweb.models.Offer;
import com.example.lab.carapplicationweb.models.User;
import com.example.lab.carapplicationweb.services.ModelService;
import com.example.lab.carapplicationweb.services.OfferService;
import com.example.lab.carapplicationweb.services.UserService;
import com.example.lab.carapplicationweb.services.dtos.*;
import com.example.lab.carapplicationweb.services.impl.AuthService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private OfferService offerService;
    private ModelService modelService;
    private AuthService authService;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public void setOfferService(OfferService offerService) { this.offerService = offerService; }

    @Autowired
    public void setModelService(ModelService modelService) { this.modelService = modelService; }

    @Autowired
    public void setAuthService(AuthService authService) { this.authService = authService; }

    @ModelAttribute("offerModel")
    public AddOfferDto initOffer() {
        return new AddOfferDto();
    }

    @ModelAttribute("updateOffer")
    public EditOfferDto getEditOfferDto() {
        return new EditOfferDto();
    }

    @GetMapping("/all")
    String getAll(Model model, Principal principal){
        LOG.log(Level.INFO, "Show all offers for " + principal.getName());

        List<ShowOfferInfoDto> offers = offerService.getAll();
        model.addAttribute("offerModels", offers);

        return "offers-all";
    }

    @GetMapping("/user-offers")
    String getUserOffers(Model model, Principal principal) {
        LOG.log(Level.INFO, "Show user offers for " + principal.getName());

        List<ShowOfferInfoDto> userOffers = offerService.getAllBySeller(principal.getName());
        model.addAttribute("offerModels", userOffers);

        return "user-offers";
    }

    @GetMapping("/top3mostexpensive")
    String getTop3MostExpensive(Model model, Principal principal) {
        LOG.log(Level.INFO, "Show top 3 most expensive for " + principal.getName());

        List<Offer> top3MostExpensiveOffers = offerService.getTop3MostExpensiveOffers();
        model.addAttribute("top3Offers", top3MostExpensiveOffers);

        return "top3offers";
    }

    @GetMapping("/offer-details/{offer-uuid}")
    public String offerDetails(@PathVariable("offer-uuid") String offerUuid, org.springframework.ui.Model model, Principal principal) {
        LOG.log(Level.INFO, "Show offer details for " + principal.getName());

        model.addAttribute("offerDetails", offerService.offerDetails(offerUuid));

        return "offer-details";
    }

    //Пока что не знаю нужно или нет
//    @GetMapping("/{uuid}")
//    String getOffer (@PathVariable String uuid, Model model, Principal principal){
//        LOG.log(Level.INFO, "Get offer for " + principal.getName());
//
//        Optional<Offer> offer = offerService.findByUuid(uuid);
//
//        model.addAttribute("offer", offer.get());
//        return "offerPage";
//    }

    @GetMapping("/add")
    String addOffer(Model model, Principal principal)
    {
        LOG.log(Level.INFO, "Open add offer page for " + principal.getName());

        model.addAttribute("models", modelService.getAll());
        if (authService.isUserAdmin(principal.getName())) {
            model.addAttribute("sellers", authService.getAll());
        }
        else
        {
            User currentUser = authService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("sellers", currentUser);
        }

        return "offer-add";
    }

    @PostMapping("/add")
    String addOffer(@Valid AddOfferDto offer, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal){
        LOG.log(Level.INFO, "Add offer for " + principal.getName());

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offer);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                    bindingResult);
            return "redirect:/offers/add";
        }

        offerService.add(offer);

        return "redirect:/offers/all";
    }

    @GetMapping("/edit/{uuid}")
    public String showEditOffer(@PathVariable("uuid") String uuid, Model model, Principal principal) {
        Optional<EditOfferDto> editOffer = offerService.findEditOfferDtoByUuid(uuid);

        model.addAttribute("updateOffer", editOffer.orElse(new EditOfferDto()));
        model.addAttribute("models", modelService.getAll());
        User currentUser = authService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("sellers", currentUser);

        return "offer-edit";
    }

    @PostMapping("/edit/{uuid}")
    public String editOffer(@Valid @ModelAttribute("updateOffer") EditOfferDto editOffer,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("models", modelService.getAll());
            User currentUser = authService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("sellers", currentUser);
            return "offer-edit";
        }

        offerService.update(editOffer);

        return "redirect:/users/profile";
    }

    @GetMapping("/offer-delete/{full-offer-name}")
    String deleteOffer(@PathVariable("full-offer-name") String fullName, Principal principal)
    {
        LOG.log(Level.INFO, "Delete offer for " + principal.getName());

        offerService.deleteByFullName(fullName);

        return "redirect:/offers/all";
    }

}
