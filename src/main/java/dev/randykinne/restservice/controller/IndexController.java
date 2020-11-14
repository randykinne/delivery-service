package dev.randykinne.restservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public HashMap<String, String> index() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Welcome to delivery-service");
        return map;
    }
}
