package in.testpress.testpress.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.testpress.testpress.R;

public class otherdetailsAdapter extends RecyclerView.Adapter<otherdetailsAdapter.ViewHolder> {
    private List<String> childItems;
    private Context context;

    public otherdetailsAdapter(Context context, List<String> childItems) {
        this.context = context;
        this.childItems = childItems;
    }

    @Override
    public int getItemCount() {
        return childItems.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item_child, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String childItem = childItems.get(position);
        holder.childTextView.setText(childItem);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView childTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            childTextView = itemView.findViewById(R.id.child_text_view);
        }
    }
}
