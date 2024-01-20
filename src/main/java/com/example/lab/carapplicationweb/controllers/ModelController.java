package com.example.lab.carapplicationweb.controllers;

import com.example.lab.carapplicationweb.services.BrandService;
import com.example.lab.carapplicationweb.services.ModelService;
import com.example.lab.carapplicationweb.services.dtos.*;
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

    @ModelAttribute("updateModel")
    public EditModelDto getEditModelDto() {
        return new EditModelDto();
    }

    @GetMapping("/all")
    String getAll(Model model, Principal principal){
        LOG.log(Level.INFO, "Show all models for " + principal.getName());

        List<ShowModelInfoDto> models = modelService.getAll();
        model.addAttribute("modelModels", models);

        return "models-all";
    }

    @GetMapping("/model-details/{model-name}")
    public String modelDetails(@PathVariable("model-name") String modelName, org.springframework.ui.Model model, Principal principal) {
        LOG.log(Level.INFO, "Show model details for " + principal.getName());

        model.addAttribute("modelDetails", modelService.modelDetails(modelName));

        return "model-details";
    }

    //Пока что не знаю нужно или нет
//    @GetMapping("/{uuid}")
//    String getModel (@PathVariable String uuid, Model model, Principal principal){
//        LOG.log(Level.INFO, "Show model for " + principal.getName());
//
//        Optional<com.example.lab.carapplicationweb.models.Model> newModel = modelService.findByUuid(uuid);
//        model.addAttribute("model", newModel.get());
//
//        return "modelPage";
//    }

    @GetMapping("/add")
    String addModel(Model model, Principal principal)
    {
        LOG.log(Level.INFO, "Open add model page for " + principal.getName());

        model.addAttribute("brands", brandService.getAll());

        return "model-add";
    }

    @PostMapping("/add")
    String addModel(@Valid AddModelDto model, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal){
        LOG.log(Level.INFO, "Add model for " + principal.getName());

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelModel", model);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModel",
                    bindingResult);
            return "redirect:/models/add";
        }

        modelService.add(model);

        return "redirect:/models/all";
    }

    @GetMapping("/edit/{model-name}")
    public String showEditModel(@PathVariable("model-name") String modelName, Model model) {
        Optional<EditModelDto> editModel = modelService.findByName(modelName);
        model.addAttribute("updateModel", editModel.orElse(new EditModelDto()));
        model.addAttribute("brands", brandService.getAll());

        return "model-edit";
    }

    @PostMapping("/edit/{model-name}")
    public String editModel(@Valid @ModelAttribute("updateModel") EditModelDto editModel,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brands", brandService.getAll());
            return "model-edit";
        }

        modelService.update(editModel);

        return "redirect:/models/all";
    }

    //DeleteByName может работать некорректно так как может быть две машины с разными брендами но одинаковым названием машины
    @GetMapping("/model-delete/{full-model-name}")
    String deleteModel(@PathVariable("full-model-name") String fullName, Principal principal)
    {
        LOG.log(Level.INFO, "Delete model for " + principal.getName());

        modelService.deleteByName(fullName);

        return "redirect:/models/all";
    }
}
