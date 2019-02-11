package kz.uib.greencard.ui.companies.company;

import kz.uib.greencard.base.MvpPresenter;
import kz.uib.greencard.base.MvpView;

public interface CompanyMvpContract {
    interface CompanyMvpView extends MvpView{

    }

    interface CompanyMvpPresenter<V extends CompanyMvpView> extends MvpPresenter<V>{

    }
}
