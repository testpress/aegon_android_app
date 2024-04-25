package in.testpress.testpress.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Mentors implements Serializable {
    @SerializedName("subject")
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public UserMenModel getUserMenModel() {
        return userMenModel;
    }

    public void setUserMenModel(UserMenModel userMenModel) {
        this.userMenModel = userMenModel;
    }

    @SerializedName("user")
    private UserMenModel userMenModel;

}
