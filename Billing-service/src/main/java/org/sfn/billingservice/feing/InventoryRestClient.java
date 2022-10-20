package org.sfn.billingservice.feing;

import org.sfn.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryRestClient {
    @GetMapping(path = "/products/{id}")
    public Product getProductById(@PathVariable(name="id") Long id);

    @GetMapping(path = "/products")
    PagedModel<Product> pageProducts(@RequestParam(name="page") int page , @RequestParam(name="size") int size);

}
