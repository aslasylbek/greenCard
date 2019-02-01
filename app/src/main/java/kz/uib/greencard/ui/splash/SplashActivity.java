package kz.uib.greencard.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseActivity;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.ui.login.LoginActivity;


public class SplashActivity extends BaseActivity implements SplashMvpView {


    SplashPresenter splashPresenter;


    public static Intent getStartIntent(Context context){
        return new Intent(context, SplashActivity.class);
    }

    @Override
    protected void init(@Nullable Bundle state) {
        DataManager manager = ((MvpApp)getApplication()).getDataManager();
        splashPresenter = new SplashPresenter(manager);
        splashPresenter.attachView(this);
        splashPresenter.decideNextActivty();
    }

    @Override
    protected int getContentResource() {
        return R.layout.activity_splash;
    }

    @Override
    public void openMainActivity() {
        /*Intent intent = Main2Activity.getStartIntent(this);
        startActivity(intent);*/
        finish();
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(this);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onDestroy() {
        splashPresenter.detachView();
        super.onDestroy();
    }
}
