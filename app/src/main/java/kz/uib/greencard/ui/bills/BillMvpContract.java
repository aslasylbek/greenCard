package kz.uib.greencard.ui.bills;

import kz.uib.greencard.base.MvpPresenter;
import kz.uib.greencard.base.MvpView;

public interface BillMvpContract {

    interface BillMvpView extends MvpView{
        void replaceWithBillFragment();
        void replaceWithComboFragment();

    }
    interface BillMvpPresenter<V extends BillMvpView> extends MvpPresenter<V>{
        void decideNextFragment(String isCombo);
    }
}
