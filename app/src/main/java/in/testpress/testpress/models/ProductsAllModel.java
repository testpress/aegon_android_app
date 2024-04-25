package in.testpress.testpress.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductsAllModel implements Serializable {
    public List<ProductsAll> getProductsAllList() {
        return productsAllList;
    }

    public void setProductsAllList(List<ProductsAll> productsAllList) {
        this.productsAllList = productsAllList;
    }

    @SerializedName("products")
    List<ProductsAll> productsAllList;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @SerializedName("next")
    private String next;
}
