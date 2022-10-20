package org.sfn.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.sfn.billingservice.model.Product;

import javax.persistence.*;


@Data
@NoArgsConstructor @AllArgsConstructor @ToString
@Entity
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private double quantity ;
    private double price ;
    private Long productId ;
    @ManyToOne
   // @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Bill bill ;
    @Transient
    private Product product ;

}
