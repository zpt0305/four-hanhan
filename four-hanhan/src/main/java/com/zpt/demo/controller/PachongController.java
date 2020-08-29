package com.zpt.demo.controller;

import com.zpt.demo.service.PachongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pc")
public class PachongController {

    @Autowired
    PachongService pachongService;

    @RequestMapping("/insert")
    public void pachong(){
        pachongService.pc("http://cha.17173.com/lol/");
    }

}
