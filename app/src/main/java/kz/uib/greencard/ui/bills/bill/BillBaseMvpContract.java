package kz.uib.greencard.ui.bills.bill;

import kz.uib.greencard.base.MvpPresenter;
import kz.uib.greencard.base.MvpView;
import kz.uib.greencard.repository.model.History;

public interface BillBaseMvpContract {

    interface BillBaseMvpView extends MvpView{
        void openSplashActivity();
        void updateUI(History history);
    }

    interface BillBaseMvpPresenter<V extends BillBaseMvpView> extends MvpPresenter<V>{

        void getBillById(String id);

    }
}
