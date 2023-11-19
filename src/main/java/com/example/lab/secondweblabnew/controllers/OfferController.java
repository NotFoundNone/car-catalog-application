package com.example.lab.secondweblabnew.controllers;

import com.example.lab.secondweblabnew.models.Offer;
import com.example.lab.secondweblabnew.services.OfferService;
import com.example.lab.secondweblabnew.services.dtos.OfferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private OfferService offerService;

    @Autowired
    public void setOfferService(OfferService offerService) { this.offerService = offerService; }

    @GetMapping
    String getAll(Model model){
        List<Offer> offers = offerService.getAll();
        model.addAttribute("offers", offers);
        return "offersPage";
    }

    @GetMapping("/{uuid}")
    String getOffer (@PathVariable String uuid, Model model){
        Optional<Offer> offer = offerService.findByUuid(uuid);
        model.addAttribute("offer", offer.get());
        return "offerPage";
    }

    @PostMapping("/add")
    String addOffer(@ModelAttribute OfferDTO offer){
        offerService.add(offer);
        return "redirect:/offersPage";
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
