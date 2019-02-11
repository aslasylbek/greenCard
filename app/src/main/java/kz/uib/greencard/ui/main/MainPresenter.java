package kz.uib.greencard.ui.main;

import kz.uib.greencard.base.BasePresenter;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.CategoriesResponse;

public class MainPresenter<V extends MainMvpContract.MainMvpView> extends BasePresenter<V> implements MainMvpContract.MainMvpPresenter<V> {

    public MainPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void getAllOffers() {
        if (isAttached())
            getMvpView().showLoading();

        getDataManager().getCategoryList(new DataManager.GetCategoryListCallBack() {
            @Override
            public void onSuccess(CategoriesResponse response) {
                if (response.getSuccess()==0){
                    if (response.getError_code()==1)
                        getMvpView().openSplashActivity();
                    getMvpView().showSnackbar(response.getMessage());
                }
                else {
                    if(response.getCategories()!=null && !response.getCategories().isEmpty()){
                        getMvpView().updateCategoriesUI(response.getCategories());
                    }
                    if(response.getCombo()!=null && !response.getCombo().isEmpty())
                        getMvpView().updateCombosUI(response.getCombo());
                    else {
                        getMvpView().updateEmptyUI();
                    }
                }
                getMvpView().hideLoading();
            }

            @Override
            public void onError(String msg) {
                if (isAttached()) {
                    getMvpView().showSnackbar("Произошла ошибка");
                    getMvpView().hideLoading();
                }
            }
        });
    }
}
