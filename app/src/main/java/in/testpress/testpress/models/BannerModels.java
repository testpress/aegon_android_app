package in.testpress.testpress.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BannerModels implements Serializable {
    public String getImage_small() {
        return image_small;
    }

    public void setImage_small(String image_small) {
        this.image_small = image_small;
    }

    @SerializedName("image_small")
    private String image_small;

    public String getImage_medium() {
        return image_medium;
    }

    public void setImage_medium(String image_medium) {
        this.image_medium = image_medium;
    }

    @SerializedName("image_medium")
    private String image_medium;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("image")
    private String image;
}
