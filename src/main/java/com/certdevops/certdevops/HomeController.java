package com.certdevops.certdevops;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/certdevops")
public class HomeController {
    @GetMapping
    public @ResponseBody String home() {
        return "Certificacion en DevOps, UCreativa";
    }
}
