package kz.uib.greencard.ui.history;

import android.util.Log;

import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.HistoryResponse;

public class HistoryPresenter<V extends HistoryMvpContract.HistoryMvpView> extends BasePresenter<V> implements HistoryMvpContract.HistoryMvpPresenter<V> {

    private static final String TAG = "HistoryPresenter";
    public HistoryPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void getAllHistory() {
        if(isAttached()) {
            getMvpView().showLoading();
            getDataManager().getAllHistory(new DataManager.GetHistoryCallBack() {
                @Override
                public void onSuccess(HistoryResponse response) {
                    if (response.getSuccess() == 0) {
                        if (response.getError_code() == 1) {
                            getMvpView().openSplashActivity();
                        }
                        getMvpView().showSnackbar(response.getMessage());
                    } else {
                        getMvpView().updateUI(response.getMonthes());
                    }
                    if(isAttached())
                        getMvpView().hideLoading();
                }

                @Override
                public void onError(String msg) {
                    getMvpView().hideLoading();
                }
            });
        }
    }

    @Override
    public void getRecentHistory() {

        if(isAttached()) {
            getMvpView().showLoading();
            getDataManager().getRecentHistory(new DataManager.GetHistoryCallBack() {
                @Override
                public void onSuccess(HistoryResponse response) {
                    if (response.getSuccess() == 0) {
                        if (response.getError_code() == 1) {
                            getMvpView().openSplashActivity();
                        }
                        getMvpView().showSnackbar(response.getMessage());
                    } else {
                        if (isAttached())
                        getMvpView().updateUI(response.getMonthes());
                    }

                    if (isAttached())
                        getMvpView().hideLoading();
                }

                @Override
                public void onError(String msg) {
                    if (isAttached())
                        getMvpView().hideLoading();
                }
            });
        }

    }
}
