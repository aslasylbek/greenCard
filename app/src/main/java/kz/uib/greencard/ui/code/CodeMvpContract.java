package kz.uib.greencard.ui.code;

import kz.uib.greencard.base.MvpPresenter;
import kz.uib.greencard.base.MvpView;

public interface CodeMvpContract {

    interface CodeMvpView extends MvpView{
        void updateUI();
        void setWrong();
        void openMenuActivity();
    }

    interface CodeMvpPresenter<V extends CodeMvpView> extends MvpPresenter<V>{
        void saveCode(String code);
        void checkCode(String code);
        void dontCreateEasyCode();
    }
}
