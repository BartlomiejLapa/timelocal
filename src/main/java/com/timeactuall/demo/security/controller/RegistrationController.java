package com.timeactuall.demo.security.controller;

import com.timeactuall.demo.security.dto.UserRegistrationDto;
import com.timeactuall.demo.security.model.User;
import com.timeactuall.demo.security.service.SecurityService;
import com.timeactuall.demo.security.service.UserService;
import com.timeactuall.demo.security.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }


    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto, BindingResult result) {


//        userValidator.validate(user, bindingResult);
        User existing = userService.findByUsername(userRegistrationDto.getUsername());
        if (existing != null) {
            result.rejectValue("user",null, "There is alredy an account registered with this name");
        }

        if (result.hasErrors()) {
            return "registration";
        }


        userService.save(userRegistrationDto);


//        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(Model model,String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

}

