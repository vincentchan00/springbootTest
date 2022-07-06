package com.example.hatchways.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PingController {

    @GetMapping("/api/ping")

    @ResponseBody
    public HashMap<String, Boolean> checkPing() {
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("success", true);
        return  map;
    }

}
