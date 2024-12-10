package com.example.Laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.Laptopshop.domain.User;
import com.example.Laptopshop.services.UploadService;
import com.example.Laptopshop.services.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    // @RequestMapping("/")
    // public String getHomePage(Model model) {
    // model.addAttribute("eric", "test");
    // model.addAttribute("HoiDanIt", "From controller with model");
    // return "hello";
    // }

    // Dùng để tạo ra trang html đăng kí người dùng
    @GetMapping("/admin/user/create")
    public String getcreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    // Nhận user từ view Create và lưu vào sql
    @PostMapping("/admin/user/create")
    public String postcreateUser(Model model, @ModelAttribute("newUser") @Valid User user,
            BindingResult newUserBindingResult,
            @RequestParam("avatarFile") MultipartFile file) {

        // Validate (Các dòng comment chỉ xuất ở terminal)
        // List<FieldError> errors = newUserBindingResult.getFieldErrors();
        // for (FieldError error : errors) {
        // System.out.println(error.getField() + " - " + error.getDefaultMessage());
        // }

        if (newUserBindingResult.hasErrors()) {
            return "/admin/user/create";
        }

        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setAvatar(avatar);
        user.setPassword(hashPassword);
        // Get đầu tiên trả ra đối tượng role. get thứ 2 trả ra Name của Role
        user.setRole(this.userService.getRoleByName(user.getRole().getName()));
        // Save
        this.userService.handleSavUser(user);
        return "redirect:/admin/user";
    }

    // Hiển thị ra bảng danh sách người dùng.
    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("listUser", users);
        return "admin/user/show";
    }

    // Hiển thị view chi tiết 1 người dùng. (Nút View)
    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable Long id) {
        User user = this.userService.getUserbyId(id);
        model.addAttribute("user", user);
        return "admin/user/detail";
    }

    // Hiển thị view Update user.
    @RequestMapping("/admin/user/update/{id}")
    public String getupdateUserPage(Model model, @PathVariable Long id) {
        User currentUser = this.userService.getUserbyId(id);
        model.addAttribute("user", currentUser);
        return "admin/user/Update";
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
        return "admin/user/delete";
    }

    // Nhận user từ view deleteUser và xoá khỏi sql
    @PostMapping("/admin/user/delete")
    public String postdeleteUser(Model model, @ModelAttribute("newUser") User user) {
        this.userService.deleteUser(user.getId());
        return "redirect:/admin/user";
    }
}