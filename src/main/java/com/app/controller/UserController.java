package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.entity.User;
import com.app.repo.UserRepo;

@Controller
public class UserController {
	@Autowired
	private UserRepo repo;
    
    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/userLogin")
    public String loginUser(@ModelAttribute("user") User user) {
//        System.out.println(user.getUserId());
//        System.out.println(user.getPassword());
        String userId = user.getUserId();
        Optional<User> userdata = this.repo.findById(userId);
        if(user.getPassword().equals(userdata.get().getPassword())) {
        	return "home";
        }
        else {
        	return "error";
        }
    }
}
