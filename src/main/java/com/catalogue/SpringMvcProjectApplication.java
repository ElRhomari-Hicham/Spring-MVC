package com.catalogue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.catalogue.dao.ProductRepository;
import com.catalogue.entitie.Product;

@SpringBootApplication
public class SpringMvcProjectApplication implements CommandLineRunner{

	@Autowired
	private ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMvcProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("************** Welcome **************");
//		productRepository.save(new Product(null, "Dell XPS", 9800, 50));
//		productRepository.save(new Product(null, "HP Pavillon", 8056, 44));
//		productRepository.save(new Product(null, "ASUS Rog", 12000, 23));
//		productRepository.save(new Product(null, "MSI", 20500, 10));
		
		Page<Product> productPages = productRepository.findByDesignationContains("S", PageRequest.of(1, 2));
		productPages.forEach(p -> {
			System.out.println(p.toString());
		});
	}
}


