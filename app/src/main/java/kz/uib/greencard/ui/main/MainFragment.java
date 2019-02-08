package kz.uib.greencard.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmall.ultraviewpager.UltraViewPager;

import java.util.List;

import butterknife.BindView;
import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseFragment;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.Category;
import kz.uib.greencard.repository.model.Combo;
import kz.uib.greencard.ui.splash.SplashActivity;

public class MainFragment extends BaseFragment implements MainMvpContract.MainMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MainFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MainPresenter presenter;

    @BindView(R.id.ultra_viewpager)
    UltraViewPager viewPager;

    @BindView(R.id.categoriesRecyclerView)
    RecyclerView recyclerView;


    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
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
    protected void init(@Nullable Bundle bundle) {
        DataManager manager = ((MvpApp)getBaseActivity().getApplicationContext()).getDataManager();
        presenter = new MainPresenter(manager);
        presenter.attachView(this);
        presenter.getAllOffers();

        /*viewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
//initialize UltraPagerAdapter，and add child view to UltraViewPager
        PagerAdapter adapter = new UltraPagerAdapter(false);
        viewPager.setAdapter(adapter);

//initialize built-in indicator
        viewPager.initIndicator();
//set style of indicators
        viewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.GREEN)
                .setNormalColor(Color.WHITE)
                .setRadius((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
//set the alignment
        viewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
//construct built-in indicator, and add it to  UltraViewPager
        viewPager.getIndicator().build();

//set an infinite loop
        viewPager.setInfiniteLoop(true);
//enable auto-scroll mode
        viewPager.setAutoScroll(2000);*/

    }

    @Override
    protected int getContentResource() {
        return R.layout.fragment_main;
    }

    @Override
    public void updateCategoriesUI(List<Category> categories) {
        Log.e(TAG, "updateCategoriesUI: "+categories);
    }

    @Override
    public void updateCombosUI(List<Combo> combos) {
        Log.e(TAG, "updateCombosUI: "+combos );
    }

    @Override
    public void openSplashActivity() {
        Intent intent = SplashActivity.getStartIntent(getBaseActivity());
        startActivity(intent);
        getBaseActivity().finish();
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }
}
