package com.example.springLogin.controller;

import com.example.springLogin.model.User;
import com.example.springLogin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String LoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("user") User user,
                              RedirectAttributes redirectAttributes,
                              HttpSession session) {
        if (userService.authenticate(user.getId(), user.getPassword())) {
            session.setAttribute("id", user.getId());
            return "redirect:/login/home";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid login request");
            return "redirect:/login";
        }
    }

    @GetMapping("/signup")
    public String SignupPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String handleSignup(@ModelAttribute("user") User user,
                               RedirectAttributes redirectAttributes) {
        userService.registerUser(user);
        redirectAttributes.addFlashAttribute("id", user.getId());
        return "redirect:/login/home";
    }

    @GetMapping("/login/home")
    public String HomePage(HttpSession session, Model model) {
        String id = (String) session.getAttribute("id");
        if (id != null) {
            model.addAttribute("id", id);
            return "home";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}