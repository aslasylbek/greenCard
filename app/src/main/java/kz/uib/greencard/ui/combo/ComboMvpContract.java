package kz.uib.greencard.ui.combo;

import kz.uib.greencard.base.MvpPresenter;
import kz.uib.greencard.base.MvpView;
import kz.uib.greencard.repository.model.ComboResponse;

public interface ComboMvpContract {
    interface  ComboMvpView extends MvpView{

        void updateUI(ComboResponse response);
        void afterComboBuy(String history_id);
        void openSplashActivity();

    }

    interface  ComboMvpPresenter<V extends ComboMvpView> extends MvpPresenter<V>{

        void getComboById(String id);

        void purchaseCombo(String id, String code);

    }
}
