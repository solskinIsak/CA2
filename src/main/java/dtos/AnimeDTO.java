package dtos;

import com.google.gson.JsonObject;

public class AnimeDTO {

    String image_url;
    String title;

    public AnimeDTO(String image_url, String title) {
        this.image_url = image_url;
        this.title = title;
    }

    public AnimeDTO() {
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "AnimeDTO{" +
                "image_url='" + image_url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
