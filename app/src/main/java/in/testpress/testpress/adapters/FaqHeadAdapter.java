package in.testpress.testpress.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import in.testpress.testpress.R;

public class FaqHeadAdapter  extends RecyclerView.Adapter<FaqHeadAdapter.ViewHolder> {
    private List<String> parentItems;
    private Map<String, List<String>> childItems;
    private Context context;

    public FaqHeadAdapter(Context context, List<String> parentItems, Map<String, List<String>> childItems) {
        this.context = context;
        this.parentItems = parentItems;
        this.childItems = childItems;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq_head, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String parentItem = parentItems.get(position);
        holder.parentTextView.setText(parentItem);

        holder.itemView.setOnClickListener(v -> {
            if (holder.childRecyclerView.getVisibility() == View.VISIBLE) {
                holder.childRecyclerView.setVisibility(View.GONE);
            } else {
                holder.childRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        List<String> childData = childItems.get(parentItem);
        if (childData != null) {
            ChildFaqAdapter childAdapter = new ChildFaqAdapter(context, childData);
            holder.childRecyclerView.setAdapter(childAdapter);
        }
    }
    @Override
    public int getItemCount() {
        return parentItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView parentTextView;
        RecyclerView childRecyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentTextView = itemView.findViewById(R.id.faq_parent_text_view);
            childRecyclerView = itemView.findViewById(R.id.faq_child_recycler_view);
            childRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }
}
