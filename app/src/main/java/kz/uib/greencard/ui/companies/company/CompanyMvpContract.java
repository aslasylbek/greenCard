package kz.uib.greencard.ui.companies.company;

import kz.uib.greencard.base.MvpPresenter;
import kz.uib.greencard.base.MvpView;
import kz.uib.greencard.repository.model.CompanyResponse;

public interface CompanyMvpContract {
    interface CompanyMvpView extends MvpView{
        void updateUI(CompanyResponse companyResponse);

    }

    interface CompanyMvpPresenter<V extends CompanyMvpView> extends MvpPresenter<V>{
        void getCompanyInformationById(String companyId);
    }
}
