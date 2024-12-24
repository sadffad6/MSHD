package com.mshd.controller;

import com.mshd.service.VisualizationService;
import com.mshd.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mshd.utils.jwt.JwtToken;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/disaster_data")
@CrossOrigin
public class VisualizationController {
    @Autowired
    private VisualizationService visualizationService;

    @GetMapping("/counts")
    public R disastersum() {
        Map<String, Integer> map = new HashMap<>();
        return  R.ok("sucess",visualizationService.countDisaster());
    }
}
