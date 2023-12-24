//package com.example.lab.carapplicationweb.controllers;
//
//import com.example.lab.carapplicationweb.models.UserRole;
//import com.example.lab.carapplicationweb.services.UserRoleService;
//import com.example.lab.carapplicationweb.services.dtos.UserRoleDTO;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/userRoles")
//public class UserRoleController {
//
//    private UserRoleService userRoleService;
//
//    private static final Logger LOG = LogManager.getLogger(Controller.class);
//
//    @Autowired
//    public void setUserService(UserRoleService userRoleService) { this.userRoleService = userRoleService; }
//
//    @GetMapping
//    String getAll(Model model){
//        List<UserRole> userRoles = userRoleService.getAll();
//        model.addAttribute("userRoles", userRoles);
//        return "userRolesPage";
//    }
//
//    @GetMapping("/{uuid}")
//    String getUserRole (@PathVariable String uuid, Model model){
//        Optional<UserRole> userRole = userRoleService.findByUuid(uuid);
//        model.addAttribute("userRole", userRole.get());
//        return "userRolePage";
//    }
//
//    @PostMapping("/add")
//    String addUserRole(@ModelAttribute UserRoleDTO userRole){
//        userRoleService.add(userRole);
//        return "redirect:/userRolesPage";
//    }
//
//    @PutMapping("/edit/{uuid}")
//    String editUserRole(@PathVariable String uuid, @ModelAttribute UserRoleDTO userRole){
//        userRoleService.update(uuid, userRole);
//        return "redirect:/userRolesPage";
//    }
//
//    @DeleteMapping("/{uuid}")
//    String deleteUserRole(@PathVariable String uuid)
//    {
//        userRoleService.deleteByUuid(uuid);
//        return "redirect:/userRolesPage";
//    }
//}
