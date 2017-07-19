package aharoldk.finalproject.clases;

/**
 * Created by alwandy on 10/07/17.
 */

public class Recipes {

    private String publisher;
    private String title;
    private String image_url;
    private String social_rank;

    public Recipes() {
    }

    public Recipes(String publisher, String title, String image_url, String social_rank) {
        this.publisher = publisher;
        this.title = title;
        this.image_url = image_url;
        this.social_rank = social_rank;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String imageUrl) {
        this.image_url = imageUrl;
    }

    public String getSocial_rank() {
        return social_rank;
    }

    public void setSocial_rank(String social_rank) {
        this.social_rank = social_rank;
    }

    public String getRate(String rate){
        double value = Double.parseDouble(rate);
        return Math.floor(value) + "%";
    }
}
