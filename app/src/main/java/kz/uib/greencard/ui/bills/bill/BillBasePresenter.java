package kz.uib.greencard.ui.bills.bill;

import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.History;

public class BillBasePresenter<V extends BillBaseMvpContract.BillBaseMvpView> extends BasePresenter<V> implements BillBaseMvpContract.BillBaseMvpPresenter<V> {

    public BillBasePresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void getBillById(String id) {
        if (isAttached())
            getMvpView().showLoading();
        getDataManager().getStoryById(id, new DataManager.GetStoryCallBack() {
            @Override
            public void onSuccess(History response) {
                if(response.getSuccess()==0){
                    if(response.getError_code()==1){
                        getMvpView().openSplashActivity();
                    }
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
