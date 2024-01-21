package com.example.lab.carapplicationweb.controllers;

import com.example.lab.carapplicationweb.models.User;
import com.example.lab.carapplicationweb.services.UserService;
import com.example.lab.carapplicationweb.services.dtos.EditUser;
import com.example.lab.carapplicationweb.services.dtos.UserRegistrationDto;
import com.example.lab.carapplicationweb.services.impl.AuthService;
import com.example.lab.carapplicationweb.views.UserProfileView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class AuthController {

    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDto initForm() {
        return new UserRegistrationDto();
    }

    @GetMapping("/register")
    public String register() {
        return "user-register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDto userRegistration,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistration", userRegistration);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistration", bindingResult);
            return "user-register";
        }
        try {
            this.authService.register(userRegistration);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("userRegistration", userRegistration);
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/users/register";
        }
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        User user = authService.getUser(username);

        UserProfileView userProfileView = new UserProfileView(
                username,
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );

        model.addAttribute("user", userProfileView);

        return "profile";
    }

    @GetMapping("/profile/edit")
    public String showEditProfile(Model model, Principal principal) {
        User editUser = authService.findByUsername(principal.getName());
        model.addAttribute("user", editUser);
        return "editUser";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@Valid @ModelAttribute("user") EditUser editUser,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editUser";
        }

        authService.update(editUser);

        return "redirect:/users/profile";
    }
}