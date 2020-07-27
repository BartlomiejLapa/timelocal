package com.timeactuall.demo.security.controller;

import com.timeactuall.demo.security.dto.UserRegistrationDto;
import com.timeactuall.demo.security.model.User;
import com.timeactuall.demo.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String registerForm(Model model) {
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result) {
        System.out.println("1##############" + userDto.getEmail());
        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "Account already exist with this email");
        }
        if (result.hasErrors()) {
            return "register";
        }

        userService.save(userDto);
        return "redirect:/register?success";
    }
}
