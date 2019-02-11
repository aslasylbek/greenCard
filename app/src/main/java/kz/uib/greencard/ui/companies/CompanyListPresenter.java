package kz.uib.greencard.ui.companies;

import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.CompaniesResponse;

public class CompanyListPresenter<V extends CompanyListMvpContract.CompanyListMvpView> extends BasePresenter<V> implements CompanyListMvpContract.CompanyListMvpPresenter<V> {
    public CompanyListPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void getCompaniesById(String category_id) {
        if(isAttached()) {
            getMvpView().showLoading();
            getDataManager().getCompanyList(category_id, new DataManager.GetCompanyListCallBack() {
                @Override
                public void onSuccess(CompaniesResponse response) {
                    if(response.getSuccess()==0){
                        if(response.getError_code()==1){
                            getMvpView().openSplashActivity();
                        }
                        getMvpView().showSnackbar(response.getMessage());
                    }
                    else {
                        if(response.getCompanies()!=null && !response.getCompanies().isEmpty()){
                            getMvpView().updateUI(response.getCompanies());
                        }
                    }
                    getMvpView().hideLoading();
                }

                @Override
                public void onError(String msg) {
                    getMvpView().showSnackbar("Произошла ошибка");
                    getMvpView().hideLoading();
                }
            });
        }


    }
}
