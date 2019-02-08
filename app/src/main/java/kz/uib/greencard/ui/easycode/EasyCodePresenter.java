package kz.uib.greencard.ui.easycode;

import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.LoginResponse;

public class EasyCodePresenter <V extends EasyCodeMvpContract.EasyCodeMvpView> extends BasePresenter<V> implements EasyCodeMvpContract.EasyCodeMvpPresenter<V> {

    private int attempt = 0;
    public EasyCodePresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void checkEasyCode(String code) {
        if (!isAttached())
            return;
        if(getDataManager().getPrefCode().equals(code)){
            if(getDataManager().isStudent()){
                getMvpView().showLoading();
                getDataManager().studentAuthentication(getDataManager().getBarcode(), getDataManager().getPrefPassword(), new DataManager.GetLoginCallback() {
                    @Override
                    public void onSuccess(LoginResponse response) {
                        if(response.getSuccess()==0){
                            getMvpView().showSnackbar(response.getMessage());
                        }
                        else {
                            getDataManager().putSessionId(response.getSessionId());
                            getMvpView().openMenuActivity();
                        }
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().showSnackbar("Произошла ошибка");
                        getMvpView().setError();
                        getMvpView().hideLoading();
                    }
                });
            }
            else {
                getMvpView().showLoading();
                getDataManager().userAuthentication(getDataManager().getBarcode(), getDataManager().getPrefPassword(), new DataManager.GetLoginCallback() {
                    @Override
                    public void onSuccess(LoginResponse response) {
                        if(response.getSuccess()==0){
                            getMvpView().showSnackbar(response.getMessage());
                        }
                        else {
                            getDataManager().putSessionId(response.getSessionId());
                            getMvpView().openMenuActivity();
                        }
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().showSnackbar("Произошла ошибка");
                        getMvpView().setError();
                        getMvpView().hideLoading();
                    }
                });
            }
        }
        else {
            if(attempt<=4) {
                attempt++;
                getMvpView().showSnackbar("Ошибка");
                getMvpView().setError();
            }
            else {
                getDataManager().setLoggedMode(false);
                getDataManager().clear();
                getMvpView().openSplashActivity();
            }
        }
    }

    @Override
    public void userLogout() {
        getDataManager().setLoggedMode(false);
        getDataManager().clear();
        getMvpView().openSplashActivity();
    }
}
