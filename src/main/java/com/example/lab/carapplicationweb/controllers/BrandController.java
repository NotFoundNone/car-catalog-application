package com.example.lab.carapplicationweb.controllers;

import com.example.lab.carapplicationweb.models.Brand;
import com.example.lab.carapplicationweb.services.BrandService;
import com.example.lab.carapplicationweb.services.dtos.AddBrandDto;
import com.example.lab.carapplicationweb.services.dtos.UpdateBrandDto;
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
    public UpdateBrandDto getUpdateBrandDto() {
        return new UpdateBrandDto();
    }

    @GetMapping("/all")
    String getAll(Principal principal, Model model){
        LOG.log(Level.INFO, "Show all brands for " + principal.getName());
        List<Brand> brands = brandService.getAll();
        model.addAttribute("brandModels", brands);
        return "brands-all";
    }

//    @GetMapping("/{uuid}")
//    String getBrand (@PathVariable String uuid, Model model){
//        Optional<Brand> brand = brandService.findByUuid(uuid);
//        model.addAttribute("brand", brand.get());
//        return "brandPage";
//    }

    @GetMapping("/add")
    String addBrand()
    {
        return "brand-add";
    }

    @PostMapping("/add")
    String addBrand(@Valid AddBrandDto brand, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandModel", brand);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel",
                    bindingResult);
            return "redirect:/brands/add";
        }
        brandService.add(brand);
        return "redirect:/brands/all";
    }

    @GetMapping("/brand-details/{brand-name}")
    public String brandDetails(@PathVariable("brand-name") String brandName, org.springframework.ui.Model model) {
        model.addAttribute("brandDetails", brandService.brandDetails(brandName));

        return "brand-details";
    }

    @GetMapping("/brand-delete/{brand-name}")
    String deleteModel(@PathVariable("brand-name") String brandName)
    {
        brandService.deleteByName(brandName);
        return "redirect:/brands/all";
    }

    @GetMapping("/update/{uuid}")
    String brandUpdate(@PathVariable("uuid") String uuid, Model model) {
        model.addAttribute("updateBrandDto", brandService.findByUuid(uuid).orElse(null));
        return "brand-update";
    }

    @PostMapping("/update/{uuid}")
    String updateBrand(@ModelAttribute("updateBrand") @Valid UpdateBrandDto updateBrand,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       @PathVariable("uuid") String uuid) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateBrandDto", updateBrand);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateBrand", bindingResult);
            return "redirect:/brands/update/" + uuid;
        }

        brandService.update(updateBrand);
        return "redirect:/brands/all";
    }
}
