package com.rf.product_server.controller;

import com.rf.product_server.dto.AddProductRequest;
import com.rf.product_server.dto.ProductDto;
import com.rf.product_server.dto.UpdateProductRequest;
import com.rf.product_server.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("")
    public String get(){
        return "ürünler";
    }
    // ürün ekle
    @PostMapping("/create/{userId}")
    public ResponseEntity<?> addProduct(@Valid @RequestBody AddProductRequest request,@PathVariable Long userId){
        return ResponseEntity.ok(productService.addProduct(request,userId));
    }
    // ürün güncelle
    @PutMapping("/update/{productId}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody UpdateProductRequest request, @PathVariable Long productId){
        return ResponseEntity.ok(productService.updateProduct(request,productId));
    }
    // ürün sil
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
    // tüm ürünleri listele
    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getProductList(){
        return ResponseEntity.ok(productService.getProductList());
    }
    // kullanıcıya ait ürünleri listele
    @GetMapping("/list/{userId}")
    public ResponseEntity<List<ProductDto>> getProductListByUserId(@PathVariable Long userId){
        return ResponseEntity.ok( productService.getProductListByUserId(userId));
    }

}
