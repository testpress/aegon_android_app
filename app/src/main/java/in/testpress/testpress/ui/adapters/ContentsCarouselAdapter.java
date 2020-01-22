package in.testpress.testpress.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import in.testpress.core.TestpressSdk;
import in.testpress.core.TestpressSession;
import in.testpress.course.TestpressCourse;
import in.testpress.models.greendao.Chapter;
import in.testpress.models.greendao.Content;
import in.testpress.models.greendao.Exam;
import in.testpress.testpress.R;
import in.testpress.testpress.models.pojo.DashboardResponse;
import in.testpress.testpress.util.UIUtils;
import in.testpress.util.ImageUtils;

public class ContentsCarouselAdapter extends RecyclerView.Adapter<ContentsCarouselAdapter.MyViewHolder> {
    private DashboardResponse response;
    private List<Content> contents;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private Context context;

    public ContentsCarouselAdapter(DashboardResponse response, Context context) {
        this.response = response;
        this.contents = response.getContents();
        this.context = context;
        imageLoader = ImageUtils.initImageLoader(context);
        options = ImageUtils.getPlaceholdersOption();
    }

    @Override
    public ContentsCarouselAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_carousel_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ContentsCarouselAdapter.MyViewHolder holder, final int position) {
        final Content content = contents.get(position);

        imageLoader.displayImage("https://picsum.photos/320/180?random=" + position, holder.image, options);
        holder.image.setColorFilter(Color.parseColor("#77000000"));

        setIconAndChapterTitle(content, holder);
        showOrHideVideoAccessories(content, holder);
        showOrHideExamAccessories(content, holder);


        String contentName = content.getName();
        String capitalizedContentName = contentName.substring(0,1).toUpperCase() + contentName.substring(1).toLowerCase();
        holder.title.setText(capitalizedContentName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = (Activity) context;
                TestpressSession session = TestpressSdk.getTestpressSession(context);
                TestpressCourse.showContentDetail(activity, content.getId().toString(), session);
            }
        });
    }

    private void setIconAndChapterTitle(Content content, ContentsCarouselAdapter.MyViewHolder holder) {
        if (response.getChapterHashMap().get(content.getChapterId()) != null) {
            Chapter chapter = response.getChapterHashMap().get(content.getChapterId());
            holder.subtitle.setText(chapter.getName());
        }

        switch (content.getContentType().toLowerCase()) {
            case "video":
                holder.contentTypeIcon.setImageResource(R.drawable.ic_video_white);
                break;
            case "exam":
                holder.contentTypeIcon.setImageResource(R.drawable.ic_exam);
                break;
            case "notes":
                holder.contentTypeIcon.setImageResource(R.drawable.ic_news);
                break;
            case "attachment":
                holder.contentTypeIcon.setImageResource(R.drawable.ic_attachment);
                break;
        }
    }

    private void showOrHideVideoAccessories(Content content, ContentsCarouselAdapter.MyViewHolder holder) {
        if (content.getVideoId() != null) {
            holder.playIcon.setVisibility(View.VISIBLE);
        } else {
            holder.playIcon.setVisibility(View.GONE);
        }
    }

    private void showOrHideExamAccessories(Content content, ContentsCarouselAdapter.MyViewHolder holder) {
        if (content.getExamId() != null) {
            Exam exam = response.getExamHashMap().get(content.getExamId());
            holder.infoLayout.setVisibility(View.VISIBLE);
            holder.numberOfQuestions.setText(exam.getNumberOfQuestions().toString());
        } else {
            holder.infoLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image, playIcon, contentTypeIcon;
        TextView title, numberOfQuestions, subtitle;
        LinearLayout infoLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image_view);
            playIcon = (ImageView) itemView.findViewById(R.id.play_icon);
            contentTypeIcon = (ImageView) itemView.findViewById(R.id.content_type_icon);
            infoLayout = (LinearLayout) itemView.findViewById(R.id.info_layout);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            numberOfQuestions = (TextView) itemView.findViewById(R.id.number_of_questions);

            title.setTypeface(UIUtils.getLatoSemiBoldFont(context));
        }
    }
}