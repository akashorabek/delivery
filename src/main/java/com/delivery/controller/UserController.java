package com.delivery.controller;

import com.delivery.model.DTO.RegisterUserDTO;
import com.delivery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/login")
    public String viewLogin() {
        return "login";
    }

    @GetMapping("/login-error")
    public String viewErrorLogin(Model model) {
        model.addAttribute("hasError", true);
        return "login";
    }

    @GetMapping("/register")
    public String viewRegister() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String register(@Valid RegisterUserDTO userDTO, Model model) {
        if (!service.register(userDTO)) {
            model.addAttribute("hasServiceErrors", "Пользователь с такой почтой уже есть.");
            return "register";
        }
        return "redirect:/";
    }

    @GetMapping("/admin/users")
    public String viewUsers(Model model) {
        model.addAttribute("users", service.findAll());
        return "users";
    }

    @PostMapping("/admin/users/role")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String changeUserRole(@RequestParam String role, @RequestParam int userId) {
        service.changeUserRole(userId, role);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/enabled")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String changeUserEnabled(@RequestParam boolean enabled, @RequestParam int userId) {
        service.changeUserEnabled(userId, enabled);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/delete")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String deleteUser(@RequestParam int userId) {
        service.deleteUser(userId);
        return "redirect:/admin/users";
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    protected String handleRegisterBind(BindException ex, Model model) {
        var errors = ex.getFieldErrors()
                .stream()
                .map(fe -> fe.getDefaultMessage())
                .collect(Collectors.toList());
        model.addAttribute("bindErrors", errors);
        return "register";
    }
}
