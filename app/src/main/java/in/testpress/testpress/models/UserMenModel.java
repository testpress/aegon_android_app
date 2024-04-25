package in.testpress.testpress.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserMenModel implements Serializable {
    @SerializedName("display_name")
    private String display_name;
    @SerializedName("id")
    private String id;
    @SerializedName("large_image")
    private String large_image;
    @SerializedName("medium_image")
    private String medium_image;
    @SerializedName("medium_small_image")
    private String medium_small_image;
    @SerializedName("mini_image")
    private String mini_image;
    @SerializedName("photo")
    private String photo;
    @SerializedName("small_image")
    private String small_image;

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLarge_image() {
        return large_image;
    }

    public void setLarge_image(String large_image) {
        this.large_image = large_image;
    }

    public String getMedium_image() {
        return medium_image;
    }

    public void setMedium_image(String medium_image) {
        this.medium_image = medium_image;
    }

    public String getMedium_small_image() {
        return medium_small_image;
    }

    public void setMedium_small_image(String medium_small_image) {
        this.medium_small_image = medium_small_image;
    }

    public String getMini_image() {
        return mini_image;
    }

    public void setMini_image(String mini_image) {
        this.mini_image = mini_image;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSmall_image() {
        return small_image;
    }

    public void setSmall_image(String small_image) {
        this.small_image = small_image;
    }

    public String getX_small_image() {
        return x_small_image;
    }

    public void setX_small_image(String x_small_image) {
        this.x_small_image = x_small_image;
    }

    @SerializedName("x_small_image")
    private String x_small_image;

}
