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

    // Dùng để tạo ra trang html đăng kí người dùng
    @RequestMapping("/admin/user/create")
    public String getcreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/createUser";
    }

    // Nhận user từ view Create và lưu vào sql
    @PostMapping("/admin/user/create")
    public String postcreateUser(Model model, @ModelAttribute("newUser") User user) {
        this.userService.handleSavUser(user);
        return "redirect:/admin/user";
    }

    // Hiển thị ra bảng danh sách người dùng.
    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("listUser", users);
        return "admin/user/table-user";
    }

    // Hiển thị view chi tiết 1 người dùng. (Nút View)
    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable Long id) {
        User user = this.userService.getUserbyId(id);
        model.addAttribute("user", user);
        return "admin/user/userDetail";
    }

    // Hiển thị view Update user.
    @RequestMapping("/admin/user/update/{id}")
    public String getupdateUserPage(Model model, @PathVariable Long id) {
        User currentUser = this.userService.getUserbyId(id);
        model.addAttribute("user", currentUser);
        return "admin/user/userUpdate";
    }

    // Nhận user từ view updateUser và lưu vào sql
    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("user") User user) {
        User currentUser = this.userService.getUserbyId(user.getId());
        if (currentUser != null) {
            currentUser.setFullname(user.getFullname());
            currentUser.setAddress(user.getAddress());
            currentUser.setPhone(user.getPhone());
            this.userService.handleSavUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    // Hiển thị view delete User
    @RequestMapping("/admin/user/delete/{id}")
    public String getdeleteUserPage(Model model, @PathVariable Long id) {
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "admin/user/deleteUser";
    }

    // Nhận user từ view deleteUser và xoá khỏi sql
    @PostMapping("/admin/user/delete")
    public String postdeleteUser(Model model, @ModelAttribute("user") User user) {
        this.userService.deleteUser(user.getId());
        return "redirect:/admin/user";
    }
}