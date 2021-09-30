package br.com.tlf.dip.product.catalog.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.tlf.dip.product.catalog.domain.Products;
import br.com.tlf.dip.product.catalog.repository.ProductRepository;


@Service
public class ProductsServiceImpl implements ProductsService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Products> findAll(){
		return productRepository.findAll();	
	}
	
	@Override
	public ResponseEntity<Products> findById(Long id){
		return ResponseEntity.of(productRepository.findById(id));
	}
	
	@Override
	public List<Products> findFiltered(String q, Double minPrice, Double maxPrice){
		return productRepository.findFiltered(q, minPrice, maxPrice);
	}
	
	@Override
	public ResponseEntity<Products> post( Products products, UriComponentsBuilder uriBuilder){
		Products p = productRepository.save(products);		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(p.getId()).toUri();
		return ResponseEntity.created(uri).body(p);
	}
	
	@Override
	public ResponseEntity<Products> update(Long id, Products products){
		Optional<Products> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			Products p = optional.get();
			p.setDescription(products.getDescription());
			p.setName(products.getName());
			p.setPrice(products.getPrice());
			productRepository.save(p);
			return ResponseEntity.ok(p);
		}
			return ResponseEntity.notFound().build();
	}
	
	@Override
	public ResponseEntity<Products> delete(@PathVariable Long id){
		Optional<Products> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}		
		return ResponseEntity.notFound().build();		
	}
	

}
