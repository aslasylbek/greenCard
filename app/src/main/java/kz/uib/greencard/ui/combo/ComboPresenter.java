package kz.uib.greencard.ui.combo;

import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.ComboResponse;
import kz.uib.greencard.repository.model.PurchaseComboResponse;

public class ComboPresenter<V extends ComboMvpContract.ComboMvpView> extends BasePresenter<V> implements ComboMvpContract.ComboMvpPresenter<V> {

    public ComboPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void getComboById(String id) {
        if (isAttached())
            getMvpView().showLoading();

        getDataManager().getComboById(id, new DataManager.GetComboStoryCallBack() {
            @Override
            public void onSuccess(ComboResponse response) {
                if(response.getSuccess()==0){
                    if (response.getError_code()==1)
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

    @Override
    public void purchaseCombo(String id, String code) {
        if (isAttached())
            getMvpView().showLoading();
        getDataManager().purchaseComboRemote(id, code, new DataManager.GetComboPurchaseCallBack() {
            @Override
            public void onSuccess(PurchaseComboResponse response) {
                if (response.getSuccess()==0){
                    if (response.getErrorCode()==1)
                        getMvpView().openSplashActivity();
                    getMvpView().showSnackbar(response.getMessage());
                }
                else {
                    getMvpView().afterComboBuy(String.valueOf(response.getHistoryId()));
                }
                getMvpView().hideLoading();
            }

            @Override
            public void onError(String msg) {
                getMvpView().showSnackbar("");
                getMvpView().hideLoading();
            }
        });
    }
}
