package org.springframework.gsspringboot.model;

import javax.persistence.*;

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

    public Vinyl(String imageLink,
                 String title,
                 String artist,
                 Double price,
                 Double quantity) {
        this.imageLink = imageLink;
        this.title = title;
        this.artist = artist;
        this.price = price;
        this.quantity = quantity;
    }

    public Vinyl(String title, String artist, Double price, Double quantity) {
        this.title = title;
        this.artist = artist;
        this.price = price;
        this.quantity = quantity;
    }

    public Vinyl() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
