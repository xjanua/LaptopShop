package com.example.Laptopshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Laptopshop.domain.Cart;
import com.example.Laptopshop.domain.CartDetail;
import com.example.Laptopshop.domain.Product;
import com.example.Laptopshop.domain.User;
import com.example.Laptopshop.repository.CartDetailRepository;
import com.example.Laptopshop.repository.CartRepository;
import com.example.Laptopshop.repository.ProductRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository,
            UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
    }

    public Product handleSaveProduct(Product product) {
        Product saveProduct = this.productRepository.save(product);
        return saveProduct;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductByID(long id) {
        return this.productRepository.findById(id).orElseThrow();
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    // Nhận vào 1 user và id sản phẩm khi được thêm vào giỏ.
    public void handleAddProductToCart(String email, long productId, HttpSession session) {

        User user = this.userService.getUserByEmail(email);

        if (user != null) {
            Cart cart = this.cartRepository.findByUser(user);

            if (cart == null) {
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(0);

                cart = this.cartRepository.save(otherCart);
            }

            Optional<Product> optionalProduct = this.productRepository.findById(productId);

            if (optionalProduct.isPresent()) {

                Product realProduct = optionalProduct.get();

                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);

                if (oldDetail == null) {
                    CartDetail newCartDetail = new CartDetail();
                    newCartDetail.setCart(cart); // Cart_id
                    newCartDetail.setProduct(realProduct); // Product_id
                    newCartDetail.setPrice(realProduct.getPrice());
                    newCartDetail.setQuantity(1);
                    this.cartDetailRepository.save(newCartDetail);

                    // update Cart Sum
                    int s = cart.getSum() + 1;
                    cart.setSum(cart.getSum() + 1);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);
                } else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
                }

            }

        }
    }
}
