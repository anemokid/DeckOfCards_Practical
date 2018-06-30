package com.example.niemawidaha.practical_deckofcards.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Card {

    // private members:
    @SerializedName("suit")
    @Expose
    private String suit;

    @SerializedName("images")
    @Expose
    private Images images;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("value")
    @Expose
    private String value;

    // constructor:
    public Card(){

    }

    public Card(String suit, String image, String code, String value) {
        this.suit = suit;
        this.image = image;
        this.code = code;
        this.value = value;
    }

    // getters
    public String getSuit() {
        return suit;
    }

    public Images getImages() {
        return images;
    }

    public String getImage() {
        return image;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    // setters:

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // to String():
    @Override
    public String toString() {
        return "Card{" +
                "suit='" + suit + '\'' +
                ", images=" + images +
                ", image='" + image + '\'' +
                ", code='" + code + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    /**
     * Images inner class:
     */
    public class Images {

        // private members:
        @SerializedName("png")
        @Expose
        private String png;

        @SerializedName("svg")
        @Expose
        private String svg;

        // constructors:
        public Images(){

        }

        public Images(String png, String svg) {
            this.png = png;
            this.svg = svg;
        }

        // getters + setters:
        public String getPng() {
            return png;
        }

        public void setPng(String png) {
            this.png = png;
        }

        public String getSvg() {
            return svg;
        }

        public void setSvg(String svg) {
            this.svg = svg;
        }

        @Override
        public String toString() {
            return "Images{" +
                    "png='" + png + '\'' +
                    ", svg='" + svg + '\'' +
                    '}';
        }
    }
}
