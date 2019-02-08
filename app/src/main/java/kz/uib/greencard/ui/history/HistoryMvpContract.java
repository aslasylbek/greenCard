package kz.uib.greencard.ui.history;

import java.util.List;

import kz.uib.greencard.base.MvpPresenter;
import kz.uib.greencard.base.MvpView;
import kz.uib.greencard.repository.model.HistoryResponse;
import kz.uib.greencard.repository.model.Monthe;

public interface HistoryMvpContract {

    interface HistoryMvpView extends MvpView{
        void updateUI(List<Monthe> response);
        void openSplashActivity();
    }

    interface HistoryMvpPresenter<V extends HistoryMvpView> extends MvpPresenter<V>{
        void getAllHistory();
        void getRecentHistory();
    }
}
