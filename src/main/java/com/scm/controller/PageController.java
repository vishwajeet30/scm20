package com.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class PageController {

    @Autowired
    private UserService userService;

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
    public String register(Model model) {
        UserForm userForm = new UserForm();
        //userForm.setName("Vishwajeet");
        //userForm.setAbout("Write something");
        model.addAttribute("userForm", userForm);
        return "register";
    }
    
    @RequestMapping(value="/do-register", method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
        System.out.println("processRegister");
        System.out.println(userForm);

        if(rBindingResult.hasErrors())
            return "register";

        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1731658266~exp=1731661866~hmac=ce0697fb980765fe0e9e3023461e9c72002e508cfe898722157eaef1a247150a&w=740")
        // .build();
        
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1731658266~exp=1731661866~hmac=ce0697fb980765fe0e9e3023461e9c72002e508cfe898722157eaef1a247150a&w=740");

        
        User savedUser = userService.saveUser(user);
        System.out.println("user saved : ");

        //add message
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message", message);

        return "redirect:/register";
    }
    
}
