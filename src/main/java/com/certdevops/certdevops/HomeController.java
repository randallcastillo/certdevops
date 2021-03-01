package com.certdevops.certdevops;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }

    // returns the Greeting resource
	@GetMapping("/hello-world")
	@ResponseBody // tells Spring MVC not to render a model into a view but, rather, to write the returned object into the response body.
	public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}
