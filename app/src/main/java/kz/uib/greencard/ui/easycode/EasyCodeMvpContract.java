package kz.uib.greencard.ui.easycode;

import kz.uib.greencard.base.MvpPresenter;
import kz.uib.greencard.base.MvpView;

public interface EasyCodeMvpContract {

    interface EasyCodeMvpView extends MvpView{

        void setError();
        void openMenuActivity();
        void openSplashActivity();

    }

    interface EasyCodeMvpPresenter<V extends EasyCodeMvpView> extends MvpPresenter<V>{

        void checkEasyCode(String code);
        void userLogout();
    }
}
