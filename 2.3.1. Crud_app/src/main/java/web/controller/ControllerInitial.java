package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import web.model.User;
import web.service.UserService;

@Controller
public class ControllerInitial {
    private UserService userService;

    @Autowired
    public ControllerInitial(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String printUsers() {
        return "index";
    }

    @GetMapping("/admin")
    public String adminUsers(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("userAuth", userService.loadUserByUsername(auth.getName()));
        model.addAttribute("userNew", new User());
        model.addAttribute("roles", userService.roleSet());
        model.addAttribute("users", userService.UserList());
        return "admin";
    }

    @GetMapping("/user")
    public String showUser(Model model, @RequestParam(required = false, defaultValue = "jetenov") String s) {
        model.addAttribute("user", userService.loadUserByUsername(s));
        return "user";
    }
}