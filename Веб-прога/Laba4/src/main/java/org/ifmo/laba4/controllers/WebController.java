package org.ifmo.laba4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping({"/", "/login", "/home"})
    public String serveReact() {
        return "forward:/index.html";
    }
}