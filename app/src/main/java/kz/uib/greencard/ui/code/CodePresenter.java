package kz.uib.greencard.ui.code;

import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;

public class CodePresenter<V extends CodeMvpContract.CodeMvpView> extends BasePresenter<V> implements CodeMvpContract.CodeMvpPresenter<V> {

    public CodePresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void saveCode(String code) {
        getDataManager().putCode(code);
        getMvpView().updateUI();
    }

    @Override
    public void checkCode(String code) {
        if(getDataManager().getPrefCode().equals(code)){
            getDataManager().setLoggedMode(true);
            getMvpView().openMenuActivity();
        }

        else{
            getDataManager().putCode(null);
            getMvpView().setWrong();
        }
    }

    @Override
    public void dontCreateEasyCode() {
        getMvpView().openMenuActivity();
    }
}
