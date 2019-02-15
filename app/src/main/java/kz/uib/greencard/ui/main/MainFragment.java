package kz.uib.greencard.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseAdapter;
import kz.uib.greencard.base.BaseFragment;
import kz.uib.greencard.base.DividerItemDecoration;
import kz.uib.greencard.base.EmptyRecyclerView;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.Category;
import kz.uib.greencard.repository.model.Combo;
import kz.uib.greencard.ui.OnFragmentInteractionListener;
import kz.uib.greencard.ui.combo.ComboFragment;
import kz.uib.greencard.ui.companies.CompanyListActivity;
import kz.uib.greencard.ui.menu.MenuActivity;
import kz.uib.greencard.ui.splash.SplashActivity;

public class MainFragment extends BaseFragment implements MainMvpContract.MainMvpView, BaseAdapter.OnItemClickListener, UltraPagerAdapter.ComboClickListener {

    private static final String CATEGORY_ID = "category_id";
    private static final String TITLE_CAT = "titleCat";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MainFragment";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private MainPresenter presenter;
    private CategoryAdapter adapter;
    private UltraPagerAdapter pagerAdapter;

    @BindView(R.id.viewpager)
    LoopingViewPager viewPager;

    @BindView(R.id.categoriesRecyclerView)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.indicator)
    PageIndicatorView indicatorView;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(@Nullable Bundle bundle) {
        DataManager manager = ((MvpApp)getBaseActivity().getApplicationContext()).getDataManager();
        presenter = new MainPresenter(manager);
        presenter.attachView(this);
        presenter.getAllOffers();

        mRecyclerView.setLayoutManager(new GridLayoutManager(getBaseActivity(), 3, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getBaseActivity()));
        adapter = new CategoryAdapter(new ArrayList(), getBaseActivity());
        adapter.attachToRecyclerView(mRecyclerView);
        adapter.setOnItemClickListener(this);
        initUltraViewPager();

    }

    @Override
    protected int getContentResource() {
        return R.layout.fragment_main;
    }

    private void initUltraViewPager(){
        pagerAdapter = new UltraPagerAdapter(getBaseActivity(), this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setIndicatorPageChangeListener(new LoopingViewPager.IndicatorPageChangeListener() {
            @Override
            public void onIndicatorProgress(int selectingPosition, float progress) {
            }
            @Override
            public void onIndicatorPageChange(int newIndicatorPosition) {
                indicatorView.setSelection(newIndicatorPosition);
            }
        });
    }

    @Override
    public void onItemClick(@NonNull Object item) {
        Category category = (Category)item;
        Intent intent = CompanyListActivity.getStartIntent(getBaseActivity());
        intent.putExtra(CATEGORY_ID, category.getId());
        intent.putExtra(TITLE_CAT, category.getName());
        startActivity(intent);
    }

    @Override
    public void onComboPageClicked(Combo combo) {
        onButtonPressed(combo.getId());
    }

    @Override
    public void updateCategoriesUI(List<Category> categories) {
        adapter.changeDataSet(categories);
    }

    @Override
    public void updateCombosUI(List<Combo> combos) {
        pagerAdapter.setItemList(combos);
        viewPager.reset();
        indicatorView.setCount(viewPager.getIndicatorCount());
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPager.resumeAutoScroll();
    }

    @Override
    public void updateEmptyUI() {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)viewPager.getLayoutParams();
        params.bottomToTop = 0;
        viewPager.requestLayout();
    }

    @Override
    public void setEmptyRView() {
        mRecyclerView.setEmptyView(null);

    }

    @Override
    public void openSplashActivity() {
        Intent intent = SplashActivity.getStartIntent(getBaseActivity());
        startActivity(intent);
        getBaseActivity().finish();
    }

    @Override
    public void onPause() {
        viewPager.pauseAutoScroll();
        super.onPause();
    }

    public void onButtonPressed(String id) {
        if (mListener != null) {
            mListener.onFragmentInteraction(id);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }
}
