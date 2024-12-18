package com.example.managementstore.viewController;
import com.example.managementstore.auth.AuthenticationRequest;
import com.example.managementstore.auth.AuthenticationResponse;
import com.example.managementstore.auth.AuthenticationService;
import com.example.managementstore.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationControllerVIEW {

    private final AuthenticationService authenticationService;

    // Display the registration page
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register"; // Thymeleaf template: register.html
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest registerRequest, Model model) {
        try {
            AuthenticationResponse response = authenticationService.register(registerRequest);
            model.addAttribute("message", "Registration successful. Token: " + response.getToken());
            return "redirect:/auth/login"; // Redirect to login page after successful registration
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register"; // Redisplay registration form on failure
        }
    }

    // Display the login page
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("authenticationRequest", new AuthenticationRequest());
        return "login"; // Thymeleaf template: login.html
    }

    @GetMapping("/dashboard")
    public String showDashboardPage(Model model) {
         return "dashboard"; // Thymeleaf template: login.html
    }

    // Handle login form submission
    @PostMapping("/signin")
    public String login(@ModelAttribute AuthenticationRequest authenticationRequest, Model model) {
        try {
            AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);
            model.addAttribute("message", "Login successful. Token: " + response.getToken());
            model.addAttribute("token", response.getToken());
            return "dashboard";  // Replace with your post-login page template
        } catch (Exception e) {
            model.addAttribute("error", "Login failed: " + e.getMessage());
            return "login"; // Redisplay login form on failure
        }
    }
}
