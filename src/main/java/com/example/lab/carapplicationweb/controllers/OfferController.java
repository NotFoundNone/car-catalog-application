package com.example.lab.carapplicationweb.controllers;

import com.example.lab.carapplicationweb.models.Offer;
import com.example.lab.carapplicationweb.services.ModelService;
import com.example.lab.carapplicationweb.services.OfferService;
import com.example.lab.carapplicationweb.services.UserService;
import com.example.lab.carapplicationweb.services.dtos.AddOfferDto;
import com.example.lab.carapplicationweb.services.dtos.OfferDTO;
import com.example.lab.carapplicationweb.services.dtos.ShowOfferInfoDto;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public void setOfferService(OfferService offerService) { this.offerService = offerService; }

    @Autowired
    public void setModelService(ModelService modelService) { this.modelService = modelService; }

    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    @ModelAttribute("offerModel")
    public AddOfferDto initOffer() {
        return new AddOfferDto();
    }

    @GetMapping("/all")
    String getAll(Model model, Principal principal){
        LOG.log(Level.INFO, "Show all offers for " + principal.getName());
        List<ShowOfferInfoDto> offers = offerService.getAll();
        model.addAttribute("offerModels", offers);
        return "offers-all";
    }

    @GetMapping("/{uuid}")
    String getOffer (@PathVariable String uuid, Model model, Principal principal){
        LOG.log(Level.INFO, "Get offer for " + principal.getName());
        Optional<Offer> offer = offerService.findByUuid(uuid);
        model.addAttribute("offer", offer.get());
        return "offerPage";
    }

    @GetMapping("/add")
    String addOffer(Model model, Principal principal)
    {
        LOG.log(Level.INFO, "Open add offer page for " + principal.getName());
        model.addAttribute("models", modelService.getAll());
        model.addAttribute("sellers", userService.getAll());
//        model.addAttribute("user", principal.getName());
//
//        if (userService.isUserAdmin(principal.getName())) {
//            model.addAttribute("sellers", userService.getAll());
//        }
        return "offer-add";
    }

    @PostMapping("/add")
    String addOffer(@Valid AddOfferDto offer, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offer);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                    bindingResult);
            return "redirect:/offers/add";
        }
        LOG.log(Level.INFO, "Add offer for " + principal.getName());
        offerService.add(offer);
        return "redirect:/";
    }

    @GetMapping("/offer-details/{offer-uuid}")
    public String offerDetails(@PathVariable("offer-uuid") String offerUuid, org.springframework.ui.Model model, Principal principal) {
        LOG.log(Level.INFO, "Show offer details for " + principal.getName());
        model.addAttribute("offerDetails", offerService.offerDetails(offerUuid));
        return "offer-details";
    }

    @GetMapping("/top3mostexpensive")
    String getTop3MostExpensive(Model model, Principal principal) {
        LOG.log(Level.INFO, "Show top 3 most expensive for " + principal.getName());
        List<Offer> top3MostExpensiveOffers = offerService.getTop3MostExpensiveOffers();
        model.addAttribute("top3Offers", top3MostExpensiveOffers);
        return "top3offers";
    }

    @GetMapping("/user-offers")
    String getUserOffers(Model model, Principal principal) {
        LOG.log(Level.INFO, "Show user offers for " + principal.getName());
        List<ShowOfferInfoDto> userOffers = offerService.getAllBySeller(principal.getName());
        model.addAttribute("offerModels", userOffers);
        return "user-offers";
    }

    @PutMapping("/edit/{uuid}")
    String editOffer(@PathVariable String uuid, @ModelAttribute OfferDTO offer, Principal principal){
        LOG.log(Level.INFO, "Edit offer for " + principal.getName());
        offerService.update(uuid, offer);
        return "redirect:/offersPage";
    }

    @GetMapping("/offer-delete/{full-offer-name}")
    String deleteOffer(@PathVariable("full-offer-name") String fullName, Principal principal)
    {
        LOG.log(Level.INFO, "Delete offer for " + principal.getName());
        offerService.deleteByFullName(fullName);
        return "redirect:/offers/all";
    }
}
