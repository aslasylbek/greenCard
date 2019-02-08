package kz.uib.greencard.ui.history;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseFragment;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.Monthe;
import kz.uib.greencard.ui.bills.BillActivity;
import kz.uib.greencard.ui.history.adapter.ItemRecyclerViewAdapter;
import kz.uib.greencard.ui.history.adapter.SectionModel;
import kz.uib.greencard.ui.history.adapter.SectionRecyclerViewAdapter;
import kz.uib.greencard.ui.splash.SplashActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends BaseFragment implements HistoryMvpContract.HistoryMvpView, TabLayout.OnTabSelectedListener, ItemRecyclerViewAdapter.HistoryClickListener {

    private static final String TAG = "HistoryFragment";
    private static final String BILL_ID = "bill_id";
    private static final String IS_COMBO = "is_combo";
    private HistoryPresenter presenter;
    private SectionRecyclerViewAdapter adapter;
    private List<SectionModel> sectionModelArrayList;

    @BindView(R.id.historyTabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.historyRView)
    RecyclerView mRecyclerView;

    public static HistoryFragment newInstance() {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(@Nullable Bundle bundle) {
        mTabLayout.addOnTabSelectedListener(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        populateRecyclerView();
        DataManager manager = ((MvpApp)getBaseActivity().getApplicationContext()).getDataManager();
        presenter = new HistoryPresenter(manager);
        presenter.attachView(this);
        presenter.getRecentHistory();

    }

    private void populateRecyclerView() {
        adapter = new SectionRecyclerViewAdapter(getBaseActivity(),this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected int getContentResource() {
        return R.layout.fragment_history;
    }

    @Override
    public void updateUI(List<Monthe> response) {
        sectionModelArrayList = new ArrayList<>();
        for (int i = 0; i < response.size(); i++) {
            sectionModelArrayList.add(new SectionModel( response.get(i).getName(), response.get(i).getHistories()));
        }
        adapter.changeData(sectionModelArrayList);
    }

    @Override
    public void onItemClick(ItemRecyclerViewAdapter.MyTag data) {
        Intent intent = BillActivity.getStartIntent(getBaseActivity());
        intent.putExtra(BILL_ID, data.getId());
        intent.putExtra(IS_COMBO, data.getIsCombo());
        startActivity(intent);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if(tab.getPosition()==0){
            presenter.getRecentHistory();
        }
        else{
            presenter.getAllHistory();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        //nothing
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        //nothing
    }

    @Override
    public void openSplashActivity() {
        Intent intent = SplashActivity.getStartIntent(getBaseActivity());
        startActivity(intent);
        getBaseActivity().finish();
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
