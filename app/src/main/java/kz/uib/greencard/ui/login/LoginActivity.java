package kz.uib.greencard.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.redmadrobot.inputmask.MaskedTextChangedListener;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseActivity;
import kz.uib.greencard.repository.DataManager;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginMvpView  {

    private static final String TAG = "LoginActivity";

    private LoginPresenter loginPresenter;
    private int beforeLength;
    @BindView(R.id.email)
    EditText editTextEmail;
    @BindView(R.id.password)
    EditText editTextPassword;
    @BindView(R.id.email_sign_in_button)
    Button button;

    @BindView(R.id.isStudent)
    Switch isStudent;

    @BindView(R.id.emailLayout)
    TextInputLayout emailInputLayout;

    public static Intent getStartIntent(Context context){
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void init(@Nullable Bundle state) {
        DataManager dataManager = ((MvpApp)getApplication()).getDataManager();
        loginPresenter = new LoginPresenter(dataManager);
        loginPresenter.attachView(LoginActivity.this);
        loginPresenter.setPrefsIfExist();


    }


    @Override
    public void setLoginPassword(String barcode, String password) {
        if (barcode!=null)
            editTextEmail.setText(barcode);
        if (password!=null){
            editTextPassword.setText(password);
        }
    }

    @OnCheckedChanged(R.id.isStudent)
    public void onCheckChange(boolean isStudent){
        if(isStudent)
            emailInputLayout.setHint("Баркод");
        else{
            emailInputLayout.setHint("Телефон");
        }
    }

    @Override
    protected int getContentResource() {
        return R.layout.activity_login;
    }

    @Override
    public void openMainActivity() {
        /*Intent intent = Main2Activity.getStartIntent(this);
        startActivity(intent);
        finish();*/

    }

    @OnClick(R.id.email_sign_in_button)
    public void onLoginButtonClick() {
        loginPresenter.startLogin();

    }

    @Override
    public User getUser() {
        String barcodeId = editTextEmail.getText().toString();
        String passwordId = editTextPassword.getText().toString();
        return new User(barcodeId, passwordId , false, false);
    }

    @Override
    public void showToast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void wrongLoginOrPassword() {
        /*editTextEmail.setError(getString(R.string.error_invalid_email));
        editTextPassword.setError(getString(R.string.error_incorrect_password));*/
    }

    @Override
    protected void onDestroy() {
        loginPresenter.detachView();
        super.onDestroy();
    }


}

