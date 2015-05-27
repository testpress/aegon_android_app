package in.testpress.testpress.core;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.activeandroid.query.Select;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import in.testpress.testpress.Injector;
import in.testpress.testpress.TestpressServiceProvider;
import in.testpress.testpress.authenticator.LogoutService;
import in.testpress.testpress.models.Attempt;
import in.testpress.testpress.models.Exam;
import in.testpress.testpress.models.TestpressApiResponse;
import retrofit.RetrofitError;


public class ExamPager extends ResourcePager<Exam> {
    String subclass;
    TestpressApiResponse<Exam> response;

    @Inject protected TestpressServiceProvider serviceProvider;
    @Inject protected LogoutService logoutService;

    public ExamPager(String subclass, TestpressService service) {
        super(service);
        this.subclass = subclass;
        Injector.inject(this);
    }

    @Override
    public ResourcePager<Exam> clear() {
        response = null;
        return super.clear();
    }

    @Override
    protected Object getId(Exam resource) {
        return resource.getExamId();
    }

    @Override
    public List<Exam> getItems(int page, int size) throws RetrofitError {
        String url = null;
        if (response == null) {
            if (subclass.equals("available")) {
                url = Constants.Http.URL_AVAILABLE_EXAMS_FRAG;
            } else if (subclass.equals("upcoming")) {
                url = Constants.Http.URL_UPCOMING_EXAMS_FRAG;
            } else if (subclass.equals("history")) {
                url = Constants.Http.URL_HISTORY_EXAMS_FRAG;
            } else {
                url = Constants.Http.URL_AVAILABLE_EXAMS_FRAG;
            }
        } else {
            try {
                URL full = new URL(response.getNext());
                url = full.getFile().substring(1);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                url = null;
            }

        }
        if (url != null) {
            try {
                List<Exam> examList;
                response = service.getExams(url, queryParams);
                examList = response.getResults();
                if (subclass.equals("history")) {
                    for (Exam exam : examList) {
                        Exam temp;
                        if(queryParams.get("course") != null) {
                            exam.query = queryParams.get("course");
                            temp = new Select()
                                    .from(Exam.class).where("examId = ?",exam.getExamId())
                                    .where("query = ?", queryParams.get("course"))
                                    .executeSingle();
                        } else {
                            temp = new Select()
                                .from(Exam.class).where("examId = ?",exam.getExamId())
                                .executeSingle();
                        }
                        if(temp == null) {
                            exam.save();
                        }
                    }
                    return getAll();
                } else {
                    return response.getResults();
                }
            }
            catch (Exception e) {
                if((e.getMessage()).equals("403 FORBIDDEN")) {
                    throw e;
                } else {
                    try {
                        if (subclass.equals("history")) {
                            List<Exam> exams = getAll();
                            return exams;
                        } else {
                            return null;
                        }
                    } catch (Exception exception) {
                        return null;
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    public List<Exam> getAll() {
        if(queryParams.get("course")!=null) {
        return new Select().all()
                .from(Exam.class).where("query = ?", queryParams.get("course"))
                .orderBy("examId DESC")
                .execute();
        }
        else  return new Select().all()
                .from(Exam.class)
                .orderBy("examId DESC")
                .execute();
    }

    @Override
    public boolean hasNext() {
        if (response == null) {
            return true;
        }

        if (response.getNext() != null) {
            if(queryParams != null)
            queryParams.clear();
            return true;
        }
        return false;
    }
}
