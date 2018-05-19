package org.springframework.gsspringboot.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "vinyls")
public class Vinyl {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "idGenerator", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    private Long id;

    @Column(name = "image_link")
    private String imageLink;

    @Column(name = "title")
    private String title;

    @Column(name = "artist")
    private String artist;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Double quantity;

    @OneToMany(mappedBy = "vinyl", cascade = CascadeType.ALL)
    private Set<Cart>carts = new HashSet<>();

}
