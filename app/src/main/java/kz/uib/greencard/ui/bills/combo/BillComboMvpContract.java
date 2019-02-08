package kz.uib.greencard.ui.bills.combo;

import kz.uib.greencard.base.MvpPresenter;
import kz.uib.greencard.base.MvpView;
import kz.uib.greencard.repository.model.ComboResponse;

public interface BillComboMvpContract {

    interface BillComboMvpView extends MvpView{
        void updateUI(ComboResponse response);
        void openSplashActivity();
    }

    interface BillComboMvpPresenter<V extends BillComboMvpView> extends MvpPresenter<V>{
        void getComboBillById(String billId);

    }
}
