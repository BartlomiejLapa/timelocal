package com.timeactuall.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller

public class TimeZoneController {
/*
    private ModelAndView selectTag() {
        ModelAndView mav = new ModelAndView("zoneList");
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        List<String> zoneList = new ArrayList<>(allZoneIds);
        Collections.sort(zoneList);

        mav.addObject("zoneList", zoneList);
        mav.addObject("country", new TimeZoneModel());
        return mav;
    }*/

    @RequestMapping("/")
    public String showTime(Model model) {

        TimeZoneDao zone = new TimeZoneDao();
//        model.addAttribute("zoneList", timeZoneDao.getZoneList());
//        model.addAttribute("zoneTime", timeZoneTime.getZoneTime());
        model.addAttribute("mapZone", zone.getZoneTime());
        model.addAttribute("mapValue", zone.getZoneTimeValue());
        return "home";

    }
}


