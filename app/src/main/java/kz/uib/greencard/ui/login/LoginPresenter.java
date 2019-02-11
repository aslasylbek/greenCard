package kz.uib.greencard.ui.login;


import android.util.Log;

import kz.uib.greencard.CommonUtils;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.LoginResponse;

/**
 * Created by aslan on 17.05.2018.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    private static final String TAG = "LoginPresenter";
    public LoginPresenter(DataManager mDataManager) {
        super(mDataManager);
    }


    @Override
    public void setPrefsIfExist() {
    }

    @Override
    public void startLogin() {
        final User user = getMvpView().getUser();

        if(isAttached()) {

            if (user.getPassword().isEmpty()) {
                getMvpView().showSnackbar("Пустой пароль");
                return;
            }

            if (!user.isStudent()) {
                final String phone = user.getBarcodeOrPhone().subSequence(1, user.getBarcodeOrPhone().length()).toString();
                if (phone.length() != 11) {
                    getMvpView().showSnackbar("Неправильный формат");
                    return;
                }
                getMvpView().showLoading();
                getDataManager().userAuthentication(phone, user.getPassword(), new DataManager.GetLoginCallback() {
                    @Override
                    public void onSuccess(LoginResponse response) {
                        if(response.getSuccess()==0) {
                            getMvpView().showSnackbar(response.getMessage());
                        }
                        else {
                            getDataManager().putSessionId(response.getSessionId());
                            getDataManager().setOnaiPanEntered(response.getIsOnayPanEntered());
                            getDataManager().putUserId(response.getUserId());
                            getDataManager().putPassword(user.getPassword());
                            getDataManager().putBarcode(phone);
                            getDataManager().setStudent(user.isStudent());
                            getMvpView().openMainActivity();
                        }
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().showSnackbar("Произошла ошибка");
                        getMvpView().hideLoading();
                    }
                });


            } else if (user.isStudent()) {
                if (!CommonUtils.isBarcodeValid(user.getBarcodeOrPhone())) {
                    getMvpView().showSnackbar("Неправильный формат");
                    return;
                }

                getMvpView().showLoading();
                getDataManager().studentAuthentication(user.getBarcodeOrPhone(), user.getPassword(), new DataManager.GetLoginCallback() {
                    @Override
                    public void onSuccess(LoginResponse response) {
                        if(response.getSuccess()==0)
                            getMvpView().showSnackbar(response.getMessage());
                        else {
                            getDataManager().setOnaiPanEntered(response.getIsOnayPanEntered());
                            getDataManager().putSessionId(response.getSessionId());
                            getDataManager().putUserId(response.getUserId());
                            getDataManager().putBarcode(user.getBarcodeOrPhone());
                            getDataManager().putPassword(user.getPassword());
                            getDataManager().setStudent(user.isStudent());
                            getMvpView().openMainActivity();
                        }
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().showSnackbar("Произошла ошибка");
                        getMvpView().hideLoading();

                    }
                });
            }

        }


    }


}
