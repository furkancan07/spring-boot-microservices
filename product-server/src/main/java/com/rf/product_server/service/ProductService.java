package com.rf.product_server.service;


import com.rf.product_server.dto.AddProductRequest;
import com.rf.product_server.dto.ProductDto;

import com.rf.product_server.dto.UpdateProductRequest;
import com.rf.product_server.dto.User;
import com.rf.product_server.entity.Product;
import com.rf.product_server.error.exception.AuthorizationException;
import com.rf.product_server.error.exception.NotFoundException;
import com.rf.product_server.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final AuthService authService;
    public ProductDto addProduct(AddProductRequest request,Long userId) {
        User user=userService.findByUserId(userId);
        if(user==null) throw new NotFoundException(userId+" id numaralı kullanıcı");
        if(authService.getIdOfLoggedInUser()!=userId) throw new AuthorizationException();
        Product product=Product.builder().description(request.getDescription()).
                name(request.getName()).userId(userId).price(request.getPrice()).build();
        productRepository.save(product);
        return toProduct(product);
    }

    public ProductDto updateProduct(UpdateProductRequest request,Long productId) {
        Product product=findById(productId);
        if(product.getUserId()!= authService.getIdOfLoggedInUser()) throw new AuthorizationException();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        productRepository.save(product);
        User user=userService.findByUserId(product.getUserId());
        return toProduct(product);
    }

    public String deleteProduct(Long id) {
        Product product=findById(id);
        if(product.getUserId()!=authService.getIdOfLoggedInUser()) throw new AuthorizationException();
        productRepository.deleteById(id);
        return "Ürün Başari ile Silindi";
    }
    protected Product findById(Long id){
        return productRepository.findById(id).orElseThrow(()->new NotFoundException(id+" numaralı ürün"));
    }

    public List<ProductDto> getProductList() {
        List<Product> list=productRepository.findAll();
        return  list.stream().map(this::toProduct).collect(Collectors.toList());
    }

    public List<ProductDto> getProductListByUserId(Long userId) {
        List<Product> list=productRepository.findByUserId(userId);
        return list.stream().map(this::toProduct).collect(Collectors.toList());
    }
    private ProductDto toProduct(Product product){
        return ProductDto.builder().user(userService.findByUserId(product.getUserId())).name(product.getName()).price(product.getPrice())
                .description(product.getDescription()).id(product.getId()).build();
    }
    public void deleteByUserId(Long userId){
        List<Product> products=productRepository.findAll();
        for (Product product : products){
            if(product.getUserId().equals(userId)){
                productRepository.delete(product);
            }
        }

    }

}
