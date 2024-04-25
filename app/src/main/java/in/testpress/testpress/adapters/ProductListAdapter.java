package in.testpress.testpress.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import in.testpress.testpress.R;
import in.testpress.testpress.models.DashboardSectionModel;
import in.testpress.testpress.ui.CoureContentActivity;
import in.testpress.util.ImageUtils;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private List<DashboardSectionModel> sectionModelList;
    private Context mContext;
    private LayoutInflater mInflater;

    public ProductListAdapter(Context context, List<DashboardSectionModel> sectionModelList) {
        this.sectionModelList = sectionModelList;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lay_item_products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DashboardSectionModel sectionModel = sectionModelList.get(position);
        holder.txt_currentprice.setText(sectionModel.getCurrent_price());
        holder.txt_title.setText(sectionModel.getTitle());
        holder.txt_startDate.setText(changeDateformate(sectionModel.getStart_date()));
        ImageLoader imageLoader = ImageUtils.initImageLoader(mContext);
        DisplayImageOptions options = ImageUtils.getPlaceholdersOption();
        imageLoader.displayImage(sectionModel.getImage(), holder.imageview, options);
//        BitmapDrawable drawable=(BitmapDrawable) holder.imageview.getDrawable();
//        holder.lay_imageview.setBackground(drawable);
//        holder.imageview.setVisibility(View.GONE);
//        Glide.with(mContext)
//                .load(sectionModel.getImage())
//                .into(holder.imageview);
//        ImageView img = new ImageView(mContext);
//        Picasso.get().load(sectionModel.getImage()).into(img, new Callback() {
//            @Override
//            public void onSuccess() {
//                holder.lay_imageview.setBackground(img.getDrawable());
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });
        holder.itemView.setTag(sectionModel.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag1 = v.getTag().toString();
//                List<Integer> array = new ArrayList<>();
//                array.add(509);
                Activity activity = (Activity) mContext;
                Intent intent = new Intent(activity, CoureContentActivity.class);
//                intent.putExtra("productSlug", tag1);
                intent.putExtra("pid", tag1);
//                activity.startActivityForResult(intent, STORE_REQUEST_CODE);
              activity.startActivity(intent);
            }
        });
    }

    private String changeDateformate(String start_date) {
        try {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
            String inputDateStr = start_date;
            Date date = inputFormat.parse(inputDateStr);
            String outputDateStr = outputFormat.format(date);
            return outputDateStr;
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return sectionModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_currentprice, txt_title, txt_startDate, txt_endate;
        ImageView imageview;
        private LinearLayout lay_imageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_currentprice = itemView.findViewById(R.id.txt_currentprice);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_startDate = itemView.findViewById(R.id.txt_startDate);
            txt_endate = itemView.findViewById(R.id.txt_enddate);
            imageview = itemView.findViewById(R.id.imageview);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;
//            itemView.setLayoutParams(new RecyclerView.LayoutParams((int)(width * 0.9), RecyclerView.LayoutParams.WRAP_CONTENT));
        }
    }
}
