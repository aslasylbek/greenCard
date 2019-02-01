package kz.uib.greencard.ui.login;


import kz.uib.greencard.R;
import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;

/**
 * Created by aslan on 17.05.2018.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    public LoginPresenter(DataManager mDataManager) {
        super(mDataManager);
    }


    @Override
    public void setPrefsIfExist() {
        /*getMvpView().showLoading();
        getMvpView().setLoginPassword(getDataManager().getBarcode(), getDataManager().getPrefPassword());
        getMvpView().hideLoading();*/
    }

    @Override
    public void startLogin() {

    }


}
