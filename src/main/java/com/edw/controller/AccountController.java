package com.edw.controller;

import com.edw.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.regex.Pattern;

@Controller
public class AccountController {
    private static final Pattern VALID_USERNAME = Pattern.compile("[A-Za-z0-9_.-]{3,50}");

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           Model model) {
        String normalizedUsername = username.trim();
        if (!VALID_USERNAME.matcher(normalizedUsername).matches()) {
            model.addAttribute("error",
                    "Use 3-50 letters, numbers, periods, underscores, or hyphens for your username.");
            model.addAttribute("username", normalizedUsername);
            return "register";
        }
        if (password.length() < 8) {
            model.addAttribute("error", "Password must be at least 8 characters long.");
            model.addAttribute("username", normalizedUsername);
            return "register";
        }
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            model.addAttribute("username", normalizedUsername);
            return "register";
        }
        if (!accountService.register(normalizedUsername, password)) {
            model.addAttribute("error", "That username is already registered.");
            model.addAttribute("username", normalizedUsername);
            return "register";
        }

        return "redirect:/login?registered";
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "dashboard";
    }
}
