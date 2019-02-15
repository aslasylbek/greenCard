package kz.uib.greencard.ui.bills.combo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseAdapter;
import kz.uib.greencard.base.BaseFragment;
import kz.uib.greencard.base.DividerItemDecoration;
import kz.uib.greencard.base.EmptyRecyclerView;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.ComboResponse;
import kz.uib.greencard.ui.splash.SplashActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillComboFragment extends BaseFragment implements BillComboMvpContract.BillComboMvpView, BaseAdapter.OnItemClickListener {

    private static final String COMBO_ID = "combo_id";
    private BillComboPresenter presenter;
    private String billId;
    private BillComboAdapter adapter;

    @BindView(R.id.comboRecyclerView)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.tv_description)
    TextView tvDescription;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_final)
    TextView tvPrice;

    @BindView(R.id.mImage)
    ImageView mImage;

    @BindView(R.id.combo_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.card_inside)
    LinearLayout linearLayout;

    public static BillComboFragment newInstance(String billId) {
        Bundle args = new Bundle();
        BillComboFragment fragment = new BillComboFragment();
        args.putString(COMBO_ID, billId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(@Nullable Bundle bundle) {
        if(getArguments()!=null)
            billId = getArguments().getString(COMBO_ID);

        mToolbar.setTitle("Детали оплаты");
        getBaseActivity().setSupportActionBar(mToolbar);
        linearLayout.setVisibility(View.INVISIBLE);
        if (getBaseActivity().getSupportActionBar()!=null){
            getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getBaseActivity()));
        adapter = new BillComboAdapter(new ArrayList(), getBaseActivity());
        adapter.attachToRecyclerView(mRecyclerView);
        adapter.setOnItemClickListener(this);
        DataManager manager = ((MvpApp)getBaseActivity().getApplicationContext()).getDataManager();
        presenter = new BillComboPresenter(manager);
        presenter.attachView(this);
        presenter.getComboBillById(billId);
    }

    @Override
    public void onItemClick(@NonNull Object item) {

    }

    @Override
    protected int getContentResource() {
        return R.layout.fragment_bill_combo;
    }

    @Override
    public void updateUI(ComboResponse response) {
        linearLayout.setVisibility(View.VISIBLE);
        Picasso.with(getBaseActivity())
                .load(response.getIconUrl())
                .resizeDimen(R.dimen.list_item_img_width, R.dimen.list_item_img_height)
                .centerInside()
                .placeholder(R.drawable.image_place_holder)
                .into(mImage);

        tvDate.setText(response.getEndDate());
        tvDescription.setText(response.getDescription());
        tvTitle.setText(response.getName());
        tvPrice.setText(response.getPrice()+"тг");
        if(response.getItems().size()>0)
            adapter.changeDataSet(response.getItems());
    }

    @Override
    public void openSplashActivity() {
        Intent intent = SplashActivity.getStartIntent(getBaseActivity());
        startActivity(intent);
        getBaseActivity().finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getBaseActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }
}
