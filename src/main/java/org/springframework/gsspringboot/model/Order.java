package org.springframework.gsspringboot.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/*@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    @SequenceGenerator(name = "idGenerator", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "order_status", nullable = false)
    private Integer orderStatus;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @OneToOne(mappedBy = "order")
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")//****
    private Set<OrderDetails> orderDetails;
}*/
