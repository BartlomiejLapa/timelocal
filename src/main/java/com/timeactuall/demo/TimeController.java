package com.timeactuall.demo;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;

        import java.time.LocalDate;
        import java.time.LocalTime;
        import java.time.format.DateTimeFormatter;


@Controller
public class TimeController {

    @RequestMapping("/")
    public String showTime(Model model) {
        LocalDate localDate = LocalDate.now();
        LocalTime now = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        model.addAttribute("time", now);
        model.addAttribute("date", localDate);

        return "home";
    }
}
