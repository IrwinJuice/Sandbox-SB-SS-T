package org.springframework.gsspringboot.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @Column(name = "cart_id")
    @SequenceGenerator(name = "idGenerator", allocationSize = 1, initialValue = 1)
                         @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
                         private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "vinyl_id", nullable = false)
    private Vinyl vinyl;

}
