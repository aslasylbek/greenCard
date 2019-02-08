package kz.uib.greencard.ui.bills.combo;

import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.ComboResponse;

public class BillComboPresenter<V extends BillComboMvpContract.BillComboMvpView> extends BasePresenter<V> implements BillComboMvpContract.BillComboMvpPresenter<V> {

    public BillComboPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void getComboBillById(String billId) {

        if(isAttached())
            getMvpView().showLoading();
        getDataManager().getComboStory(billId, new DataManager.GetComboStoryCallBack() {
            @Override
            public void onSuccess(ComboResponse response) {
                if(response.getSuccess()==0){
                    if(response.getError_code()==1)
                        getMvpView().openSplashActivity();
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
