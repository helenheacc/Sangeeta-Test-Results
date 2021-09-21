package com.springboot.CBAspringbootapp;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.CBAspringbootapp.bean.Product;
import com.springboot.CBAspringbootapp.json.ProductJson;
import com.springboot.CBAspringbootapp.service.ProductService;


@RestController
public class ProductController {
	
	private final ProductService productService;
	
	@Value("${pom.version}")
	private String pomVersion;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
    @GetMapping(path = "/v1/products", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProducts() {
    	
		List<Product> products = null;
		
		try {
			products = productService.getProducts().get();
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<Object>(ProductJson.generateErrorJson(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	    return new ResponseEntity<Object>(ProductJson.generateProductListJson(products), HttpStatus.OK);	    
    }
    
    @GetMapping(path = "/v1/product", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProduct(@RequestParam(value="id") int id) {
    	
		Optional<Product> product = productService.getProductById(id);
		
	    return new ResponseEntity<Object>(ProductJson.generateProductJson(product.get()), HttpStatus.OK);	    
    }
    
    
    @GetMapping(path = "/v1/hc", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getHc() {
    	return new ResponseEntity<Object>(ProductJson.generateVersionJson(pomVersion), HttpStatus.OK);       
    }
    
    @GetMapping(path = "*", produces=MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<Object> getNotFound() {
    	return new ResponseEntity<Object>(ProductJson.generateNotFoundJson(), HttpStatus.OK);       
    }
    
    
}
