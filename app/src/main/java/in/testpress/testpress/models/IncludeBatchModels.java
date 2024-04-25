package in.testpress.testpress.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IncludeBatchModels implements Serializable {

    private int quiz_count;
    private int exams_count;
    private int attachments_count;
    private int videos_count;
    private String title;
    private String uri;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count;

    public int getQuiz_count() {
        return quiz_count;
    }

    public void setQuiz_count(int quiz_count) {
        this.quiz_count = quiz_count;
    }

    public int getExams_count() {
        return exams_count;
    }

    public void setExams_count(int exams_count) {
        this.exams_count = exams_count;
    }

    public int getAttachments_count() {
        return attachments_count;
    }

    public void setAttachments_count(int attachments_count) {
        this.attachments_count = attachments_count;
    }

    public int getVideos_count() {
        return videos_count;
    }

    public void setVideos_count(int videos_count) {
        this.videos_count = videos_count;
    }

    public int getVideo_conference_count() {
        return video_conference_count;
    }

    public void setVideo_conference_count(int video_conference_count) {
        this.video_conference_count = video_conference_count;
    }

    private int video_conference_count;


}
