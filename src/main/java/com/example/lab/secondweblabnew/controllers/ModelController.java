package com.example.lab.secondweblabnew.controllers;

import com.example.lab.secondweblabnew.services.BrandService;
import com.example.lab.secondweblabnew.services.ModelService;
import com.example.lab.secondweblabnew.services.dtos.ModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ModelController {

    private ModelService modelService;

    @Autowired
    public void setBrandService(ModelService modelService) { this.modelService = modelService; }

    @GetMapping
    String getAll(Model model){
        List<com.example.lab.secondweblabnew.models.Model> listModels = modelService.getAll();
        model.addAttribute("models", listModels);
        return "modelsPage";
    }

    @GetMapping("/{uuid}")
    String getModel (@PathVariable String uuid, Model model){
        Optional<com.example.lab.secondweblabnew.models.Model> newModel = modelService.findByUuid(uuid);
        model.addAttribute("model", newModel.get());
        return "modelPage";
    }

    @PostMapping("/add")
    String addModel(@ModelAttribute ModelDTO model){
        modelService.add(model);
        return "redirect:/modelsPage";
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
