package org.springframework.gsspringboot.model;

import javax.persistence.*;

@Entity
@Table(name = "carousel")
public class Carousel {

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

    public Carousel() {
    }

    public Carousel(String imageLink,
                    String title,
                    String artist) {
        this.imageLink = imageLink;
        this.title = title;
        this.artist = artist;
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

}
