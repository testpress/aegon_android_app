package in.testpress.testpress.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import in.testpress.core.TestpressSdk;
import in.testpress.testpress.ApiServiceFactory;
import in.testpress.testpress.Injector;
import in.testpress.testpress.R;
import in.testpress.testpress.TestpressServiceProvider;
import in.testpress.testpress.adapters.AllProductsListAdapter;
import in.testpress.testpress.adapters.ProductListAdapter;
import in.testpress.testpress.models.BannerModels;
import in.testpress.testpress.models.DashboardModel;
import in.testpress.testpress.models.DashboardSectionModel;
import in.testpress.testpress.models.ProductsAll;
import in.testpress.testpress.models.ProductsAllModel;
import in.testpress.util.ImageUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardNewFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardNewFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private View mView;
    private RecyclerView rv_products, rv_allcourse;
    String token;
    ApiServiceFactory.Apiservice apiservice = ApiServiceFactory.getApiservie();
    @Inject
    protected TestpressServiceProvider serviceProvider;
    private List<String> dataList;
    AllProductsListAdapter allProductsListAdapter;
    private ImageView img_bannertop, img_bannerbottom;

    ShimmerFrameLayout shimmer_view_container;

    TextView txt_popularcourses, txt_recentcourses;

    private int currentPage;
    private String next;
    private String nextUrl;
    private RecyclerView.OnScrollListener scrollListener;
    private boolean isLoading;
    List<ProductsAll> productsAllList;

    public DashboardNewFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardNewFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardNewFrag newInstance(String param1, String param2) {
        DashboardNewFrag fragment = new DashboardNewFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_dashboard_new, container, false);
        initViews();
        return mView;
    }

    private void showLoading() {
        shimmer_view_container.setVisibility(View.VISIBLE);
        shimmer_view_container.startShimmer();
    }

    private void hideShimmer() {
        shimmer_view_container.stopShimmer();
        shimmer_view_container.setVisibility(View.GONE);
    }

    private void initViews() {
        Injector.inject(getActivity());
        shimmer_view_container = mView.findViewById(R.id.shimmer_view_container);
        rv_products = mView.findViewById(R.id.rv_products);
        txt_popularcourses = mView.findViewById(R.id.txt_popularcourses);
        txt_recentcourses = mView.findViewById(R.id.recent_courses);
        rv_allcourse = mView.findViewById(R.id.rv_allcourse);
        img_bannertop = mView.findViewById(R.id.img_bannertop);
        img_bannerbottom = mView.findViewById(R.id.img_bannerbottom);
        String JsonToken = null;
        JsonToken = "JWT " + TestpressSdk.getTestpressSession(requireContext()).getToken();
        if (ApiServiceFactory.isNetworkAvailable(getActivity())) {
            showLoading();
            apiservice.getDashboard(JsonToken, "").clone().enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String result = response.body().string().toString();
                        if (result != null) {
                            Gson gson = new Gson();
                            DashboardModel dashboardModel = gson.fromJson(result, DashboardModel.class);
                            setBanners(dashboardModel.getBannerModels());
                            List<DashboardSectionModel> sectionModelList = dashboardModel.getSectionModelList();
                            setDashboardSectionModel(sectionModelList);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        } else {
            Toast.makeText(getActivity(), getString(R.string.msg_connect_internet), Toast.LENGTH_SHORT).show();
        }
        next = "1";
        isLoading = true;
        getProducts("");
        isLoading = false;
        rv_allcourse.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        dataList = new ArrayList<>();
        dataList.add("Item 1");
        dataList.add("Item 2");
        dataList.add("Item 3");
        dataList.add("Item 4");
        // Add more items as needed
//        allProductsListAdapter = new AllProductsListAdapter(dataList);
//        rv_allcourse.setAdapter(allProductsListAdapter);
        int spanCount = 2; // 2 columns
        int spacing = 50; // 50px spacing
        boolean includeEdge = true;
        rv_allcourse.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
//       rv_allcourse.addItemDecoration(new MultiColorItemDecoration(getActivity(), R.drawable.divider_drawable));

    }

    private void getProducts(String s) {
        if (ApiServiceFactory.isNetworkAvailable(getActivity())) {
            String JsonToken = null;
            JsonToken = "JWT " + TestpressSdk.getTestpressSession(requireContext()).getToken();
            apiservice.getProducts(JsonToken, "", next).clone().enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    hideShimmer();
                    try {
                        String result = response.body().string().toString();
                        if (result != null) {
                            Gson gson = new Gson();
                            JSONObject jsonObject = new JSONObject(result);
                            ProductsAllModel dashboardModel = gson.fromJson(jsonObject.get("results").toString(), ProductsAllModel.class);
                            nextUrl = jsonObject.getString("next");
                            productsAllList = dashboardModel.getProductsAllList();
                            setProductsAllModel(productsAllList);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        } else {
            Toast.makeText(getActivity(), getString(R.string.msg_connect_internet), Toast.LENGTH_SHORT).show();
        }
    }

    private void setBanners(List<BannerModels> bannerModels) {
        int iteration = 0;
        for (BannerModels bannerModels1 : bannerModels) {
            if (iteration == 0) {
                ImageLoader imageLoader = ImageUtils.initImageLoader(getActivity());
                DisplayImageOptions options = ImageUtils.getPlaceholdersOption();
                imageLoader.displayImage(bannerModels1.getImage(), img_bannerbottom, options);
            }
            if (iteration == 1) {
                ImageLoader imageLoader = ImageUtils.initImageLoader(getActivity());
                DisplayImageOptions options = ImageUtils.getPlaceholdersOption();
                imageLoader.displayImage(bannerModels1.getImage(), img_bannertop, options);

            }
            iteration++;
        }
    }

    private void setProductsAllModel(List<ProductsAll> productsAllList) {
        txt_recentcourses.setVisibility(View.VISIBLE);
        allProductsListAdapter = new AllProductsListAdapter(getActivity(), productsAllList);
        rv_allcourse.setAdapter(allProductsListAdapter);
        int spanCount = 2; // 2 columns
        int spacing = 50; // 50px spacing
        boolean includeEdge = true;
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), spanCount);
        rv_allcourse.setLayoutManager(layoutManager);
//        rv_allcourse.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
//                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
//                int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
//                        && firstVisibleItemPosition >= 0) {
//                    // Load more data if not already loading and end of list is reached
//                    loadMoreData();
//                }
//            }
//        });
//            scrollListener= new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//                    if (!recyclerView.canScrollVertically(1)) {
//                        loadMoreData();
//                    }
//                }
//            };


    }

    private void loadMoreData() {
        currentPage++;
        isLoading = true;
        try {
            String nextItem = nextUrl.split("=")[1];
            next = nextItem;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getProducts(next);
        String JsonToken = null;
        JsonToken = "JWT " + TestpressSdk.getTestpressSession(requireContext()).getToken();
        apiservice.getProducts(JsonToken, "", next).clone().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                hideShimmer();
                try {
                    String result = response.body().string().toString();
                    if (result != null) {
                        Gson gson = new Gson();
                        JSONObject jsonObject = new JSONObject(result);
                        ProductsAllModel dashboardModel = gson.fromJson(jsonObject.get("results").toString(), ProductsAllModel.class);
                        nextUrl = jsonObject.getString("next");
                        List<ProductsAll> productsModelAllList = dashboardModel.getProductsAllList();
                        for (ProductsAll productsAll : productsModelAllList) {
                            productsAllList.add(productsAll);
                        }
                        allProductsListAdapter.notifyDataSetChanged();
//                        setProductsAllModel(productsAllList);
                        isLoading = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


        // Call Retrofit API to fetch more data
        // Example: MyApiService.getData(currentPage)
        // Append fetched data to dataList
        // Example: dataList.addAll(response.body().getDataList());
        // Notify adapter of data change
        // Example: adapter.notifyDataSetChanged();
    }

    private void setDashboardSectionModel(List<DashboardSectionModel> sectionModelList) {
        txt_popularcourses.setVisibility(View.VISIBLE);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
//        {
//            @Override
//            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
////                lp.width = getWidth() /3;
//                return true;
//            }
//        };
//        rv_products.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        int spanCount = 1; // 2 columns
        int spacing = 50; // 50px spacing
        boolean includeEdge = true;
        rv_products.setLayoutManager(new GridLayoutManager(getActivity(), spanCount, GridLayoutManager.HORIZONTAL, false));
//        rv_products.setLayoutManager(staggeredGridLayoutManager);
        ProductListAdapter productListAdapter = new ProductListAdapter(getActivity(), sectionModelList);
//        rv_products.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
        rv_products.setAdapter(productListAdapter);
    }
}