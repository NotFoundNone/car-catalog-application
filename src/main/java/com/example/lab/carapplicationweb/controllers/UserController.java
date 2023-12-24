//package com.example.lab.carapplicationweb.controllers;
//
//import com.example.lab.carapplicationweb.models.User;
//import com.example.lab.carapplicationweb.services.UserService;
//import com.example.lab.carapplicationweb.services.dtos.AddUserDto;
//import com.example.lab.carapplicationweb.services.dtos.UserDTO;
//import jakarta.validation.Valid;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/users")
//public class UserController {
//
//    private UserService userService;
//
//    private static final Logger LOG = LogManager.getLogger(Controller.class);
//
//    @Autowired
//    public void setUserService(UserService userService) { this.userService = userService; }
//
//    @ModelAttribute("userModel")
//    public AddUserDto initUser() {
//        return new AddUserDto();
//    }
//
//    @GetMapping("/all")
//    String getAll(Model model){
//        List<User> users = userService.getAll();
//        model.addAttribute("userModels", users);
//        return "users-all";
//    }
//
//    @GetMapping("/{uuid}")
//    String getUser (@PathVariable String uuid, Model model){
//        Optional<User> user = userService.findByUuid(uuid);
//        model.addAttribute("user", user.get());
//        return "userPage";
//    }
//
//    @GetMapping("/add")
//    String addUser()
//    {
//        return "user-add";
//    }
//
//    @PostMapping("/add")
//    String addUser(@Valid AddUserDto user, BindingResult bindingResult, RedirectAttributes redirectAttributes){
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("userModel", user);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
//                    bindingResult);
//            return "redirect:/users/add";
//        }
//        userService.add(user);
//        return "redirect:/users/all";
//    }
//
//    @PutMapping("/edit/{uuid}")
//    String editUser(@PathVariable String uuid, @ModelAttribute UserDTO user){
//        userService.update(uuid, user);
//        return "redirect:/usersPage";
//    }
//
//    @DeleteMapping("/{uuid}")
//    String deleteUser(@PathVariable String uuid)
//    {
//        userService.deleteByUuid(uuid);
//        return "redirect:/usersPage";
//    }
//}
