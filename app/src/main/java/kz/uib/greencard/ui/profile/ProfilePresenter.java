package kz.uib.greencard.ui.profile;

import android.util.Log;

import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.PostResponse;
import kz.uib.greencard.repository.model.ProfileResponse;

public class ProfilePresenter<V extends ProfileMvpContract.ProfileMvpView> extends BasePresenter<V> implements ProfileMvpContract.ProfileMvpPresenter<V> {

    public ProfilePresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void getUserInformation() {

        if(!isAttached())
            return;

        getMvpView().showLoading();
        getDataManager().getUserInformation(new DataManager.GetProfileCallback() {
            @Override
            public void onSuccess(ProfileResponse response) {
                if(response.getSuccess()==0){
                    if(response.getErrorCode()==1)
                        getMvpView().openSplashActivity();
                    getMvpView().showSnackbar(response.getMessage());
                }
                else {
                    if (isAttached())
                        getMvpView().updateUI(response);
                }
                if (isAttached())
                getMvpView().hideLoading();
            }

            @Override
            public void onError(String t) {
                getMvpView().hideLoading();
            }
        });
    }

    @Override
    public void changePaymentFields() {
        ProfileResponse payment = getMvpView().getPaymentInfo();
        if(!payment.getIin().isEmpty() && !payment.getOnayPan().isEmpty()){
            getMvpView().showLoading();
            getDataManager().setPaymentInformation(payment.getIin(), payment.getOnayPan(), new DataManager.GetPostCallback() {
                @Override
                public void onSuccess(PostResponse response) {
                    if(response.getSuccess()==0){
                        if (response.getCode()==1)
                            getMvpView().openSplashActivity();
                        getMvpView().showSnackbar(response.getMessage());
                    }else getMvpView().showSnackbar("Данные успешно изменены!");
                    getMvpView().hideLoading();
                }

                @Override
                public void onError(String msg) {
                    getMvpView().hideLoading();
                }
            });
        } else {
            getMvpView().showSnackbar("Заполните все поля");
        }
    }

    @Override
    public void changeInformation() {
        ProfileResponse info = getMvpView().getBaseInfo();
        if(info.getFirstname().isEmpty() || info.getLastname().isEmpty()){
            getMvpView().showSnackbar("Заполните все поля");
        }
        else {
            getMvpView().showLoading();
            getDataManager().setUserInformation(info.getLastname(), info.getFirstname(), info.getInstagram(), new DataManager.GetPostCallback() {
                @Override
                public void onSuccess(PostResponse response) {
                    if(response.getSuccess()==0){
                        if (response.getCode()==1)
                            getMvpView().openSplashActivity();
                        getMvpView().showSnackbar(response.getMessage());
                    }
                    else getMvpView().showSnackbar("Данные успешно изменены!");
                    getMvpView().hideLoading();
                }

                @Override
                public void onError(String msg) {
                    getMvpView().hideLoading();
                }
            });
        }

    }

    @Override
    public void resetPassword(String oldPass, String newPassWord, String confirmPass) {
        if(oldPass.isEmpty() || newPassWord.isEmpty() || confirmPass.isEmpty()){
            getMvpView().showSnackbar("Заполните все поля");
            return;
        }
        if(newPassWord.equals(confirmPass)){
            getMvpView().showLoading();
            getDataManager().resetUserPassword(newPassWord, oldPass, new DataManager.GetPostCallback() {
                @Override
                public void onSuccess(PostResponse response) {
                    if(response.getSuccess()==0){
                        if(response.getCode()==1){
                            getMvpView().openSplashActivity();
                        }
                        getMvpView().showSnackbar(response.getMessage());
                    }
                    else getMvpView().showSnackbar("Пароль успешно изменен");
                    getMvpView().hideLoading();
                }

                @Override
                public void onError(String msg) {
                    getMvpView().hideLoading();
                }
            });
        }
        else {
            getMvpView().clearPass();
            getMvpView().showSnackbar("Пароли не совпадают");
        }
    }

    @Override
    public void onLogout() {
        getMvpView().openSplashActivity();
    }
}
