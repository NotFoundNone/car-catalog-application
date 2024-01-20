package com.example.lab.carapplicationweb.controllers;

import com.example.lab.carapplicationweb.models.Brand;
import com.example.lab.carapplicationweb.services.BrandService;
import com.example.lab.carapplicationweb.services.dtos.AddBrandDto;
import com.example.lab.carapplicationweb.services.dtos.ShowBrandInfoDto;
import com.example.lab.carapplicationweb.services.dtos.EditBrandDto;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private BrandService brandService;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public void setBrandService(BrandService brandService) { this.brandService = brandService; }

    @ModelAttribute("brandModel")
    public AddBrandDto initBrand() {
        return new AddBrandDto();
    }

    @ModelAttribute("updateBrand")
    public EditBrandDto getUpdateBrandDto() {
        return new EditBrandDto();
    }

    @GetMapping("/all")
    String getAll(Principal principal, Model model){
        LOG.log(Level.INFO, "Show all brands for " + principal.getName());

        List<ShowBrandInfoDto> brands = brandService.getAll();
        model.addAttribute("brandModels", brands);

        return "brands-all";
    }

    @GetMapping("/brand-details/{brand-name}")
    public String brandDetails(@PathVariable("brand-name") String brandName, org.springframework.ui.Model model, Principal principal) {
        LOG.log(Level.INFO, "Show brand details for " + principal.getName());

        model.addAttribute("brandDetails", brandService.brandDetails(brandName));

        return "brand-details";
    }

    @GetMapping("/add")
    String addBrand(Principal principal)
    {
        LOG.log(Level.INFO, "Open add new brand page for " + principal.getName());

        return "brand-add";
    }

    @PostMapping("/add")
    String addBrand(@Valid AddBrandDto brand, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal){
        LOG.log(Level.INFO, "Add new brand for " + principal.getName());

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandModel", brand);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel",
                    bindingResult);
            return "redirect:/brands/add";
        }

        brandService.add(brand);

        return "redirect:/brands/all";
    }

    @GetMapping("/edit/{brand-name}")
    public String showEditBrand(@PathVariable("brand-name") String brandName, Model model) {
        Optional<EditBrandDto> editBrand = brandService.findByName(brandName);
        model.addAttribute("updateBrand", editBrand.orElse(new EditBrandDto()));

        return "brand-edit";
    }

    @PostMapping("/edit/{brand-name}")
    public String editBrand(@Valid @ModelAttribute("updateBrand") EditBrandDto editBrand,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "brand-edit";
        }

        brandService.update(editBrand);

        return "redirect:/brands/all";
    }

    @GetMapping("/brand-delete/{brand-name}")
    String deleteModel(@PathVariable("brand-name") String brandName, Principal principal)
    {
        LOG.log(Level.INFO, "Delete brand for " + principal.getName());

        brandService.deleteByName(brandName);

        return "redirect:/brands/all";
    }
}
