package br.com.tlf.dip.product.catalog.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.tlf.dip.product.catalog.domain.Products;
import br.com.tlf.dip.product.catalog.service.ProductsService;
import br.com.tlf.dip.product.catalog.validation.ValidationHandler;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/products")
public class ProductsController{
	
	 @Autowired
	 private ProductsService productsService;
	
	 
	 @ApiResponses(value = { 
			 @ApiResponse(responseCode = "200", description = "OK", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = Products.class)))}),
			 @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
	 @GetMapping
	 public @ResponseBody List<Products> findAll(){		
		 return productsService.findAll();
	 }
	
	 
	 
	 @ApiResponses(value = { 
			 @ApiResponse(responseCode = "200", description = "OK", content = { @Content( schema = @Schema(implementation = Products.class)) }),
			 @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			 @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
	 @GetMapping("/{id}")
	 public ResponseEntity<Products> findById(@PathVariable Long id){
		 return productsService.findById(id);
	 }
	
	 
	 
	 @ApiResponses(value = { 
			 @ApiResponse(responseCode = "200", description = "OK", content = { @Content( array = @ArraySchema(schema = @Schema(implementation = Products.class)))}),
			 @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
	 @GetMapping("/search")
	 public List<Products> findFiltered(
			@RequestParam(required=false) String q,
			@RequestParam(required=false, value = "min_price") Double minPrice,
			@RequestParam(required=false, value = "max_price") Double maxPrice){		
		 return productsService.findFiltered(q, minPrice, maxPrice);
	 }
	
	 

	 @ApiResponses(value = { 
			 @ApiResponse(responseCode = "201", description = "Created", content = { @Content( schema = @Schema(implementation = Products.class)) }),
			 @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content( schema = @Schema(implementation = ValidationHandler.class))}),
			 @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
	 @Transactional
	 @PostMapping
	 public ResponseEntity<Products> post(@RequestBody @Valid Products products, UriComponentsBuilder uriBuilder){		
		 return productsService.post(products, uriBuilder);
	 }
	
	 
	 
	 @ApiResponses(value = { 
			 @ApiResponse(responseCode = "200", description = "OK", content = { @Content( schema = @Schema(implementation = Products.class)) }),
			 @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content( schema = @Schema(implementation = ValidationHandler.class))}),
			 @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			 @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
	 @Transactional
	 @PutMapping("/{id}")
	 public ResponseEntity<Products> update(@PathVariable Long id, @RequestBody @Valid Products products){
		 return productsService.update(id, products);
	 }
	
	 
	
	 @ApiResponses(value = { 
			 @ApiResponse(responseCode = "200", description = "OK", content = @Content),
			 @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			 @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
	 @Transactional
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Products> delete(@PathVariable Long id){
		 return productsService.delete(id);
	 }
}