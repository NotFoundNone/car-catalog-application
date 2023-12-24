package com.example.lab.carapplicationweb.controllers;

import com.example.lab.carapplicationweb.services.BrandService;
import com.example.lab.carapplicationweb.services.ModelService;
import com.example.lab.carapplicationweb.services.dtos.AddModelDto;
import com.example.lab.carapplicationweb.services.dtos.ModelDTO;
import com.example.lab.carapplicationweb.services.dtos.ShowModelInfoDto;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/models")
public class ModelController {

    private ModelService modelService;
    private BrandService brandService;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public void setModelService(ModelService modelService) { this.modelService = modelService; }

    @Autowired
    public void setBrandService(BrandService brandService) { this.brandService = brandService; }

    @ModelAttribute("modelModel")
    public AddModelDto initModel() {
        return new AddModelDto();
    }

    @GetMapping("/all")
    String getAll(Model model, Principal principal){
        LOG.log(Level.INFO, "Show all models for " + principal.getName());
        List<ShowModelInfoDto> models = modelService.getAll();
        model.addAttribute("modelModels", models);
        return "models-all";
    }

    @GetMapping("/{uuid}")
    String getModel (@PathVariable String uuid, Model model, Principal principal){
        LOG.log(Level.INFO, "Show brand for " + principal.getName());
        Optional<com.example.lab.carapplicationweb.models.Model> newModel = modelService.findByUuid(uuid);
        model.addAttribute("model", newModel.get());
        return "modelPage";
    }

    @GetMapping("/add")
    String addModel(Model model, Principal principal)
    {
        LOG.log(Level.INFO, "Open add model page for " + principal.getName());
        model.addAttribute("brands", brandService.getAll());
        return "model-add";
    }

    @PostMapping("/add")
    String addModel(@Valid AddModelDto model, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelModel", model);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModel",
                    bindingResult);
            return "redirect:/models/add";
        }
        LOG.log(Level.INFO, "Add model for " + principal.getName());
        modelService.add(model);
        return "redirect:/";
    }

    @GetMapping("/model-details/{model-name}")
    public String modelDetails(@PathVariable("model-name") String modelName, org.springframework.ui.Model model, Principal principal) {
        LOG.log(Level.INFO, "Show model details for " + principal.getName());
        model.addAttribute("modelDetails", modelService.modelDetails(modelName));
        return "model-details";
    }

    @PutMapping("/edit/{uuid}")
    String editModel(@PathVariable String uuid, @ModelAttribute ModelDTO model, Principal principal){
        LOG.log(Level.INFO, "Edit model for " + principal.getName());
        modelService.update(uuid, model);
        return "redirect:/modelsPage";
    }

    @GetMapping("/model-delete/{full-model-name}")
    String deleteModel(@PathVariable("full-model-name") String fullName, Principal principal)
    {
        LOG.log(Level.INFO, "Delete model for " + principal.getName());
        modelService.deleteByName(fullName);
        return "redirect:/models/all";
    }
}
