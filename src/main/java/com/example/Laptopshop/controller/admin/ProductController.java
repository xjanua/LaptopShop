package com.example.Laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Laptopshop.domain.Product;
import com.example.Laptopshop.services.ProductService;
import com.example.Laptopshop.services.UploadService;

import jakarta.validation.Valid;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    // Hiển thị view tạo mới sản phẩm
    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    // Nhận sản phẩm từ view tạo mới sản phẩm rồi thêm vào sql
    @PostMapping("/admin/product/create")
    public String postCreateProductPage(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("imgProduct") MultipartFile file) {

        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/create";
        }

        String ImgProduct = this.uploadService.handleSaveUploadFile(file, "product");
        product.setImage(ImgProduct);
        this.productService.handleSaveProduct(product);
        return "redirect:/admin/product";
    }

    // Hiển thị ra bảng danh sách product.
    @GetMapping("/admin/product")
    public String getViewAllProductPage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("ListProduct", products);
        return "admin/product/show";
    }

    // Hiển thị chi tiết product
    @GetMapping("/admin/product/{id}")
    public String getDetailProduct(Model model, @PathVariable long id) {
        Product product = this.productService.getProductByID(id);
        model.addAttribute("product", product);
        return "admin/product/detail";
    }

    // Hiển thị ra view Update Product
    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProduct(Model model, @PathVariable long id) {
        Product currentProduct = this.productService.getProductByID(id);
        model.addAttribute("product", currentProduct);
        return "admin/product/update";
    }

    // Nhận product Update và lưu lại vào sql
    @PostMapping("/admin/product/update")
    public String postUpdateProductPage(Model model,
            @ModelAttribute("product") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("imgProduct") MultipartFile file) {

        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/update";
        }

        Product currentProduct = this.productService.getProductByID(product.getId());
        // Product currentProduct =
        // this.productService.fetchProductById(product.getId()).get();
        if (currentProduct != null) {
            if (file.isEmpty() == false) {
                String img = this.uploadService.handleSaveUploadFile(file, "product");
                currentProduct.setImage(img);
            }
            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setDetailDesc(product.getDetailDesc());
            currentProduct.setShortDesc(product.getShortDesc());
            currentProduct.setQuantity(product.getQuantity());
            currentProduct.setFactory(product.getFactory());
            currentProduct.setTarget(product.getTarget());
            currentProduct.setName(product.getName());
            this.productService.handleSaveProduct(currentProduct);
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("product", new Product());
        return "admin/product/delete";
    }

    // Nhận user từ view deleteUser và xoá khỏi sql
    @PostMapping("/admin/product/delete")
    public String postdeleteProduct(Model model, @ModelAttribute("product") Product product) {
        this.productService.deleteProduct(product.getId());
        return "redirect:/admin/product";
    }
}
