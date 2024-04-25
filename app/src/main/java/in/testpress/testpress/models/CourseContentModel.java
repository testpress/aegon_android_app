package in.testpress.testpress.models;

import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Attr;

import java.io.Serializable;
import java.util.List;

public class CourseContentModel implements Serializable {
    @SerializedName("quiz_count")
    private int quiz_count;
    @SerializedName("exams_count")
    private int exams_count;
    @SerializedName("attachments_count")
    private int attachments_count;
    @SerializedName("videos_count")
    private int videos_count;
    @SerializedName("video_conference_count")
    private int video_conference_count;


    public List<Mentors> getMentorsList() {
        return mentorsList;
    }

    public void setMentorsList(List<Mentors> mentorsList) {
        this.mentorsList = mentorsList;
    }

    public List<AttrModels> getAttrModels() {
        return attrModels;
    }

    public void setAttrModels(List<AttrModels> attrModels) {
        this.attrModels = attrModels;
    }

    @SerializedName("attributes")
    private List<AttrModels> attrModels;
    @SerializedName("mentors")
    private List<Mentors> mentorsList;

    public List<FaqsModel> getFaqsModels() {
        return faqsModels;
    }

    public void setFaqsModels(List<FaqsModel> faqsModels) {
        this.faqsModels = faqsModels;
    }

    @SerializedName("faqs")
    private List<FaqsModel> faqsModels;

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

    public int getLivestream_count() {
        return livestream_count;
    }

    public void setLivestream_count(int livestream_count) {
        this.livestream_count = livestream_count;
    }

    @SerializedName("livestream_count")
    private int livestream_count;


}
