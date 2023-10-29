package com.dreamteam.findyourfather.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AngularController {

    @GetMapping("/angular")
    public String getAngularApp() {
        return "index.html";
    }
}
