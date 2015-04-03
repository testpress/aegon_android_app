package in.testpress.testpress.core;

import java.util.HashMap;
import java.util.List;

import in.testpress.testpress.models.Exam;
import in.testpress.testpress.models.UserExam;
import in.testpress.testpress.models.Questions;
import in.testpress.testpress.models.TestpressApiResponse;
import retrofit.http.Body;
import retrofit.http.EncodedPath;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

public interface ExamService {

    @GET(Constants.Http.URL_AVAILABLE_EXAMS_FRAG)
    TestpressApiResponse<Exam> getAvailableExams(@Header("Authorization") String authorization);

    @GET(Constants.Http.URL_UPCOMING_EXAMS_FRAG)
    TestpressApiResponse<Exam> getUpcomingExams(@Header("Authorization") String authorization);

    @GET(Constants.Http.URL_HISTORY_EXAMS_FRAG)
    TestpressApiResponse<Exam> getHistoryExams(@Header("Authorization") String authorization);

    @GET("/{questions_url}")
    TestpressApiResponse<Questions> getQuestions(@EncodedPath("questions_url") String questionsUrlFrag, @Header("Authorization") String authorization);

    @POST(Constants.Http.URL_START_EXAM_FRAG)
    UserExam getUserExam(@Header("Authorization") String authorization, @Body HashMap<String, String> arguments);
}


