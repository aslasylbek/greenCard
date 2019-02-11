package kz.uib.greencard.ui.menu;

import kz.uib.greencard.base.MvpPresenter;
import kz.uib.greencard.base.MvpView;

public interface MenuMvpContract {

    interface MenuMvpView extends MvpView{
        void attachMainFragment();

    }

    interface MenuMvpPresenter<V extends MenuMvpView> extends MvpPresenter<V>{
        void initActivity();

    }
}
