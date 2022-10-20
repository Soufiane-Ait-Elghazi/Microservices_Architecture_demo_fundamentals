package org.sfn.billingservice;

import org.sfn.billingservice.dao.BillRepository;
import org.sfn.billingservice.dao.ProductItemRepository;
import org.sfn.billingservice.entities.Bill;
import org.sfn.billingservice.entities.ProductItem;
import org.sfn.billingservice.feing.CustomerRestClient;
import org.sfn.billingservice.feing.InventoryRestClient;
import org.sfn.billingservice.model.Customer;
import org.sfn.billingservice.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(RepositoryRestConfiguration rrc,
                            BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient ,
                            InventoryRestClient inventoryRestClient)
    {
        rrc.exposeIdsFor(ProductItem.class,Bill.class);
        return  args -> {
            Customer customer = customerRestClient.getCustomerById(1L);
            System.out.println(customer.toString());
            Bill bill = billRepository.save(new Bill(null,new Date(),customer.getId(),null,customer));
            PagedModel<Product> productPageModel = inventoryRestClient.pageProducts(0,5);
            productPageModel.forEach(p->{
                ProductItem productItem = new ProductItem();
                productItem.setPrice(p.getPrice());
                productItem.setProduct(p);
                productItem.setProductId(p.getId());
                productItem.setQuantity(1+new Random().nextInt(20));
                productItem.setBill(bill);
                productItemRepository.save(productItem);
            });
        };
    }
}
