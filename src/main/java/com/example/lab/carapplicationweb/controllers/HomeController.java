package com.example.lab.carapplicationweb.controllers;

import com.example.lab.carapplicationweb.models.Offer;
import com.example.lab.carapplicationweb.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private OfferService offerService;

    @Autowired
    public void setOfferService(OfferService offerService) { this.offerService = offerService; }

    @GetMapping("/")
    String getTop3MostExpensive(Model model) {
        List<Offer> top3MostExpensiveOffers = offerService.getTop3MostExpensiveOffers();
        model.addAttribute("top3Offers", top3MostExpensiveOffers);
        return "home-index";
    }
}
