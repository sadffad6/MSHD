package org.example.mhsd.Controller;

import org.example.mhsd.Service.DecodedCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.example.mhsd.utils.R;
@RestController
public class DecodedCodeController {


    @Autowired
    DecodedCodeService decodedCodeService;
    @RequestMapping("/v1")
    public R decodedCode(@RequestParam String code) {
        decodedCodeService.decodeAndSave(code);
        return R.ok("解析成功");

    }}

