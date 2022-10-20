package org.sfn.billingservice.web;


import org.sfn.billingservice.dao.BillRepository;
import org.sfn.billingservice.dao.ProductItemRepository;
import org.sfn.billingservice.entities.Bill;
import org.sfn.billingservice.feing.CustomerRestClient;
import org.sfn.billingservice.feing.InventoryRestClient;
import org.sfn.billingservice.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {

    BillRepository billRepository ;
    ProductItemRepository productItemRepository ;
    CustomerRestClient customerRestClient;
    InventoryRestClient inventoryRestClient ;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, InventoryRestClient inventoryRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.inventoryRestClient = inventoryRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name="id") Long id){
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(pi->{
            Product product = inventoryRestClient.getProductById(pi.getProductId());
            pi.setProduct(product);
        });
        return bill;
    }
}
