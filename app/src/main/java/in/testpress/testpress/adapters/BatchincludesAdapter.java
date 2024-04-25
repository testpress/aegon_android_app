package in.testpress.testpress.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.testpress.testpress.R;
import in.testpress.testpress.models.IncludeBatchModels;

public class BatchincludesAdapter extends RecyclerView.Adapter<BatchincludesAdapter.ViewHolder> {
    private List<IncludeBatchModels> includeBatchModelsList;
    private Context mContext;
    private LayoutInflater mInflater;
    public BatchincludesAdapter(Context context, List<IncludeBatchModels> includeBatchModelsList) {
        this.includeBatchModelsList = includeBatchModelsList;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lay_item_include_batch, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IncludeBatchModels inclue = includeBatchModelsList.get(position);
        holder.txt_count.setText("" + inclue.getCount()+" +");
        holder.txtTitle.setText(inclue.getTitle());

    }

    @Override
    public int getItemCount() {
        return includeBatchModelsList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgv_include_batch;
        TextView txt_count, txtTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgv_include_batch = itemView.findViewById(R.id.imgv_include_batch);
            txt_count = itemView.findViewById(R.id.txt_count);
            txtTitle = itemView.findViewById(R.id.txt_title);
        }
    }
}
