package com.timeactuall.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String checkData (TimeZone timeZone){
        String zoneName = timeZone.getZoneName();
         return "redirect:/results?zoneName="+zoneName;
    }

    @GetMapping("/results")
    public String showAll(@RequestParam String zoneName,TimeZone timeZone, Model model){
        System.out.println("name:"+ zoneName);
        model.addAttribute("zoneName", zoneName);
        model.addAttribute("zoneValue", new TimeZoneDao().getTime(zoneName));
        return "results";
    }

    /*
    @PostMapping("/")
    public String checkData (TimeZone timeZone){
        String zoneName = timeZone.getZoneName();
        return "redirect:/results/"+zoneName;
    }

    @GetMapping("/results/{zoneName}")
    public String showAll(@PathVariable String zoneName,TimeZone timeZone, Model model){
        System.out.println("name:"+ zoneName);
        model.addAttribute("zoneName", timeZone.getZoneName());
        model.addAttribute("zoneValue", new TimeZoneDao().getTime(timeZone.getZoneName()));
        return "results";
    }
*/


}


