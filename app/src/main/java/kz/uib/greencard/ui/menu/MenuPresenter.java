package kz.uib.greencard.ui.menu;

import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;

public class MenuPresenter<V extends MenuMvpContract.MenuMvpView> extends BasePresenter<V> implements MenuMvpContract.MenuMvpPresenter<V> {

    public MenuPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void initActivity() {
        getMvpView().attachMainFragment();

    }
}
