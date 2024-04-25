package in.testpress.testpress.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import in.testpress.models.greendao.Content;
import in.testpress.testpress.R;
import in.testpress.testpress.fragments.AllClassesFrag;
import in.testpress.testpress.fragments.AnnouncementFrag;
import in.testpress.testpress.fragments.DescriptionFrag;
import in.testpress.testpress.fragments.PaymentSummaryFrag;

public class CoureContentActivity extends AppCompatActivity {
    TabLayout tabLayout;
    RecyclerView rv_other_details, rv_content_faq, rv_batch_includes;
    FrameLayout fragmentContainer;
    ImageView img_v_nav;
    private Content mContent;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coure_content);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.txt_btn_back));
        }
        initViews();
    }

    private void initViews() {
        mContext=CoureContentActivity.this;
        rv_batch_includes = findViewById(R.id.rv_batch_includes);
        img_v_nav = findViewById(R.id.img_v_nav);
        fragmentContainer = findViewById(R.id.fragmentContainer);
        img_v_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rv_other_details = findViewById(R.id.rv_other_details);
        rv_content_faq = findViewById(R.id.rv_content_faq);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setInlineLabel(true);
        String[] tabTitles = {
                "Description",
                "All Classes",
                "Announcement"
        };
        int[] tabIcons = {R.drawable.ic_description_red, R.drawable.ic_allclasses_grey, R.drawable.ic_announcement_grey};
        for (int i = 0; i < tabTitles.length; i++) {
            View tabView = getLayoutInflater().inflate(R.layout.content_tab_layout, null);
            // Set icon and title
            ImageView tabIcon = tabView.findViewById(R.id.tab_icon);
            tabIcon.setImageResource(tabIcons[i]);
            TextView tabTitle = tabView.findViewById(R.id.tab_title);
            tabTitle.setText(tabTitles[i]);
            if (i > 0) {
                tabTitle.setTextColor(getColor(R.color.txt_para_colorcode));
            }
            // Add tab to TabLayout
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setCustomView(tabView);
            tabLayout.addTab(tab);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View tabView = tab.getCustomView();
                ImageView tabIcon = tabView.findViewById(R.id.tab_icon);
                TextView tabText = tabView.findViewById(R.id.tab_title);
                tabIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.txt_dark_red), PorterDuff.Mode.SRC_IN);
                tabText.setTextColor(ContextCompat.getColor(mContext, R.color.txt_dark_red));
                Fragment fragment = null;
                int position = tab.getPosition();
                if (position == 0) {
//                    updateTab(0);
                    fragment = new DescriptionFrag();
                } else if (position == 1) {
                    fragment = new AllClassesFrag();
                } else if (position == 2) {
                    fragment = new AnnouncementFrag();
                }
                updateDisplay(fragment);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View tabView = tab.getCustomView();
                ImageView tabIcon = tabView.findViewById(R.id.tab_icon);
                TextView tabText = tabView.findViewById(R.id.tab_title);
                tabIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.txt_para_colorcode), PorterDuff.Mode.SRC_IN);
                tabText.setTextColor(ContextCompat.getColor(mContext, R.color.txt_para_colorcode));
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        updateDisplay(new DescriptionFrag());
    }

    public void updateDisplay(Fragment fragment) {
        if(fragment.getClass().equals(PaymentSummaryFrag.class)){
            tabLayout.setVisibility(View.GONE);
        }else{
            tabLayout.setVisibility(View.VISIBLE);
        }
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        FragmentTransaction newFragmentTag = ft.replace(R.id.fragmentContainer, fragment, "NewFragmentTag");
        ft.commit();
    }

    private void updateTab(int tabPos) {
//        String[] tabTitles = {
//                "Description",
//                "All Classes",
//                "Announcement"
//        };
//        int[] tabIcons = {R.drawable.ic_description_red, R.drawable.ic_allclasses_grey, R.drawable.ic_announcement_grey};
//        for (int i = 0; i < 3; i++) {
//            View tabView = getLayoutInflater().inflate(R.layout.content_tab_layout, null);
//            tabLayout.removeAllTabs();
//            // Set icon and title
//            if (i == tabPos) {
//                ImageView tabIcon = tabView.findViewById(R.id.tab_icon);
//                tabIcon.setImageResource(tabIcons[i]);
//                TextView tabTitle = tabView.findViewById(R.id.tab_title);
//                tabTitle.setText(tabTitles[i]);
//                tabTitle.setTextColor(getColor(R.color.txt_para_colorcode));
//                tabLayout.getTabAt(i).setCustomView(tabView);
//            }else{
//                ImageView tabIcon = tabView.findViewById(R.id.tab_icon);
//                tabIcon.setImageResource(tabIcons[i]);
//                TextView tabTitle = tabView.findViewById(R.id.tab_title);
//                tabTitle.setText(tabTitles[i]);
//                tabTitle.setTextColor(getColor(R.color.txt_para_colorcode));
//                tabLayout.getTabAt(i).setCustomView(tabView);
//            }
    }
}
