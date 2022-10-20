package org.sfn.billingservice.model;

import lombok.Data;

@Data
public class Product {
    private Long id ;
    private String name ;
    private double price ;
    private double quantity ;
}
