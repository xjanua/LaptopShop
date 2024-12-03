package com.example.Laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.example.Laptopshop.domain.User;
import com.example.Laptopshop.services.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("eric", "test");
        model.addAttribute("HoiDanIt", "From controller with model");
        return "hello";
    }

    // Hiển thị ra bảng danh sách người dùng.
    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("listUser", users);
        return "admin/user/table-user";
    }

    // Hiển thị chi tiết người dùng.
    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable Long id) {
        User user = this.userService.getUserbyId(id);
        model.addAttribute("id", id);
        model.addAttribute("user", user);
        return "admin/user/userDetail";
    }

    // Dùng để tạo ra trang html đăng kí người dùng
    @RequestMapping("/admin/user/create")
    public String getcreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") User user) {
        this.userService.SaveUser(user);
        return "redirect:/admin/user";
    }
}