package com.example.lab.carapplicationweb.controllers;

import com.example.lab.carapplicationweb.models.Offer;
import com.example.lab.carapplicationweb.services.OfferService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private OfferService offerService;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public void setOfferService(OfferService offerService) { this.offerService = offerService; }

    @GetMapping("/")
    String getTop3MostExpensive(Principal principal, Model model) {
        LOG.log(Level.INFO, "Open home page and show top 3 most expensive car " + principal.getName());
        List<Offer> top3MostExpensiveOffers = offerService.getTop3MostExpensiveOffers();
        model.addAttribute("top3Offers", top3MostExpensiveOffers);
        return "home-index";
    }
}
