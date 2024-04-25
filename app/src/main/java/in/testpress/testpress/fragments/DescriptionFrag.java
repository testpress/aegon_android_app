package in.testpress.testpress.fragments;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.testpress.core.TestpressSdk;
import in.testpress.store.models.Product;
import in.testpress.testpress.ApiServiceFactory;
import in.testpress.testpress.R;
import in.testpress.testpress.adapters.BatchincludesAdapter;
import in.testpress.testpress.adapters.FaqHeadAdapter;
import in.testpress.testpress.adapters.OtherDetialsheadAdapter;
import in.testpress.testpress.models.AttrModels;
import in.testpress.testpress.models.CourseContentModel;
import in.testpress.testpress.models.FaqsModel;
import in.testpress.testpress.models.IncludeBatchModels;
import in.testpress.testpress.ui.CoureContentActivity;
import in.testpress.util.EventsTrackerFacade;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescriptionFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescriptionFrag extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;
    RecyclerView rv_other_details, rv_content_faq, rv_batch_includes;
    ApiServiceFactory.Apiservice apiservice = ApiServiceFactory.getApiservie();
    String pid;
    private TextView txt_courseFee_actual;
    private Button btn_desc_buynow;
    private Product product;
    private EventsTrackerFacade eventsTrackerFacade;


    public DescriptionFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DescriptionFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static DescriptionFrag newInstance(String param1, String param2) {
        DescriptionFrag fragment = new DescriptionFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_description, container, false);
        initViews();
        eventsTrackerFacade = new EventsTrackerFacade(getContext());
        return mView;
    }

    private void initViews() {
        pid = getActivity().getIntent().getStringExtra("pid");
        btn_desc_buynow = mView.findViewById(R.id.btn_desc_buynow);
        txt_courseFee_actual = mView.findViewById(R.id.txt_courseFee_actual);
        rv_batch_includes = mView.findViewById(R.id.rv_batch_includes);
        rv_other_details = mView.findViewById(R.id.rv_other_details);
        rv_content_faq = mView.findViewById(R.id.rv_content_faq);
        btn_desc_buynow.setOnClickListener(buynowListner);
        getCourseContentDetails();
        rv_other_details.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> parentItems = Arrays.asList("Other Details");
        Map<String, List<String>> childItems = new HashMap<>();
        childItems.put("Other Details", Arrays.asList("Complete NEET syllabus will be coverd on time", "2 live class of 2.5 hours per day", "whole syllabus split into 4 trems"));
        OtherDetialsheadAdapter adapter = new OtherDetialsheadAdapter(getActivity(), parentItems, childItems);
        rv_other_details.setAdapter(adapter);

    }

    View.OnClickListener buynowListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CoureContentActivity coureContentActivity = (CoureContentActivity) getActivity();
            coureContentActivity.updateDisplay(new PaymentSummaryFrag());
        }
    };

    private void getCourseContentDetails() {
        String JsonToken = null;
        JsonToken = "JWT " + TestpressSdk.getTestpressSession(getActivity()).getToken();
        if (ApiServiceFactory.isNetworkAvailable(getActivity())) {
            apiservice.getProductcontentDetails(JsonToken, "", pid).clone().enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String result = response.body().string().toString();
                        if (result != null) {
                            Gson gson = new Gson();
                            CourseContentModel courseContentModel = gson.fromJson(result, CourseContentModel.class);
                            setCourseContentDetails(courseContentModel);
                        }
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        } else {
            Toast.makeText(getActivity(), getString(R.string.msg_connect_internet), Toast.LENGTH_SHORT).show();
        }
    }

    private void setCourseContentDetails(CourseContentModel courseContentModel) {
        SpannableString spannableString = new SpannableString("2200");
        int startIndex = 0; // Start index of the text range
        int endIndex = 4;
        spannableString.setSpan(new StrikethroughSpan(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_courseFee_actual.setText(spannableString);
        List<IncludeBatchModels> includeBatchModelsList = new ArrayList<>();
//        if(courseContentModel.getAttachments_count()>0) {
//            IncludeBatchModels includeBatchModels = new IncludeBatchModels();
//            includeBatchModels.setAttachments_count(courseContentModel.getAttachments_count());
//            includeBatchModels.setTitle("");
//            includeBatchModels.setCount(courseContentModel.getAttachments_count());
//            includeBatchModelsList.add(includeBatchModels);
//        }
        if (courseContentModel.getLivestream_count() > 0) {
            IncludeBatchModels liveStreamCount = new IncludeBatchModels();
            liveStreamCount.setVideo_conference_count(courseContentModel.getLivestream_count());
            liveStreamCount.setTitle("Live Lecture");
            liveStreamCount.setCount(courseContentModel.getLivestream_count());
            includeBatchModelsList.add(liveStreamCount);
        }
        if (courseContentModel.getQuiz_count() > 0) {
            IncludeBatchModels quizCount = new IncludeBatchModels();
            quizCount.setQuiz_count(courseContentModel.getQuiz_count());
            quizCount.setTitle("Dpps's Quizzes");
            quizCount.setCount(courseContentModel.getQuiz_count());
            includeBatchModelsList.add(quizCount);
        }
        if (courseContentModel.getExams_count() > 0) {
            IncludeBatchModels examsCount = new IncludeBatchModels();
            examsCount.setExams_count(courseContentModel.getExams_count());
            examsCount.setTitle("Tests");
            examsCount.setCount(courseContentModel.getExams_count());
            includeBatchModelsList.add(examsCount);
        }
//        if(courseContentModel.getVideos_count()>0)
//        {
//            IncludeBatchModels videosCount = new IncludeBatchModels();
//            videosCount.setQuiz_count(courseContentModel.getVideos_count());
//            videosCount.setTitle("Dpps's Quizzes");
//            videosCount.setCount(courseContentModel.getVideos_count());
//            includeBatchModelsList.add(videosCount);
//        }
        int spanCount = 1;
        rv_batch_includes.setLayoutManager(new GridLayoutManager(getActivity(), spanCount, GridLayoutManager.HORIZONTAL, false));
        BatchincludesAdapter batchincludesAdapter = new BatchincludesAdapter(getActivity(), includeBatchModelsList);
        rv_batch_includes.setAdapter(batchincludesAdapter);
        setOtherDetails(courseContentModel.getAttrModels());
        setFquesAdapter(courseContentModel.getFaqsModels());
    }

    private void setOtherDetails(List<AttrModels> attrModels) {
        List<AttrModels> attrModel= new ArrayList<>();
        

    }

    private void setFquesAdapter(List<FaqsModel> faqsModels) {
        if (faqsModels != null && faqsModels.size() > 0) {
            rv_content_faq.setLayoutManager(new LinearLayoutManager(getActivity()));
            List<String> parentItemsFaq = new ArrayList<>();
            Map<String, List<String>> childItemsFaq = new HashMap<>();
            for (FaqsModel faqs : faqsModels) {
                parentItemsFaq.add(faqs.getQuestion_text());
            }
            for (FaqsModel faqsAnswer : faqsModels) {
                List<String> AnswerChild = new ArrayList<>();
                AnswerChild.add(faqsAnswer.getAnswer());
                childItemsFaq.put(faqsAnswer.getQuestion_text(), AnswerChild);
            }
//            List<String> parentItemsFaq = Arrays.asList("Why should i join this course and how will this help?", "Can the classes be downloaded", "How will i get my doubts answered");
//            Map<String, List<String>> childItemsFaq = new HashMap<>();
//            childItemsFaq.put("Why should i join this course and how will this help?", Arrays.asList("whole syllabus split into 4 trems"));
//            childItemsFaq.put("Can the classes be downloaded", Arrays.asList("Child 2"));
//            childItemsFaq.put("How will i get my doubts answered", Arrays.asList("Child 3"));
            FaqHeadAdapter faqHeadAdapter = new FaqHeadAdapter(getActivity(), parentItemsFaq, childItemsFaq);
            rv_content_faq.setAdapter(faqHeadAdapter);
        }
    }


}