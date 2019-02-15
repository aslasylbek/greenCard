package kz.uib.greencard.ui.qr;

import java.util.List;

import kz.uib.greencard.base.MvpPresenter;
import kz.uib.greencard.base.MvpView;
import kz.uib.greencard.repository.model.ComboQr;
import kz.uib.greencard.repository.model.QrDiscountResponse;
import kz.uib.greencard.repository.model.QrResponse;

public interface QrMvpContract {

    interface QrMvpView extends MvpView{

        void informViewAboutQr(QrResponse response);
        void openSplashActivity();
        void showConfirmDiscount(QrDiscountResponse response);
        void testOpenBill(String history_id, String isCombo);
        void showComboQrResponse(List<ComboQr> comboQrs);

    }
    interface QrMvpPresenter<V extends QrMvpView> extends MvpPresenter<V>{

        void sendQrToServer(String qrCode);
        void getDiscount(String qrCode, String amount);
        void confirmDiscount(String historyId, String xOnlineCode);
        void confirmCombo(String comboOptionId);

    }
}
