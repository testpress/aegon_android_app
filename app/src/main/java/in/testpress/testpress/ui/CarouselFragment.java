package in.testpress.testpress.ui;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.testpress.testpress.R;

import com.astuetz.PagerSlidingTabStrip;
import com.viewpagerindicator.TitlePageIndicator;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Fragment which houses the View pager.
 */
public class CarouselFragment extends Fragment {

    @InjectView(R.id.tpi_header)
    protected PagerSlidingTabStrip indicator;

    @InjectView(R.id.vp_pages)
    protected ViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_carousel, container, false);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ButterKnife.inject(this, getView());

        pager.setAdapter(new TestpressPagerAdapter(getResources(), getChildFragmentManager()));
        indicator.setViewPager(pager);
        indicator.setIndicatorColor(Color.parseColor("#3598db"));
        Bundle data = getArguments();
        if (data != null) {
            pager.setCurrentItem(Integer.parseInt(data.getString("currentItem")));
        } else {
            pager.setCurrentItem(0);

        }
    }
}