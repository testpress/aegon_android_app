package in.testpress.testpress.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import in.testpress.testpress.R;
import in.testpress.testpress.models.ProductsAll;
import in.testpress.testpress.ui.CoureContentActivity;
import in.testpress.util.ImageUtils;

public class AllProductsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ProductsAll> dataList;
    private Context mContext;
    int[] colors;
    private boolean isLoading = false;
    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    public AllProductsListAdapter(Context mcontext, List<ProductsAll> dataList) {
        this.dataList = dataList;
        this.mContext = mcontext;
        colors = mcontext.getResources().getIntArray(R.array.card_colors);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recently_courses, parent, false);
            return new ItemViewHolder(view);
        }else{
            View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item_layout, parent, false);
            return new LoadingViewHolder(loadingView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder , int position) {
        ProductsAll productsAll = dataList.get(position);
        ImageLoader imageLoader = ImageUtils.initImageLoader(mContext);
        DisplayImageOptions options = ImageUtils.getPlaceholdersOption();
        try {
            imageLoader.displayImage(productsAll.getProductImages().get(0).getSmall(), ((ItemViewHolder) holder).imgv_product, options);
        }catch (Exception e){

        }

        ((ItemViewHolder) holder).txt_course_title.setText(productsAll.getTitle());
        ((ItemViewHolder) holder).txt_sections.setText("" + productsAll.getChapters_count() + " Sections");

        String price = productsAll.getPrice();
        Double priceAmount = 0.0;
        try {
            priceAmount = Double.parseDouble(price);
        } catch (Exception e) {

        }
        if (priceAmount > 0) {
            ((ItemViewHolder) holder).txt_price.setText("" + priceAmount);
        } else {
            ((ItemViewHolder) holder).txt_price.setText("Free");
        }
        int color = colors[position % colors.length];
       setRoundedBackground(((ItemViewHolder) holder).carview1,color,20f);
        ((ItemViewHolder) holder).itemView.setTag(productsAll.getId());
        ((ItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag1 = v.getTag().toString();

//                Intent intent = new Intent(mContext, ProductDetailsActivity.class);
//                intent.putExtra(ProductDetailsActivity.PRODUCT_SLUG, tag);
                Activity activity = (Activity) mContext;
//                activity.startActivityForResult(intent, TestpressStore.STORE_REQUEST_CODE);
//                activity.startActivity(CoursePreviewActivity.createIntent((ArrayList<Integer>) array, activity, tag1));
//                mContext.startActivity(new Intent(mContext, CourseSampleActivity.class));
                Intent intent = new Intent(activity, CoureContentActivity.class);
                intent.putExtra("pid", tag1);
//                intent.putExtra("productSlug", tag1);
//                activity.startActivityForResult(intent, STORE_REQUEST_CODE);
               activity.startActivity(intent);
            }
        });
    }
    private void setRoundedBackground(CardView cardView, int backgroundColor, float cornerRadius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(backgroundColor);
        drawable.setCornerRadius(cornerRadius); // Set corner radius

        cardView.setBackground(drawable); // Set the drawable as background
    }
    private int dpToPx(int dp,Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
    @Override
    public int getItemViewType(int position) {
        return position < dataList.size() ? VIEW_TYPE_ITEM : VIEW_TYPE_LOADING;
    }

    @Override
    public int getItemCount() {
        return dataList.size() + (isLoading ? 1 : 0);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView, txt_course_title, txt_sections, txt_price;
        private ImageView imgv_product;
        CardView carview1;

        MyViewHolder(View itemView) {
            super(itemView);
            imgv_product = itemView.findViewById(R.id.imgv_product);
            txt_course_title = itemView.findViewById(R.id.txt_course_title);
            carview1 = itemView.findViewById(R.id.carview1);
            txt_sections = itemView.findViewById(R.id.txt_sections);
            txt_price = itemView.findViewById(R.id.txt_price);


        }
    }
    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView, txt_course_title, txt_sections, txt_price;
        private ImageView imgv_product;
        CardView carview1;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgv_product = itemView.findViewById(R.id.imgv_product);
            txt_course_title = itemView.findViewById(R.id.txt_course_title);
            carview1 = itemView.findViewById(R.id.carview1);
            txt_sections = itemView.findViewById(R.id.txt_sections);
            txt_price = itemView.findViewById(R.id.txt_price);

        }
        // Bind method and views
    }
    private static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        // Views for loading indicator, if any
    }
}
