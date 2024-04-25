package in.testpress.testpress.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductsAll implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    @SerializedName("start_date")
    private String start_date;

    public List<ProductImages> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImages> productImages) {
        this.productImages = productImages;
    }

    @SerializedName("images")
    private List<ProductImages> productImages;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @SerializedName("slug")
    private String slug;
    @SerializedName("price")
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getChapters_count() {
        return chapters_count;
    }

    public void setChapters_count(int chapters_count) {
        this.chapters_count = chapters_count;
    }

    @SerializedName("chapters_count")
    private int chapters_count;


}
