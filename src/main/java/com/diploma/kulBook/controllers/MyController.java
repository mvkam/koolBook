package com.diploma.kulBook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
    @RequestMapping(value = "/")
    @ResponseBody
    public ModelAndView getRoot() {
        return new ModelAndView("index");
    }
}
