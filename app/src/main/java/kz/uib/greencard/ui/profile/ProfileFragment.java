package kz.uib.greencard.ui.profile;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseFragment;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.ProfileResponse;
import kz.uib.greencard.ui.splash.SplashActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment implements ProfileMvpContract.ProfileMvpView, DialogInterface.OnClickListener {

    private ProfilePresenter presenter;

    @BindView(R.id.mName)
    EditText mName;
    @BindView(R.id.mSurname)
    EditText mSurname;
    @BindView(R.id.mInstagram)
    EditText mInstagramm;
    @BindView(R.id.mPhone)
    EditText mPhone;
    @BindView(R.id.mPoints)
    EditText mPoints;
    @BindView(R.id.mClass)
    EditText mClass;

    @BindView(R.id.mIin)
    EditText mIin;
    @BindView(R.id.mOnay)
    EditText mOnay;

    @BindView(R.id.mOldPass)
    EditText mOldPass;
    @BindView(R.id.mNewPass)
    EditText mNewPass;
    @BindView(R.id.mConfirmPass)
    EditText mConfirmPass;

    @BindView(R.id.parentLayout)
    ScrollView mParentLayout;



    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(@Nullable Bundle bundle) {
        mParentLayout.setVisibility(View.GONE);
        DataManager manager = ((MvpApp)getBaseActivity().getApplicationContext()).getDataManager();
        presenter = new ProfilePresenter(manager);
        presenter.attachView(this);
        presenter.getUserInformation();
    }

    @Override
    public void updateUI(ProfileResponse response) {
        mParentLayout.setVisibility(View.VISIBLE);
        mName.setText(response.getFirstname());
        mSurname.setText(response.getLastname());
        mInstagramm.setText(response.getInstagram());
        mPhone.setText(response.getPhone());
        mPoints.setText(response.getPoint());
        mClass.setText(response.getClass_());
        mIin.setText(response.getIin());
        mOnay.setText(response.getOnayPan());
    }

    @Override
    protected int getContentResource() {
        return R.layout.fragment_profile;
    }

    @Override
    public ProfileResponse getBaseInfo() {
        String name = mName.getText().toString();
        String surname = mSurname.getText().toString();
        String instagramm = mInstagramm.getText().toString();
        return new ProfileResponse(name, surname, instagramm);
    }

    @Override
    public ProfileResponse getPaymentInfo() {
        String iin = mIin.getText().toString();
        String onay = mOnay.getText().toString();
        return new ProfileResponse(iin, onay);
    }

    @Override
    public void clearPass() {
        mNewPass.setText("");
        mConfirmPass.setText("");
    }

    @Override
    public void openSplashActivity() {
        Intent intent = SplashActivity.getStartIntent(getBaseActivity());
        startActivity(intent);
        getBaseActivity().finish();

    }

    @OnClick(R.id.btnLogout)
    public void onLogout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getBaseActivity());
        builder.setTitle("Вы точно хотете выйти?")
                .setCancelable(true)
                .setNegativeButton("Cancel", this)
                .setPositiveButton("OK", this);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which==-2){
            dialog.cancel();
        }
        else {
            presenter.onLogout();
        }
    }

    @OnClick(R.id.btnResetPass)
    public void onClickResetPass(){
        presenter.resetPassword(
                mOldPass.getText().toString(),
                mNewPass.getText().toString(),
                mConfirmPass.getText().toString());
    }

    @OnClick(R.id.btnPayment)
    public void onClickPaymentChange(){
        presenter.changePaymentFields();
    }

    @OnClick(R.id.btnSaveInfo)
    public void onClickInfoChange(){
        presenter.changeInformation();
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }
}
