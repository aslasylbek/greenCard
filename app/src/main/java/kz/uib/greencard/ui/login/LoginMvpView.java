package kz.uib.greencard.ui.login;


import kz.uib.greencard.base.MvpView;

/**
 * Created by aslan on 17.05.2018.
 */

public interface LoginMvpView extends MvpView {

    void openMainActivity();
    void onLoginButtonClick();
    User getUser();
    void showToast(int resId);
    void wrongLoginOrPassword();

    void setLoginPassword(String barcode, String password);

}
