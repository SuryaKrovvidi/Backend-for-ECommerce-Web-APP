package com.example.service;

import com.example.Helper.IdGenerator;
import com.example.dao.ProductModelDao;
import com.example.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.Helper.IdGenerator;
@Service
public class ProductService {

    @Autowired
    ProductModelDao productModelDao;


    @Autowired
    IdGenerator idGenerator;


    public ResponseEntity<List<ProductModel>> getHomeProducts(){

        List<ProductModel> products = productModelDao.findAll();
        return new ResponseEntity<List<ProductModel>>(products, HttpStatus.OK);

    }


    public ResponseEntity<List<ProductModel>> getProducts(){

        List<ProductModel> products = productModelDao.findAll();
        return new ResponseEntity<List<ProductModel>>(products, HttpStatus.OK);

    }


    public ResponseEntity<ProductModel> productSave(ProductModel productModel){

        productModel.setProductId(idGenerator.getSaltString());

        if(productModelDao.existsById(productModel.getProductId())) {
            return new ResponseEntity<ProductModel>(productModel, HttpStatus.OK);
        }

//        System.out.println(productModel.getProductId());
        productModelDao.save(productModel);
        return new ResponseEntity<ProductModel>(productModel, HttpStatus.OK);
    }


    //This service updates the details of a product
    public ResponseEntity<ProductModel> productEditSave(String productId, ProductModel productModel){

        if(!(productModelDao.existsById(productId))){
            return new ResponseEntity<ProductModel>((ProductModel) null, HttpStatus.BAD_REQUEST);
        }

        ProductModel productToBeUpdated = productModelDao.findById(productId).get();

        productToBeUpdated.setProductId(productId);
        productToBeUpdated.setImageUrl(productModel.getImageUrl());
        productToBeUpdated.setProductName(productModel.getProductName());
        productToBeUpdated.setPrice(productModel.getPrice());
        productToBeUpdated.setDescription(productModel.getDescription());
        productToBeUpdated.setQuantity(productModel.getQuantity());

        System.out.println(productModel);
        System.out.println(productToBeUpdated);

        productModelDao.save(productToBeUpdated);

        ProductModel updatedProduct = productModelDao.findById(productToBeUpdated.getProductId()).get();

        return new ResponseEntity<ProductModel>(updatedProduct, HttpStatus.OK);
    }



    public ResponseEntity<ProductModel> productEditData(String productId){          //DO NOT PASS A JSON "OBJECT" IN THE REQUEST, PASS THE ID AS PLAIN STRING
        if(!(productModelDao.existsById(productId))){
            return new ResponseEntity<ProductModel>((ProductModel) null, HttpStatus.BAD_REQUEST);
        }

        ProductModel productToBeSent = productModelDao.findById(productId).get();
        return new ResponseEntity<ProductModel>(productToBeSent, HttpStatus.OK);
    }

    public ResponseEntity<ProductModel> productDelete(String productId){


        if(productModelDao.existsById(productId)){

            ProductModel deletedProduct = productModelDao.findById(productId).get();
            productModelDao.deleteById(productId);
            return new ResponseEntity<ProductModel>(deletedProduct, HttpStatus.OK);
        }
        return new ResponseEntity<ProductModel>((ProductModel) null,  HttpStatus.BAD_REQUEST);
    }
}
