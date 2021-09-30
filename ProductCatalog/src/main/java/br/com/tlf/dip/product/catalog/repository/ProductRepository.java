package br.com.tlf.dip.product.catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.tlf.dip.product.catalog.domain.Products;


public interface ProductRepository extends JpaRepository<Products,Long>{
	
	
	@Query(value = "SELECT * FROM Products WHERE (:q IS NULL OR (UPPER(name) LIKE UPPER(CONCAT('%', :q, '%'))" 
	    	+ "OR UPPER(description) LIKE UPPER(CONCAT('%', :q, '%'))))" 
				+ "AND (:minPrice IS NULL OR price >= :minPrice)" 
					+ "AND (:maxPrice IS NULL OR price <= :maxPrice)", nativeQuery = true)
	List<Products> findFiltered(String q, Double minPrice , Double maxPrice);

}
