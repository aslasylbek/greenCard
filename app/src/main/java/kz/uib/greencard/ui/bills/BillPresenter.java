package kz.uib.greencard.ui.bills;

import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;

public class BillPresenter<V extends BillMvpContract.BillMvpView> extends BasePresenter<V> implements BillMvpContract.BillMvpPresenter<V> {

    public BillPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void decideNextFragment(String isCombo) {
        if(isCombo.equals("1")){
            getMvpView().replaceWithComboFragment();
        }
        else {
            getMvpView().replaceWithBillFragment();
        }

    }
}
