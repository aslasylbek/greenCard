package kz.uib.greencard.ui.login;


import kz.uib.greencard.base.MvpPresenter;

/**
 * Created by aslan on 17.05.2018.
 */

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void startLogin();

    void setPrefsIfExist();

}
