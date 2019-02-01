package kz.uib.greencard.base;


import kz.uib.greencard.base.general.LoadingView;

/**
 * Created by aslan on 17.05.2018.
 */

public interface MvpView extends LoadingView {

    boolean isNetworkConnected();

    void showToastMessage(int resId);

    void showSnackbar(String message);


}
