package org.nervous.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController()
public class HelloController {

    @RequestMapping(path = "/hello", method = GET)
    public String show() {
        return "hello, world.";
    }
}
