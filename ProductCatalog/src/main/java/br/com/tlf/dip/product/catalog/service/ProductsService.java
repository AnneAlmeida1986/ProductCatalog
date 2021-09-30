package br.com.tlf.dip.product.catalog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.tlf.dip.product.catalog.domain.Products;

public interface ProductsService {
	public List<Products> findAll();	
	public ResponseEntity<Products> findById(Long id);	
	public List<Products> findFiltered(String q, Double minPrice, Double maxPrice);	
	public ResponseEntity<Products> post(Products products, UriComponentsBuilder uriBuilder);
	public ResponseEntity<Products> update(Long id, Products products);	
	public ResponseEntity<Products> delete(Long id);

}
