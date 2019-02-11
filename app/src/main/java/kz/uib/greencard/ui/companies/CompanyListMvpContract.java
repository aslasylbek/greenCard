package kz.uib.greencard.ui.companies;

import java.util.List;

import kz.uib.greencard.base.MvpPresenter;
import kz.uib.greencard.base.MvpView;
import kz.uib.greencard.repository.model.Company;

public interface CompanyListMvpContract {

    interface CompanyListMvpView extends MvpView{

        void updateUI(List<Company> companies);
        void openSplashActivity();

    }

    interface CompanyListMvpPresenter<V extends CompanyListMvpView> extends MvpPresenter<V>{

        void getCompaniesById(String category_id);

    }
}
