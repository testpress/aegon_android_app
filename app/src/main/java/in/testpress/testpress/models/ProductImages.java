package in.testpress.testpress.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductImages implements Serializable {
    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    @SerializedName("small")
    private String small;
}
