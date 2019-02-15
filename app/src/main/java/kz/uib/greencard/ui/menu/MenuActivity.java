package kz.uib.greencard.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseActivity;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.ui.OnFragmentInteractionListener;
import kz.uib.greencard.ui.combo.ComboFragment;
import kz.uib.greencard.ui.history.HistoryFragment;
import kz.uib.greencard.ui.main.MainFragment;
import kz.uib.greencard.ui.profile.ProfileFragment;
import kz.uib.greencard.ui.qr.QRFragment;

public class MenuActivity extends BaseActivity implements MenuMvpContract.MenuMvpView, OnFragmentInteractionListener {

    private static final String TAG = "MenuActivity";
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

    @BindView(R.id.menuFrame)
    FrameLayout mMenuFrame;

    private MenuPresenter presenter;


    public static Intent getMenuIntent(Context context){
        return new Intent(context, MenuActivity.class);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.app_home:
                    fragmentTransaction.replace(R.id.menuFrame, MainFragment.newInstance()).commit();
                    return true;
                case R.id.app_qr:
                    fragmentTransaction.replace(R.id.menuFrame, QRFragment.newInstance()).commit();
                    return true;
                case R.id.app_history:
                    fragmentTransaction.replace(R.id.menuFrame, HistoryFragment.newInstance()).commit();
                    return true;
                case R.id.app_profile:
                    fragmentTransaction.replace(R.id.menuFrame, ProfileFragment.newInstance()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onFragmentInteraction(String id) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.menuFrame, ComboFragment.newInstance(id));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void init(@Nullable Bundle state) {

        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        DataManager manager = ((MvpApp)getApplicationContext()).getDataManager();
        presenter = new MenuPresenter(manager);
        presenter.attachView(this);
        presenter.initActivity();
    }

    @Override
    public void attachMainFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.menuFrame, MainFragment.newInstance()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    protected int getContentResource() {
        return R.layout.activity_menu;
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
