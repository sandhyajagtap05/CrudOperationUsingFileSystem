package com.Product.ProductService.Controller;



import com.Product.ProductService.Entity.Product;
import com.Product.ProductService.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file-products")
public class FileController {

    @Autowired
    private FileService fileService;


    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) throws IOException {
        fileService.createProduct(product);
        return new ResponseEntity<>("Product created in file", HttpStatus.CREATED);
    }

    // Get all products from the file
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() throws IOException {
        return new ResponseEntity<>(fileService.getAllProducts(), HttpStatus.OK);
    }

    // Update a product in the file
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) throws IOException {
        productDetails.setId(id);  // Set the ID based on the path variable
        fileService.updateProduct(productDetails);
        return new ResponseEntity<>("Product updated in file", HttpStatus.OK);
    }

    // Delete a product from the file
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws IOException {
        fileService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted from file", HttpStatus.NO_CONTENT);
    }
}


