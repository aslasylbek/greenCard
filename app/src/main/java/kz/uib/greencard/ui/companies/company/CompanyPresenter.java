package kz.uib.greencard.ui.companies.company;

import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.CompanyResponse;

public class CompanyPresenter<V extends CompanyMvpContract.CompanyMvpView> extends BasePresenter<V> implements CompanyMvpContract.CompanyMvpPresenter<V> {
    public CompanyPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void getCompanyInformationById(String companyId) {

        if (isAttached())
            getMvpView().showLoading();
        getDataManager().getCompanyById(companyId, new DataManager.GetCompanyByIdCallBack() {
            @Override
            public void onSuccess(CompanyResponse response) {
                if(response.getSuccess()==0){
                    getMvpView().showSnackbar(response.getMessage());
                }
                else {
                    getMvpView().updateUI(response);
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
