package com.example.Laptopshop.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Laptopshop.domain.Product;
import com.example.Laptopshop.domain.User;
import com.example.Laptopshop.domain.DTO.RegisterDTO;
import com.example.Laptopshop.services.ProductService;
import com.example.Laptopshop.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomePageControlle {
    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public HomePageControlle(ProductService productService, UserService userService, PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    //Hiển thị ra homePage
    @GetMapping("/")
    public String getHomePage(Model model, HttpServletRequest request) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("ListProduct", products);

        HttpSession session = request.getSession(false);

        return "client/homepage/show";
    }

    //Hiển thị ra trang đăng kí người dùng
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    //Xử lí yêu cầu đăng kí. Lưu vào sql
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("registerUser") @Valid RegisterDTO registerDTO,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }

        User user = this.userService.registerDTOtoUser(registerDTO);

        String hashPassword = this.passwordEncoder.encode(user.getPassword());

        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));
        // Save
        this.userService.handleSaveUser(user);
        return "redirect:/login";
    }

    //Hiển thị ra trang login
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/login";
    }

    //Xử lí người dùng vô trang lỗi
    @GetMapping("/access-deny")
    public String getdenyPage(Model model) {
        return "client/auth/deny";
    }

    //Hiển thị ra trang giỏ hàng
    @GetMapping("/cart")
    public String getCartPage(Model model) {
        return "client/Cart/show";
    }
}
