/*
package org.springframework.gsspringboot.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @Column(name = "order_details_id")
    @SequenceGenerator(name = "idGenerator", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    private Long id;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_products",
            //foreign key for OrderDetails in orders_products table
            joinColumns = @JoinColumn(name = "order_details_id"),
            //foreign key for other side - Vinyl in orders_products table
            inverseJoinColumns = @JoinColumn(name = "vinyl_id"))
    Set<Vinyl>vinyls;


}
*/
