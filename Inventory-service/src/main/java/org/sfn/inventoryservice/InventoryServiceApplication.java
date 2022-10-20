package org.sfn.inventoryservice;

import org.sfn.inventoryservice.dao.ProductRepository;
import org.sfn.inventoryservice.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration rrc){
        rrc.exposeIdsFor(Product.class);
        return args -> {
            productRepository.save(new Product(null,"Product01",100.0,10));
            productRepository.save(new Product(null,"Product02",100.0,20));
            productRepository.save(new Product(null,"Product03",100.0,30));
            productRepository.save(new Product(null,"Product04",100.0,40));

            productRepository.findAll().forEach(product -> {
                System.out.println(product.getName());
            });
        };
    }
}
