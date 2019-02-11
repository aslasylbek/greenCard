package kz.uib.greencard.ui.companies.company;

import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;

public class CompanyPresenter<V extends CompanyMvpContract.CompanyMvpView> extends BasePresenter<V> implements CompanyMvpContract.CompanyMvpPresenter<V> {
    public CompanyPresenter(DataManager mDataManager) {
        super(mDataManager);
    }


}
