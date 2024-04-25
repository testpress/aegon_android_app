package in.testpress.testpress.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DashboardModel implements Serializable {
    public List<DashboardSectionModel> getSectionModelList() {
        return sectionModelList;
    }

    public void setSectionModelList(List<DashboardSectionModel> sectionModelList) {
        this.sectionModelList = sectionModelList;
    }

    @SerializedName("products")
    private List<DashboardSectionModel> sectionModelList;

    public List<BannerModels> getBannerModels() {
        return bannerModels;
    }

    public void setBannerModels(List<BannerModels> bannerModels) {
        this.bannerModels = bannerModels;
    }

    @SerializedName("banner_ads")
    private List<BannerModels> bannerModels;
}
