package kz.uib.greencard.ui.profile;

import kz.uib.greencard.base.MvpPresenter;
import kz.uib.greencard.base.MvpView;
import kz.uib.greencard.repository.model.ProfileResponse;

public interface ProfileMvpContract {

    interface ProfileMvpView extends MvpView{
        void updateUI(ProfileResponse response);
        ProfileResponse getBaseInfo();
        ProfileResponse getPaymentInfo();
        void clearPass();
        void openSplashActivity();
    }

    interface ProfileMvpPresenter<V extends ProfileMvpView> extends MvpPresenter<V>{

        void getUserInformation();
        void changePaymentFields();
        void changeInformation();
        void resetPassword(String oldPass, String newPassWord, String confirmPass);
        void onLogout();

    }
}
