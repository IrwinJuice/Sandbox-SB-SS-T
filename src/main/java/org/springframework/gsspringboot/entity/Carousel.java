package org.springframework.gsspringboot.entity;


public class Carousel {
    private String srcSlide1;
    private String h5Slide1;
    private String pSlide1;
    private String srcSlide2;
    private String h5Slide2;
    private String pSlide3;


    public Carousel() {
    }

    public Carousel(String srcSlide1, String h5Slide1, String pSlide1) {
        this.srcSlide1 = srcSlide1;
        this.h5Slide1 = h5Slide1;
        this.pSlide1 = pSlide1;
    }

    public Carousel(String srcSlide1, String h5Slide1, String pSlide1, String srcSlide2, String h5Slide2,
                    String pSlide3) {
        this.srcSlide1 = srcSlide1;
        this.h5Slide1 = h5Slide1;
        this.pSlide1 = pSlide1;
        this.srcSlide2 = srcSlide2;
        this.h5Slide2 = h5Slide2;
        this.pSlide3 = pSlide3;
    }

    public String getSrcSlide1() {
        return srcSlide1;
    }

    public void setSrcSlide1(String srcSlide1) {
        this.srcSlide1 = srcSlide1;
    }

    public String getH5Slide1() {
        return h5Slide1;
    }

    public void setH5Slide1(String h5Slide1) {
        this.h5Slide1 = h5Slide1;
    }

    public String getpSlide1() {
        return pSlide1;
    }

    public void setpSlide1(String pSlide1) {
        this.pSlide1 = pSlide1;
    }

    public String getSrcSlide2() {
        return srcSlide2;
    }

    public void setSrcSlide2(String srcSlide2) {
        this.srcSlide2 = srcSlide2;
    }

    public String getH5Slide2() {
        return h5Slide2;
    }

    public void setH5Slide2(String h5Slide2) {
        this.h5Slide2 = h5Slide2;
    }

    public String getpSlide3() {
        return pSlide3;
    }

    public void setpSlide3(String pSlide3) {
        this.pSlide3 = pSlide3;
    }
}
