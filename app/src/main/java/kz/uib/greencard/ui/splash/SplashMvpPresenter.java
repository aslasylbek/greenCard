package kz.uib.greencard.ui.splash;


import kz.uib.greencard.base.MvpPresenter;

/**
 * Created by aslan on 17.05.2018.
 */

public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {

    void decideNextActivty();

}
