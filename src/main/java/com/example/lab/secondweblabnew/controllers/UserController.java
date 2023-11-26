package com.example.lab.secondweblabnew.controllers;

import com.example.lab.secondweblabnew.models.User;
import com.example.lab.secondweblabnew.services.UserService;
import com.example.lab.secondweblabnew.services.dtos.AddUserDto;
import com.example.lab.secondweblabnew.services.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    @ModelAttribute("userModel")
    public AddUserDto initUser() {
        return new AddUserDto();
    }

    @GetMapping
    String getAll(Model model){
        List<User> users = userService.getAll();
        model.addAttribute("offers", users);
        return "usersPage";
    }

    @GetMapping("/{uuid}")
    String getUser (@PathVariable String uuid, Model model){
        Optional<User> user = userService.findByUuid(uuid);
        model.addAttribute("user", user.get());
        return "userPage";
    }

    @GetMapping("/add")
    String addBrand()
    {
        return "user-add";
    }

    @PostMapping("/add")
    String addUser(@ModelAttribute UserDTO user){
        userService.add(user);
        return "redirect:/usersPage";
    }

    @PutMapping("/edit/{uuid}")
    String editUser(@PathVariable String uuid, @ModelAttribute UserDTO user){
        userService.update(uuid, user);
        return "redirect:/usersPage";
    }

    @DeleteMapping("/{uuid}")
    String deleteUser(@PathVariable String uuid)
    {
        userService.deleteByUuid(uuid);
        return "redirect:/usersPage";
    }
}
