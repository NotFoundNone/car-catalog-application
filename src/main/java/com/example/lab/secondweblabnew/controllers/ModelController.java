package com.example.lab.secondweblabnew.controllers;

import com.example.lab.secondweblabnew.services.BrandService;
import com.example.lab.secondweblabnew.services.ModelService;
import com.example.lab.secondweblabnew.services.dtos.AddBrandDto;
import com.example.lab.secondweblabnew.services.dtos.AddModelDto;
import com.example.lab.secondweblabnew.services.dtos.ModelDTO;
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
@RequestMapping("/models")
public class ModelController {

    private ModelService modelService;
    private BrandService brandService;

    @Autowired
    public void setModelService(ModelService modelService) { this.modelService = modelService; }

    @Autowired
    public void setBrandService(BrandService brandService) { this.brandService = brandService; }

    @ModelAttribute("modelModel")
    public AddModelDto initModel() {
        return new AddModelDto();
    }

    @GetMapping("/all")
    String getAll(Model model){
        List<com.example.lab.secondweblabnew.models.Model> models = modelService.getAll();
        model.addAttribute("modelModels", models);
        return "models-all";
    }

    @GetMapping("/{uuid}")
    String getModel (@PathVariable String uuid, Model model){
        Optional<com.example.lab.secondweblabnew.models.Model> newModel = modelService.findByUuid(uuid);
        model.addAttribute("model", newModel.get());
        return "modelPage";
    }

    @GetMapping("/add")
    String addBrand(Model model)
    {
        model.addAttribute("brands", brandService.getAll());
        return "model-add";
    }

    @PostMapping("/add")
    String addModel(@Valid AddModelDto model, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelModel", model);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModel",
                    bindingResult);
            return "redirect:/models/add";
        }
        modelService.add(model);
        return "redirect:/";
    }

    @PutMapping("/edit/{uuid}")
    String editModel(@PathVariable String uuid, @ModelAttribute ModelDTO model){
        modelService.update(uuid, model);
        return "redirect:/modelsPage";
    }

    @DeleteMapping("/{uuid}")
    String deleteModel(@PathVariable String uuid)
    {
        modelService.deleteByUuid(uuid);
        return "redirect:/modelsPage";
    }
}
