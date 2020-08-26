package com.timeactuall.demo;

import com.timeactuall.demo.weather.WeatherDao;
import com.timeactuall.demo.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/")
public class TimeZoneController {
    private WeatherService weatherService;

    @Autowired
    public TimeZoneController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String showTimeZone(@CookieValue(name="zoneName", defaultValue = "0") String cookie, Model model) {
        try {
            if (cookie.equals("0")) {
                model.addAttribute("isVisible", false);
            } else {

                try {
                    getWeatherService(cookie, model);
                } catch (Exception e) {
                    model.addAttribute("temp", "weather for this zone is unavailable");
                }
                model.addAttribute("cookieZoneName", cookie);
                model.addAttribute("zoneValue", new TimeZoneDao().getTime(cookie));
                model.addAttribute("isVisible", true);
            }
        } catch (Exception e) {
            model.addAttribute("isVisible", false);
        }

        model.addAttribute("timeZone", new TimeZone());
        model.addAttribute("zone", new TimeZoneDao().getZone());
        return "home";

    }

    @GetMapping("home")
    public String redirect(){
        return "redirect:/";
    }

    @GetMapping("/cookie")
    public String next(String zoneName, HttpServletResponse response, RedirectAttributes redirectAttributes){
        Cookie cookie = new Cookie("zoneName", zoneName);
        cookie.setPath("/");
        response.addCookie(cookie);
        redirectAttributes.addAttribute("zoneName", zoneName);
        return "redirect:/results";
    }

    @GetMapping("/results")
    public String showTime(@RequestParam String zoneName, Model model) {
    try {
        try {
            getWeatherService(zoneName, model);

        } catch (Exception e) {
                model.addAttribute("temp", "weather for this zone is unavailable");
        }
        model.addAttribute("zoneName", zoneName);
        model.addAttribute("zoneValue", new TimeZoneDao().getTime(zoneName));

    } catch (Exception e){
        model.addAttribute("zoneName", "invalid zone, try again");
    }

    return "results";

    }

    private void getWeatherService(@RequestParam String zoneName, Model model) {
        WeatherDao weather;
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
    }
}


