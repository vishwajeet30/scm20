package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home Page");
        model.addAttribute("name", "Subtring Technology");
        model.addAttribute("channel", "sony");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("isLogin", true);
        System.out.println("About Page Loading");
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services Page Loading");
        return "services";
    }

    //contact page
    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }
    @GetMapping("/register")
    public String register() {
        return new String("register");
    }
    
    
}