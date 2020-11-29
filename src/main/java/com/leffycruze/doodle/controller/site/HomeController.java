package com.leffycruze.doodle.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/site")
public class HomeController {

    @GetMapping("")
    public String home() {
        return "index";
    }
}
