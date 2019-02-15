package kz.uib.greencard.ui.combo;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseAdapter;
import kz.uib.greencard.base.BaseFragment;
import kz.uib.greencard.base.DividerItemDecoration;
import kz.uib.greencard.base.EmptyRecyclerView;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.ComboResponse;
import kz.uib.greencard.repository.model.Item;
import kz.uib.greencard.ui.bills.BillActivity;
import kz.uib.greencard.ui.bills.combo.BillComboAdapter;
import kz.uib.greencard.ui.companies.company.CompanyActivity;
import kz.uib.greencard.ui.splash.SplashActivity;


public class ComboFragment extends BaseFragment implements ComboMvpContract.ComboMvpView, BaseAdapter.OnItemClickListener {


    private static final String COMBO_ID = "combo_id";
    private static final String TAG = "ComboFragment";
    private static final String BILL_ID = "bill_id";
    private static final String IS_COMBO = "is_combo";

    private static final String COMPANY_ID = "company_id";

    private String comboId;
    private ComboPresenter presenter;
    private BillComboAdapter adapter;

    @BindView(R.id.iv_combo)
    ImageView mImage;

    @BindView(R.id.combo_box_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tv_description)
    TextView mDesc;

    @BindView(R.id.card_inside)
    LinearLayout rootView;

    @BindView(R.id.tv_price)
    TextView mPrice;

    @BindView(R.id.tv_date)
    TextView mDate;

    @BindView(R.id.comboRecyclerView)
    EmptyRecyclerView mRecyclerView;


    public static ComboFragment newInstance(String comboId) {
        ComboFragment fragment = new ComboFragment();
        Bundle args = new Bundle();
        args.putString(COMBO_ID, comboId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(@Nullable Bundle bundle) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getBaseActivity()));
        adapter = new BillComboAdapter(new ArrayList(), getBaseActivity());
        adapter.attachToRecyclerView(mRecyclerView);
        adapter.setOnItemClickListener(this);

        rootView.setVisibility(View.GONE);
        mToolbar.setTitle("");
        getBaseActivity().setSupportActionBar(mToolbar);
        if(getBaseActivity().getSupportActionBar()!=null)
            getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DataManager manager = ((MvpApp)getBaseActivity().getApplicationContext()).getDataManager();
        presenter = new ComboPresenter(manager);
        presenter.attachView(this);
        presenter.getComboById(comboId);
    }

    @Override
    protected int getContentResource() {
        return R.layout.fragment_combo;
    }

    @Override
    public void updateUI(ComboResponse response) {
        rootView.setVisibility(View.VISIBLE);
        Picasso.with(getBaseActivity())
                .load(response.getIconUrl())
                .resizeDimen(R.dimen.list_item_img_width, R.dimen.list_item_img_height)
                .centerCrop()
                .placeholder(R.drawable.image_place_holder)
                .into(mImage);

        mDesc.setText(response.getDescription());
        mPrice.setText(String.valueOf(response.getPrice())+"тг");
        mDate.setText(response.getEndDate());
        if (response.getItems().size()>0){
            adapter.changeDataSet(response.getItems());
        }
        mToolbar.setTitle(response.getName());
    }

    @Override
    public void onItemClick(@NonNull Object item) {
        Item company = (Item)item;
        Intent intent = CompanyActivity.getStartIntent(getBaseActivity());
        intent.putExtra(COMPANY_ID, company.getProviderCompanyId());
        startActivity(intent);
    }

    public void showDialog(){
        final Dialog dialogFragment = new Dialog(getBaseActivity());
        dialogFragment.setContentView(R.layout.purchase_dialog);
        dialogFragment.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogFragment.setCancelable(true);
        Button mBuy = dialogFragment.findViewById(R.id.btn_buy);
        Button mCancel = dialogFragment.findViewById(R.id.btn_cancel);
        final EditText mCode = dialogFragment.findViewById(R.id.et_code);
        mBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCode.getText().toString().equals("")){
                    mCode.setError("введите код");
                }
                else {
                    presenter.purchaseCombo(comboId, mCode.getText().toString());
                    dialogFragment.dismiss();
                }
            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.cancel();
            }
        });

        dialogFragment.show();
    }

    @Override
    public void afterComboBuy(String historyId) {
        Intent intent = BillActivity.getStartIntent(getBaseActivity());
        intent.putExtra(BILL_ID, historyId);
        intent.putExtra(IS_COMBO, "1");
        startActivity(intent);
    }

    @OnClick(R.id.btnBuy)
    public void onComboBuyClick(){
        showDialog();
    }

    @Override
    public void openSplashActivity() {
        Intent intent = SplashActivity.getStartIntent(getBaseActivity());
        startActivity(intent);
        getBaseActivity().finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            comboId = getArguments().getString(COMBO_ID);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
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
