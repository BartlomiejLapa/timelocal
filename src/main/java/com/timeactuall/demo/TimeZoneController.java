package com.timeactuall.demo;

import com.timeactuall.demo.weather.WeatherDao;
import com.timeactuall.demo.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/")
public class TimeZoneController {
    Cookie cookieZoneName;
    Cookie cookieZoneValue;
    Cookie cookieTemp;
    Cookie cookieDescription;
    Cookie cookieWeatherMain;
    Cookie cookieIcon;
    private WeatherService weatherService;

    @Autowired
    public TimeZoneController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public String showTimeZone(Model model) {
        try {
            model.addAttribute("cookieZoneName", cookieZoneName.getValue());
            model.addAttribute("cookieZoneValue", cookieZoneValue.getValue());
            model.addAttribute("cookieTemp", cookieTemp.getValue());
            model.addAttribute("cookieDescription", cookieDescription.getValue());
            model.addAttribute("cookieWeatherMain", cookieWeatherMain.getValue());
            model.addAttribute("cookieIcon", cookieIcon.getValue());
            model.addAttribute("isVisible", true);
        } catch (Exception e) {
            model.addAttribute("isVisible", false);
        }

        model.addAttribute("timeZone", new TimeZone());
        model.addAttribute("zone", new TimeZoneDao().getZone());
        return "home";

    }

    @GetMapping("/results")
    public String showTime(@RequestParam String zoneName, Model model) {
        WeatherDao weather;
    try {
        try {
            if (zoneName.contains("/")) {
                String city[] = zoneName.split("/");
                weather = weatherService.getWeather(city[city.length-1].replace("_"," "));
            } else {
                weather = weatherService.getWeather(zoneName.replace("_"," "));
            }

            model.addAttribute("temp", weather.getTemp());
            model.addAttribute("description", weather.getDescription());
            model.addAttribute("weatherMain", weather.getWeatherMain());
            model.addAttribute("icon", weather.getIcon());

            cookieTemp = new Cookie("cookieTemp", weather.getTemp());
            cookieDescription = new Cookie("cookieDescription", weather.getDescription());
            cookieWeatherMain = new Cookie("cookieWeatherMain", weather.getWeatherMain());
            cookieIcon = new Cookie("cookieIcon", weather.getIcon());

        } catch (Exception e) {
                model.addAttribute("temp", "weather for this zone is unavailable");
                cookieTemp = null;
                cookieDescription = null;
                cookieWeatherMain = null;
                cookieIcon = null;
            System.out.println("ads");
            }

        cookieZoneName = new Cookie("cookieZoneName", zoneName);
        cookieZoneValue = new Cookie("cookieZoneValue", new TimeZoneDao().getTime(zoneName).toString());

        model.addAttribute("zoneName", zoneName);
        model.addAttribute("zoneValue", new TimeZoneDao().getTime(zoneName));

    } catch (Exception e){
        model.addAttribute("zoneName", "invalid zone, try again");
        cookieZoneName = null;
        cookieZoneValue = null;
    }

    return "results";

    }
}


