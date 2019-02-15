package kz.uib.greencard.ui.qr;

import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.PurchaseComboResponse;
import kz.uib.greencard.repository.model.QrConfirmResponse;
import kz.uib.greencard.repository.model.QrDiscountResponse;
import kz.uib.greencard.repository.model.QrResponse;

public class QrPresenter<V extends QrMvpContract.QrMvpView> extends BasePresenter<V> implements QrMvpContract.QrMvpPresenter<V> {

    public QrPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void sendQrToServer(String qrCode) {

        if (isAttached())
            getMvpView().showLoading();

        getDataManager().getQrInformation(qrCode, new DataManager.GetQrInfoCallBack() {
            @Override
            public void onSuccess(QrResponse response) {
                if (response.getSuccess()==0){
                    if (response.getErrorCode()==1)
                        getMvpView().openSplashActivity();
                    getMvpView().showSnackbar(response.getMessage());
                }
                else {
                    if (response.getComboes()==null){
                        getMvpView().informViewAboutQr(response);
                    }
                    else getMvpView().showComboQrResponse(response.getComboes());

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
    public void confirmCombo(String comboOptionId) {

        if (isAttached())
            getMvpView().showLoading();

        getDataManager().confirmCombo(comboOptionId, new DataManager.GetComboPurchaseCallBack() {
            @Override
            public void onSuccess(PurchaseComboResponse response) {
                if (response.getSuccess()==0){
                    if (response.getErrorCode()==1)
                        getMvpView().openSplashActivity();
                    getMvpView().showSnackbar(response.getMessage());
                }
                else {
                    getMvpView().testOpenBill(String.valueOf(response.getHistoryId()), "1");
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
    public void getDiscount(String qrCode, String amount) {
        if(!tryParseInt(amount)){
            return;
        }
        int amountInt = Integer.parseInt(amount);

        if (amountInt<0){
            return;
        }

        if (isAttached())
            getMvpView().showLoading();

        getDataManager().getDiscount(qrCode, amountInt, new DataManager.GetQrDiscountCallBack() {
            @Override
            public void onSuccess(QrDiscountResponse response) {
                if (response.getSuccess()==0){
                    if (response.getErrorCode()==1)
                        getMvpView().openSplashActivity();
                    getMvpView().showSnackbar(response.getMessage());
                }
                else {
                    getMvpView().showConfirmDiscount(response);
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
    public void confirmDiscount(final String historyId, String xOnlineCode) {
        if (isAttached())
            getMvpView().showLoading();

        getDataManager().confirm_discount(historyId, xOnlineCode, new DataManager.GetConfirmCallBack() {
            @Override
            public void onSuccess(QrConfirmResponse response) {
                if (response.getSuccess()==0){
                    /*if (response.getErrorCode()==1)
                        getMvpView().openSplashActivity();*/
                    getMvpView().showSnackbar(response.getMessage());
                }
                else {
                    getMvpView().testOpenBill(historyId,"0");
                }
                getMvpView().hideLoading();
            }

            @Override
            public void onError(String msg) {
                getMvpView().hideLoading();
                getMvpView().showSnackbar("Произошла ошибка");
            }
        });

    }

    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
