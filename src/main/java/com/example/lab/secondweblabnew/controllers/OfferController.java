package com.example.lab.secondweblabnew.controllers;

import com.example.lab.secondweblabnew.models.Offer;
import com.example.lab.secondweblabnew.services.ModelService;
import com.example.lab.secondweblabnew.services.OfferService;
import com.example.lab.secondweblabnew.services.UserService;
import com.example.lab.secondweblabnew.services.dtos.AddModelDto;
import com.example.lab.secondweblabnew.services.dtos.AddOfferDto;
import com.example.lab.secondweblabnew.services.dtos.OfferDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private OfferService offerService;
    private ModelService modelService;
    private UserService userService;

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
    String getAll(Model model){
        List<Offer> offers = offerService.getAll();
        model.addAttribute("offerModels", offers);
        return "offers-all";
    }

    @GetMapping("/{uuid}")
    String getOffer (@PathVariable String uuid, Model model){
        Optional<Offer> offer = offerService.findByUuid(uuid);
        model.addAttribute("offer", offer.get());
        return "offerPage";
    }

    @GetMapping("/add")
    String addOffer(Model model)
    {
        model.addAttribute("models", modelService.getAll());
        model.addAttribute("sellers", userService.getAll());
        return "offer-add";
    }

    @PostMapping("/add")
    String addOffer(@Valid AddOfferDto offer, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offer);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                    bindingResult);
            return "redirect:/offers/add";
        }
        offerService.add(offer);
        return "redirect:/";
    }

    @PutMapping("/edit/{uuid}")
    String editOffer(@PathVariable String uuid, @ModelAttribute OfferDTO offer){
        offerService.update(uuid, offer);
        return "redirect:/offersPage";
    }

    @DeleteMapping("/{uuid}")
    String deleteOffer(@PathVariable String uuid)
    {
        offerService.deleteByUuid(uuid);
        return "redirect:/offersPage";
    }
}
