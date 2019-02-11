package kz.uib.greencard.ui.companies.company;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseActivity;

public class CompanyActivity extends BaseActivity implements CompanyMvpContract.CompanyMvpView {

    private CompanyPresenter presenter;

    @Override
    protected void init(@Nullable Bundle state) {

    }

    @Override
    protected int getContentResource() {
        return R.layout.activity_company;
    }


    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
