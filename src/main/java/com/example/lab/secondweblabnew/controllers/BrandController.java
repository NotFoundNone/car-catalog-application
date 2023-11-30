package com.example.lab.secondweblabnew.controllers;

import com.example.lab.secondweblabnew.models.Brand;
import com.example.lab.secondweblabnew.services.BrandService;
import com.example.lab.secondweblabnew.services.dtos.AddBrandDto;
import com.example.lab.secondweblabnew.services.dtos.AddUserDto;
import com.example.lab.secondweblabnew.services.dtos.BrandDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private BrandService brandService;

    @Autowired
    public void setBrandService(BrandService brandService) { this.brandService = brandService; }

    @ModelAttribute("brandModel")
    public AddBrandDto initBrand() {
        return new AddBrandDto();
    }

    @GetMapping("/all")
    String getAll(Model model){
        List<Brand> brands = brandService.getAll();
        model.addAttribute("brandModels", brands);
        return "brands-all";
    }

    @GetMapping("/{uuid}")
    String getBrand (@PathVariable String uuid, Model model){
        Optional<Brand> brand = brandService.findByUuid(uuid);
        model.addAttribute("brand", brand.get());
        return "brandPage";
    }

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

    @PutMapping("/edit/{uuid}")
    String editBrand(@PathVariable String uuid, @ModelAttribute BrandDTO brand){
        brandService.update(uuid, brand);
        return "redirect:/brandsPage";
    }

    @DeleteMapping("/{uuid}")
    String deleteBrand(@PathVariable String uuid)
    {
        brandService.deleteByUuid(uuid);
        return "redirect:/brandsPage";
    }
}
