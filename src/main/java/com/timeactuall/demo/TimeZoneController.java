package com.timeactuall.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
public class TimeZoneController {

    @GetMapping
    public String showTimeZone(Model model) {

        model.addAttribute("timeZone", new TimeZone());
        model.addAttribute("zone", new TimeZoneDao().getZone());
        return "home";

    }

    @PostMapping("/")
    public String checkData (TimeZone timeZone,Model model){
        System.out.println(timeZone);
        model.addAttribute("zoneName", timeZone.getZoneName());
        model.addAttribute("zoneValue", new TimeZoneDao().getTime(timeZone.getZoneName()));

        return "results";
    }

}


