package in.testpress.testpress.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import in.testpress.testpress.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentSummaryFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentSummaryFrag extends android.app.Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private View mView;
    public PaymentSummaryFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentSummaryFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentSummaryFrag newInstance(String param1, String param2) {
        PaymentSummaryFrag fragment = new PaymentSummaryFrag();
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
        mView= inflater.inflate(R.layout.fragment_payment_summary, container, false);
        initViews();
        return mView;
    }

    private void initViews() {

    }
}