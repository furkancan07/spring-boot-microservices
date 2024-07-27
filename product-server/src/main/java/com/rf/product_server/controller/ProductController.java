package com.rf.product_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @GetMapping("")
    public String get(){
        return "ürünler";
    }
    // ürün ekle
    // ürün güncelle
    // ürün sil
    // tüm ürünleri listele
    // kullanıcıya ait ürünleri listele

}
