package kz.uib.greencard.ui.main;

import java.util.List;

import kz.uib.greencard.base.MvpPresenter;
import kz.uib.greencard.base.MvpView;
import kz.uib.greencard.repository.model.Category;
import kz.uib.greencard.repository.model.Combo;
import kz.uib.greencard.repository.model.Company;

public interface MainMvpContract {

    interface MainMvpView extends MvpView{
        void updateCategoriesUI(List<Category> categories);
        void updateCombosUI(List<Combo> combos);
        void updateEmptyUI();
        void setEmptyRView();
        void openSplashActivity();
    }

    interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V>{

        void getAllOffers();

    }

}
