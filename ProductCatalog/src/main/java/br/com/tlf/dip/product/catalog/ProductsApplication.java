package br.com.tlf.dip.product.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Products Catalog API", description = "Desafio Allura - FastTrack", version = "1.0",
				   license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")))

public class ProductsApplication {
	public static void main(String[] args){
		SpringApplication.run(ProductsApplication.class, args);		
	}

}
