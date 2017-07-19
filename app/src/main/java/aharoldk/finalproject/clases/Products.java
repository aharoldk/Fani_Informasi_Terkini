package aharoldk.finalproject.clases;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alwandy on 11/07/17.
 */

public class Products {

    private String brand;
    private String name;
    private String price;
    private String image_link;
    private String rating;
    private String description;

    public Products() {
    }

    public Products(String brand, String name, String price, String image_link, String rating, String description) {
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.image_link = image_link;
        this.rating = rating;
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
