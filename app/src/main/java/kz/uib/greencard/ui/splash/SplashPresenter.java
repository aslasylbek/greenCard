package kz.uib.greencard.ui.splash;


import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;

/**
 * Created by aslan on 17.05.2018.
 */

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {

    public SplashPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void decideNextActivty() {
        if (getDataManager().getLoggedMode()){
            getMvpView().openEasyCodeActivity();
        }
        else
            getMvpView().openLoginActivity();
    }

}
