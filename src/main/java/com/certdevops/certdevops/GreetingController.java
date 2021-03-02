package com.certdevops.certdevops;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // returns the Greeting resource
    @GetMapping("/greet")
    @ResponseBody // tells Spring MVC not to render a model into a view but, rather, to write the
                  // returned object into the response body.
    public Greeting sayHello(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
}
