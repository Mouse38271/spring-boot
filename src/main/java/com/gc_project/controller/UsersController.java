package com.gc_project.controller;

import com.gc_project.model.UsersModel;
import com.gc_project.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        System.out.println("register request: " + usersModel);
        UsersModel registeredUser = usersService.registerUser(usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model){
        System.out.println("login request: " + usersModel);
        UsersModel authenticated = usersService.authenticate(usersModel.getEmail(), usersModel.getPassword());
        if (authenticated != null){
            model.addAttribute("userLogin", authenticated.getLogin());
            return "personal_page";
        }
        return "error_page";
    }

    @GetMapping("/Class")
    public String welcome() {
        return "class-page";
    }

    @GetMapping("/TaskPage")
    public String viewTaskPage() {
        return "task-page";
    }

    @GetMapping("/change-password")
    public String getChangePasswordPage(Model model) {
        model.addAttribute("changePasswordRequest", new UsersModel());
        return "change_password_page";
    }

    @PostMapping("/change-password")
    public String changePassword(@ModelAttribute UsersModel usersModel, @RequestParam String oldPassword, @RequestParam String newPassword) {
        System.out.println("change password request: " + usersModel);
        boolean isChanged = usersService.changePassword(usersModel.getEmail(), oldPassword, newPassword);
        return isChanged ? "redirect:/login" : "error_page";
    }
}
