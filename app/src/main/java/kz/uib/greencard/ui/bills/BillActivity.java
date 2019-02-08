package kz.uib.greencard.ui.bills;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseActivity;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.ui.bills.bill.BillFragment;
import kz.uib.greencard.ui.bills.combo.BillComboFragment;
import kz.uib.greencard.ui.menu.MenuActivity;

public class BillActivity extends BaseActivity implements BillMvpContract.BillMvpView {

    private BillPresenter presenter;
    private String billId;
    private String isCombo;
    private static final String BILL_ID = "bill_id";
    private static final String IS_COMBO = "is_combo";

  /*  @BindView(R.id.toolbar)
    Toolbar mBillToolbar;*/


    public static Intent getStartIntent(Context context){
        return new Intent(context, BillActivity.class);
    }

    @Override
    protected void init(@Nullable Bundle state) {
        billId = getIntent().getStringExtra(BILL_ID);
        isCombo = getIntent().getStringExtra(IS_COMBO);

        DataManager manager = ((MvpApp)getApplicationContext()).getDataManager();
        presenter = new BillPresenter(manager);
        presenter.attachView(this);
        presenter.decideNextFragment(isCombo);
    }

    @Override
    protected int getContentResource() {
        return R.layout.activity_bill;
    }

    @Override
    public void replaceWithBillFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.billFrame, BillFragment.newInstance(billId)).commit();
    }

    @Override
    public void replaceWithComboFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.billFrame, BillComboFragment.newInstance(billId)).commit();
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
