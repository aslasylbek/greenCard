package kz.uib.greencard.base;

/**
 * Created by aslan on 17.05.2018.
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();

    boolean isAttached();
}
