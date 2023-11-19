package com.example.lab.secondweblabnew.controllers;

import com.example.lab.secondweblabnew.models.Brand;
import com.example.lab.secondweblabnew.services.BrandService;
import com.example.lab.secondweblabnew.services.dtos.BrandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private BrandService brandService;

    @Autowired
    public void setBrandService(BrandService brandService) { this.brandService = brandService; }

    @GetMapping
    String getAll(Model model){
        List<Brand> brands = brandService.getAll();
        model.addAttribute("brands", brands);
        return "brandsPage";
    }

    @GetMapping("/{uuid}")
    String getBrand (@PathVariable String uuid, Model model){
        Optional<Brand> brand = brandService.findByUuid(uuid);
        model.addAttribute("brand", brand.get());
        return "brandPage";
    }

    @PostMapping("/add")
    String addBrand(@ModelAttribute BrandDTO brand){
        brandService.add(brand);
        return "redirect:/brandsPage";
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
