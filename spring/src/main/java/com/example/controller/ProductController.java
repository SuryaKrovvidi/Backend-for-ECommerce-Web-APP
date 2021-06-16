package com.example.controller;

import com.example.model.ProductModel;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/home")
    ResponseEntity<List<ProductModel>> getHomeProducts(){
        return productService.getHomeProducts();
    }

    @GetMapping("/admin")       //specific to admin     //get all products
    ResponseEntity<List<ProductModel>> getProducts(){
        return productService.getProducts();
    }

    @PostMapping("/admin/addProduct")      //specific to admin     //add a product to database
    ResponseEntity<ProductModel> productSave(@RequestBody ProductModel productModel){
        return productService.productSave(productModel);
    }

    @PostMapping("/admin/productEdit/{productId}")       //specific to admin        //save the changes to Database
    ResponseEntity<ProductModel> productEditSave(@PathVariable("productId") String productId,
                                                 @RequestBody ProductModel productModel){

        return productService.productEditSave(productId, productModel);
    }


    @GetMapping("/admin/productEdit/{productId}")             //specific to admin   //get details of product
    ResponseEntity<ProductModel> productEditData(@PathVariable("productId") String productId){
        return productService.productEditData(productId);
    }

    @GetMapping("/admin/delete/{productId}")      //specific to admin
    ResponseEntity<ProductModel> productDelete(@PathVariable("productId") String productId){
        return productService.productDelete(productId);
    }


}
