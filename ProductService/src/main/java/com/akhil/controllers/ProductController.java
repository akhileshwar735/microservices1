package com.akhil.controllers;


//import com.akhil.entity.ApiError;
import com.akhil.entity.ApiError;
import com.akhil.entity.Product;
import com.akhil.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/api/products") // uniform interface for this resource
@RestController // instructs spring to create an instance of this class and keep in container
// also, handler mapping scans this object for all the handler functions (@RequestMapping, @GetMapping, ...)
public class ProductController {

    @Autowired // instruct spring to find an object of type ProductService and assign it to this reference.
    ProductService service;

    // http://localhost:5000/api/products/23
    @GetMapping(path = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Object> handleGetProductById(@PathVariable Integer id) {
        Product p = service.getProductById(id);
        if (p == null) {
            // respond with 404
            // return ResponseEntity.notFound().build();
            ApiError err = new ApiError(HttpStatus.NOT_FOUND, "No product was found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error received in code");
        } else {
            // respond with 200
            return ResponseEntity.ok(p);
        }
    }

    @GetMapping(path = "/{id}", produces = "text/plain")
    public String handleGetProductByIdAsString(@PathVariable Integer id) {
        return service.getProductById(id).toString();
    }

    @GetMapping(produces = "application/json")
    public Iterable<Product> handleGetAllProducts(
            @RequestParam(name = "_page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "_limit", required = false, defaultValue = "10") Integer size
    ) {
        return service.getAllProducts(page, size);
    }

    @GetMapping("/not-in-stock")
    public Iterable<Product> handleGetOutOfStockProducts(){
        return service.getOutOfStockProducts();
    }

    // /api/products/by-price?min=50&max=100
    @GetMapping("/by-price")
    public Iterable<Product> handleGetProductsByPriceRange(
            @RequestParam Double min, @RequestParam Double max){
        return service.getProductsInPriceRange(min, max);
    }

    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<Object> handleAddNewProduct(@RequestBody Product product){
        try{
            Product p=service.addNewProduct(product);
            return ResponseEntity.ok(p);
        }
        catch(Exception e){
            ApiError err=new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }

    @PutMapping(path = "/{id}",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Object> handleUpdateProduct(@PathVariable Integer id,@RequestBody Product product){
        try{
            product.setProductId(id);
            Product p=service.updateProduct(product);
            return ResponseEntity.ok(p);
        }
        catch(Exception e){
            ApiError err=new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }

    @DeleteMapping(path = "/{id}",produces = "application/json")
    public ResponseEntity<Object> handleDeleteProduct(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(service.deleteProduct(id)); //status 200
        }
        catch(Exception e){
            ApiError err=new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }
}