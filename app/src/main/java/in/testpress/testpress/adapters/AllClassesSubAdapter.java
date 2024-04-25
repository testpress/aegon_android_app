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

public class AllClassesSubAdapter extends RecyclerView.Adapter<AllClassesSubAdapter.ViewHolder> {
    private List<String> titles;
    private Context mContext;

    public AllClassesSubAdapter(Context context, List<String> titles) {
        this.mContext = context;
        this.titles = titles;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_allclasses_frag, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = titles.get(position);
        holder.txt_allclass_title.setText(title);

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_allclass_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_allclass_title = itemView.findViewById(R.id.txt_allclass_title);

        }
    }
}
