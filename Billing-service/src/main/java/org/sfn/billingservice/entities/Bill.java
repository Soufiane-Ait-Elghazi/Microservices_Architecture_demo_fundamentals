package org.sfn.billingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.sfn.billingservice.model.Customer;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date billingDate ;
    private Long customerId ;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems ;
    @Transient
    private Customer customer ;


}
