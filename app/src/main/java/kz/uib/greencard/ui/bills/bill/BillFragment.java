package kz.uib.greencard.ui.bills.bill;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseFragment;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.History;
import kz.uib.greencard.ui.bills.combo.BillComboFragment;
import kz.uib.greencard.ui.splash.SplashActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillFragment extends BaseFragment implements BillBaseMvpContract.BillBaseMvpView {

    private BillBasePresenter presenter;
    private static final String BILL_ID = "BillFragment";
    private String billId;

    @BindView(R.id.imageIcon)
    ImageView mIcon;

    @BindView(R.id.mSumma)
    TextView mSumma;

    @BindView(R.id.mSale)
    TextView mSale;

    @BindView(R.id.tv_final)
    TextView tvFinal;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.card_inside)
    LinearLayout card_inside;

    @BindView(R.id.bill_toolbar)
    Toolbar mBillToolbar;

    public static BillFragment newInstance(String billId) {
        Bundle args = new Bundle();
        BillFragment fragment = new BillFragment();
        args.putString(BILL_ID, billId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(@Nullable Bundle bundle) {
        if(getArguments()!=null)
            billId = getArguments().getString(BILL_ID);
        mBillToolbar.setTitle("Детали оплаты");
        getBaseActivity().setSupportActionBar(mBillToolbar);
        if (getBaseActivity().getSupportActionBar()!=null){
            getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        DataManager manager = ((MvpApp)getBaseActivity().getApplicationContext()).getDataManager();
        presenter = new BillBasePresenter(manager);
        presenter.attachView(this);
        card_inside.setVisibility(View.INVISIBLE);
        presenter.getBillById(billId);
    }

    @Override
    protected int getContentResource() {
        return R.layout.fragment_bill;
    }

    @Override
    public void openSplashActivity() {
        Intent intent = SplashActivity.getStartIntent(getBaseActivity());
        startActivity(intent);
        getBaseActivity().finish();
    }

    @Override
    public void updateUI(History history) {
        card_inside.setVisibility(View.VISIBLE);
        mSumma.setText(history.getAmount()+"тг");
        mSale.setText(history.getSalePercent()+"%");
        tvFinal.setText("-"+history.getAmountFinal()+"тг");
        tvDate.setText(history.getDate());
        Picasso.with(getBaseActivity())
                .load(history.getCompanyIconUrl())
                .resizeDimen(R.dimen.list_item_img_width, R.dimen.list_item_img_height)
                .centerInside()
                .placeholder(R.drawable.image_place_holder)
                .into(mIcon);
        getBaseActivity().setTitle(history.getCompanyName());
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
