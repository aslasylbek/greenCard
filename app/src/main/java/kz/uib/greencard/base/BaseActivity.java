package kz.uib.greencard.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import kz.uib.greencard.R;
import kz.uib.greencard.base.general.LoadingDialog;
import kz.uib.greencard.base.general.LoadingView;
import kz.uib.greencard.ui.splash.SplashActivity;

/**
 * Created by aslan on 17.05.2018.
 */

/*
@Todo potom dopishu
 */
public abstract class BaseActivity extends AppCompatActivity implements MvpView, BaseFragment.Callback {
    private LoadingView mLoadingView;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentResource());
        mUnbinder = ButterKnife.bind(this);
        init(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    protected abstract void init(@Nullable Bundle state);

    @LayoutRes
    protected abstract int getContentResource();

    @Override
    public void showLoading() {
        hideLoading();
        if (mLoadingView==null){
            mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        }
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        if (mLoadingView!=null)
            mLoadingView.hideLoading();
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void showToastMessage(int resId) {
        Toast.makeText(getApplicationContext(),
                getString(resId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.whiteColor));
        snackbar.show();
    }

   /* @Override
    public void openSplashActivity() {
        Intent intent = SplashActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }*/

    @Override
    public void onFragmentAttached(){

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    protected void onDestroy() {
        if (mUnbinder!=null){
            mUnbinder.unbind();
        }
        super.onDestroy();
    }



}
