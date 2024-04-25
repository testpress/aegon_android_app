package in.testpress.testpress.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.testpress.testpress.R;
import in.testpress.testpress.adapters.AllClassesSubAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllClassesFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllClassesFrag extends android.app.Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RecyclerView recycle_view_allclass_sub;
    private View mView;

    public AllClassesFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllClassesFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static AllClassesFrag newInstance(String param1, String param2) {
        AllClassesFrag fragment = new AllClassesFrag();
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
        mView = inflater.inflate(R.layout.fragment_all_classes, container, false);
        initViews();
        return mView;
    }

    private void initViews() {
        recycle_view_allclass_sub=mView.findViewById(R.id.recycle_view_allclass_sub);
        int spanCount = 4; // 2 columns
        int spacing = 50; // 50px spacing
        boolean includeEdge = true;
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), spanCount);
        recycle_view_allclass_sub.setLayoutManager(layoutManager);
        List<String> titles = new ArrayList<>();
        titles.add("Notices");
        titles.add("Physics");
        titles.add("Chemistry");
        titles.add("Botany");
        titles.add("Zoology");
        AllClassesSubAdapter allClassesSubAdapter = new AllClassesSubAdapter(getActivity(), titles);
        recycle_view_allclass_sub.setAdapter(allClassesSubAdapter);
    }

}