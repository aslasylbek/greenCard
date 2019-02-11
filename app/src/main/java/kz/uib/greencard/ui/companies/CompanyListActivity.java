package kz.uib.greencard.ui.companies;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseActivity;
import kz.uib.greencard.base.BaseAdapter;
import kz.uib.greencard.base.DividerItemDecoration;
import kz.uib.greencard.base.EmptyRecyclerView;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.Company;
import kz.uib.greencard.ui.splash.SplashActivity;

public class CompanyListActivity extends BaseActivity implements CompanyListMvpContract.CompanyListMvpView , BaseAdapter.OnItemClickListener {

    private static final String CATEGORY_ID = "category_id";
    private static final String TITLE_CAT = "titleCat";

    private CompanyListPresenter presenter;
    private CompanyAdapter adapter;
    private String category_id;

    @BindView(R.id.rvCompany)
    EmptyRecyclerView mRecyclerView;

    public static Intent getStartIntent(Context context){
        return new Intent(context, CompanyListActivity.class);
    }

    @Override
    protected void init(@Nullable Bundle state) {
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        String title = getIntent().getStringExtra(TITLE_CAT);
        setTitle(title);
        category_id = getIntent().getStringExtra(CATEGORY_ID);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        adapter = new CompanyAdapter(new ArrayList(), this);
        adapter.attachToRecyclerView(mRecyclerView);
        adapter.setOnItemClickListener(this);

        DataManager manager = ((MvpApp)getApplicationContext()).getDataManager();
        presenter = new CompanyListPresenter(manager);
        presenter.attachView(this);
        presenter.getCompaniesById(category_id);
    }

    @Override
    public void onItemClick(@NonNull Object item) {
        Company company = (Company)item;

    }

    @Override
    public void updateUI(List<Company> companies) {
        adapter.changeDataSet(companies);
    }

    @Override
    public void openSplashActivity() {
        Intent intent = SplashActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    protected int getContentResource() {
        return R.layout.activity_company_list;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
