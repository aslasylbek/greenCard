package kz.uib.greencard.repository;

import android.os.Build;
import android.util.Log;


import java.util.List;
import java.util.Map;

import kz.uib.greencard.BuildConfig;
import kz.uib.greencard.repository.local.PreferenceHelper;
import kz.uib.greencard.repository.local.SharedPrefsHelper;
import kz.uib.greencard.repository.model.CategoriesResponse;
import kz.uib.greencard.repository.model.ComboResponse;
import kz.uib.greencard.repository.model.CompaniesResponse;
import kz.uib.greencard.repository.model.CompanyResponse;
import kz.uib.greencard.repository.model.History;
import kz.uib.greencard.repository.model.HistoryResponse;
import kz.uib.greencard.repository.model.LoginResponse;
import kz.uib.greencard.repository.model.PostResponse;
import kz.uib.greencard.repository.model.ProfileResponse;
import kz.uib.greencard.repository.model.PurchaseComboResponse;
import kz.uib.greencard.repository.model.QrConfirmResponse;
import kz.uib.greencard.repository.model.QrDiscountResponse;
import kz.uib.greencard.repository.model.QrResponse;
import kz.uib.greencard.repository.remote.ApiFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aslan on 17.05.2018.
 */

public class DataManager  implements DataManagerContract{

    private static final String TAG = "DataManager";
    private PreferenceHelper prefsHelper;

    public DataManager(SharedPrefsHelper prefsHelper) {
        this.prefsHelper = prefsHelper;
    }


    public void userAuthentication(String phone, String password, final GetLoginCallback callback){
        ApiFactory.getApiService().userAuthentication(phone, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onError(t);
            }
        });
    }


    public void studentAuthentication(String barcode, String password, final GetLoginCallback callback){
        ApiFactory.getApiService().studentAuthentication(barcode, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                 callback.onError(t);
            }
        });
    }

    public void getUserInformation(final GetProfileCallback callback){
        ApiFactory
                .getApiService()
                .getUserInformation(getSessionId(), "1")
                .enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()){
                    callback.onSuccess(response.body());
                }
                else callback.onError(response.message());
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void setUserInformation(String lastname, String firsname, String instagramm, final GetPostCallback callback){
        ApiFactory.getApiService()
                .setUserInformation(getSessionId(), "1", lastname, firsname, instagramm)
                .enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        if(response.isSuccessful()){
                            callback.onSuccess(response.body());
                        }
                        else{
                            callback.onError(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }

    public void setPaymentInformation(String iin, String onay_pan, final GetPostCallback callback){
        ApiFactory
                .getApiService()
                .setPaymentInformation(getSessionId(), "1", iin, onay_pan)
                .enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        if(response.isSuccessful()){
                            callback.onSuccess(response.body());
                        }
                        else {
                            callback.onError(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }

    public void resetUserPassword(String newPassword, String oldPassword, final GetPostCallback callback){
        ApiFactory
                .getApiService()
                .resetPassword(getSessionId(), "1", newPassword, oldPassword)
                .enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        if(response.isSuccessful())
                            callback.onSuccess(response.body());
                        else
                            callback.onError(response.message());
                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }

    public void getAllHistory(final GetHistoryCallBack callBack){
        ApiFactory
                .getApiService()
                .getAllHistory(getSessionId(), "1")
                .enqueue(new Callback<HistoryResponse>() {
                    @Override
                    public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                        if(response.isSuccessful()){
                            callBack.onSuccess(response.body());
                        }
                        else callBack.onError(response.message());
                    }

                    @Override
                    public void onFailure(Call<HistoryResponse> call, Throwable t) {
                        callBack.onError(t.getMessage());
                    }
                });
    }

    public void getRecentHistory(final GetHistoryCallBack callBack){
        ApiFactory
                .getApiService()
                .getRecentHistory(getSessionId(), "1")
                .enqueue(new Callback<HistoryResponse>() {
                    @Override
                    public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                        if(response.isSuccessful())
                            callBack.onSuccess(response.body());
                        else callBack.onError(response.message());
                    }

                    @Override
                    public void onFailure(Call<HistoryResponse> call, Throwable t) {
                        callBack.onError(t.getMessage());
                    }
                });
    }

    public void getStoryById(String history_id, final GetStoryCallBack callBack){
        ApiFactory
                .getApiService()
                .getHistoryById(getSessionId(), history_id)
                .enqueue(new Callback<History>() {
                    @Override
                    public void onResponse(Call<History> call, Response<History> response) {
                        if (response.isSuccessful())
                            callBack.onSuccess(response.body());
                        else
                            callBack.onError(response.message());
                    }

                    @Override
                    public void onFailure(Call<History> call, Throwable t) {
                        callBack.onError(t.getMessage());
                    }
                });
    }

    public void getComboStory(String history_id, final GetComboStoryCallBack callBack){
        ApiFactory
                .getApiService()
                .getComboHistoryById(getSessionId(), history_id)
                .enqueue(new Callback<ComboResponse>() {
                    @Override
                    public void onResponse(Call<ComboResponse> call, Response<ComboResponse> response) {
                        if(response.isSuccessful()){
                            callBack.onSuccess(response.body());
                        }
                        else {
                            callBack.onError(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ComboResponse> call, Throwable t) {
                        callBack.onError(t.getMessage());
                    }
                });
    }

    public void getCompanyById(String company_id, final GetCompanyByIdCallBack callBack){
        ApiFactory
                .getApiService()
                .getCompanyById(company_id)
                .enqueue(new Callback<CompanyResponse>() {
                    @Override
                    public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {
                        if (response.isSuccessful())
                            callBack.onSuccess(response.body());
                        else callBack.onError(response.message());
                    }

                    @Override
                    public void onFailure(Call<CompanyResponse> call, Throwable t) {
                        callBack.onError(t.getMessage());
                    }
                });
    }

    public void getCompanyList(String category_id, final GetCompanyListCallBack callBack){
        ApiFactory
                .getApiService()
                .getCategoryById(getSessionId(), category_id)
                .enqueue(new Callback<CompaniesResponse>() {
                    @Override
                    public void onResponse(Call<CompaniesResponse> call, Response<CompaniesResponse> response) {
                        if (response.isSuccessful())
                            callBack.onSuccess(response.body());
                        else callBack.onError(response.message());
                    }

                    @Override
                    public void onFailure(Call<CompaniesResponse> call, Throwable t) {
                        callBack.onError(t.getMessage());
                    }
                });
    }

    public void getCategoryList(final GetCategoryListCallBack callBack){
        ApiFactory
                .getApiService()
                .getCategories(getSessionId(), "1")
                .enqueue(new Callback<CategoriesResponse>() {
                    @Override
                    public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                        if (response.isSuccessful())
                            callBack.onSuccess(response.body());
                        else callBack.onError(response.message());
                    }

                    @Override
                    public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                        callBack.onError(t.getMessage());
                    }
                });
    }

    public void getComboById(String combo_id, final GetComboStoryCallBack callBack){
        ApiFactory
                .getApiService()
                .getComboById(getSessionId(), combo_id)
                .enqueue(new Callback<ComboResponse>() {
                    @Override
                    public void onResponse(Call<ComboResponse> call, Response<ComboResponse> response) {
                        if (response.isSuccessful())
                            callBack.onSuccess(response.body());
                        else callBack.onError(response.message());
                    }

                    @Override
                    public void onFailure(Call<ComboResponse> call, Throwable t) {
                        callBack.onError(t.getMessage());
                    }
                });
    }

    public void purchaseComboRemote(String comboId, String code, final GetComboPurchaseCallBack callBack ){
        ApiFactory
                .getApiService()
                .purchaseCombo(getSessionId(), comboId, code)
                .enqueue(new Callback<PurchaseComboResponse>() {
                    @Override
                    public void onResponse(Call<PurchaseComboResponse> call, Response<PurchaseComboResponse> response) {
                        if (response.isSuccessful()){
                            callBack.onSuccess(response.body());
                        }
                        else callBack.onError(response.message());
                    }

                    @Override
                    public void onFailure(Call<PurchaseComboResponse> call, Throwable t) {
                        callBack.onError(t.getMessage());
                    }
                });
    }

    public void getQrInformation(String qrCode, final GetQrInfoCallBack callBack){
        ApiFactory
                .getApiService()
                .getQrInformation(getSessionId(), qrCode, "1")
                .enqueue(new Callback<QrResponse>() {
                    @Override
                    public void onResponse(Call<QrResponse> call, Response<QrResponse> response) {
                        if (response.isSuccessful()){
                            callBack.onSuccess(response.body());
                        }
                        else {
                            callBack.onError(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<QrResponse> call, Throwable t) {
                        callBack.onError(t.getMessage());

                    }
                });
    }

    public void getDiscount(String qrCode, int amount, final GetQrDiscountCallBack callBack){
        ApiFactory
                .getApiService()
                .getQrDiscount(getSessionId(), qrCode, "1", amount)
                .enqueue(new Callback<QrDiscountResponse>() {
                    @Override
                    public void onResponse(Call<QrDiscountResponse> call, Response<QrDiscountResponse> response) {
                        if (response.isSuccessful()){
                            callBack.onSuccess(response.body());
                        }
                        else callBack.onError(response.message());
                    }

                    @Override
                    public void onFailure(Call<QrDiscountResponse> call, Throwable t) {
                        callBack.onError(t.getMessage());
                    }
                });
    }

    public void confirm_discount(String historyId, String xonlinecode, final GetConfirmCallBack callBack){
        ApiFactory
                .getApiService()
                .confirmDiscount(getSessionId(), historyId, "1", xonlinecode)
                .enqueue(new Callback<QrConfirmResponse>() {
                    @Override
                    public void onResponse(Call<QrConfirmResponse> call, Response<QrConfirmResponse> response) {
                        if (response.isSuccessful())
                            callBack.onSuccess(response.body());
                        else callBack.onError(response.message());
                    }

                    @Override
                    public void onFailure(Call<QrConfirmResponse> call, Throwable t) {
                        callBack.onError(t.getMessage());
                    }
                });
    }

    public void confirmCombo(String optionId, final GetComboPurchaseCallBack callBack){
        ApiFactory
                .getApiService()
                .confirmCombo(getSessionId(), optionId, "1")
                .enqueue(new Callback<PurchaseComboResponse>() {
                    @Override
                    public void onResponse(Call<PurchaseComboResponse> call, Response<PurchaseComboResponse> response) {
                        if (response.isSuccessful())
                            callBack.onSuccess(response.body());
                        else callBack.onError(response.message());
                    }

                    @Override
                    public void onFailure(Call<PurchaseComboResponse> call, Throwable t) {
                        callBack.onError(t.getMessage());
                    }
                });
    }

    @Override
    public void clear() {
        prefsHelper.clear();
    }

    @Override
    public void putPassword(String password) {
        prefsHelper.putPassword(password);
    }

    @Override
    public String getPrefPassword() {
        return prefsHelper.getPrefPassword();
    }

    @Override
    public void putCode(String code) {
        prefsHelper.putCode(code);
    }

    @Override
    public String getPrefCode() {
        return prefsHelper.getPrefCode();
    }

    @Override
    public void putUserId(int user_id) {
        prefsHelper.putUserId(user_id);
    }

    @Override
    public int getPrefUserid() {
        return prefsHelper.getPrefUserid();
    }

    @Override
    public void putBarcode(String barcode) {
        prefsHelper.putBarcode(barcode);
    }

    @Override
    public String getBarcode() {
        return prefsHelper.getBarcode();
    }

    @Override
    public boolean getLoggedMode() {
        return prefsHelper.getLoggedMode();
    }

    @Override
    public void setLoggedMode(boolean loggedIn) {
        prefsHelper.setLoggedMode(loggedIn);
    }

    @Override
    public void setStudent(boolean isStudent) {
        prefsHelper.setStudent(isStudent);
    }

    @Override
    public boolean isStudent() {
        return prefsHelper.isStudent();
    }

    @Override
    public void putSessionId(String session_id) {
        prefsHelper.putSessionId(session_id);
    }

    @Override
    public String getSessionId() {
        return prefsHelper.getSessionId();
    }

    @Override
    public void setOnaiPanEntered(boolean onai_pan) {
        prefsHelper.setOnaiPanEntered(onai_pan);
    }

    @Override
    public boolean isOnaiPanEntered() {
        return false;
    }

    public interface GetLoginCallback {
        void onSuccess(LoginResponse response);
        void onError(Throwable t);
    }

    public interface GetProfileCallback {
        void onSuccess(ProfileResponse response);
        void onError(String t);
    }

    public interface GetPostCallback{
        void onSuccess(PostResponse response);
        void onError(String msg);
    }

    public interface GetHistoryCallBack{
        void onSuccess(HistoryResponse response);
        void onError(String msg);
    }

    public interface GetStoryCallBack{
        void onSuccess(History response);
        void onError(String msg);
    }

    public interface GetComboStoryCallBack{
        void onSuccess(ComboResponse response);
        void onError(String msg);
    }

    public interface GetCompanyByIdCallBack{
        void onSuccess(CompanyResponse response);
        void onError(String msg);
    }

    public interface GetCompanyListCallBack{
        void onSuccess(CompaniesResponse response);
        void onError(String msg);
    }

    public interface GetCategoryListCallBack{
        void onSuccess(CategoriesResponse response);
        void onError(String msg);
    }
    public interface GetComboPurchaseCallBack{
        void onSuccess(PurchaseComboResponse response);
        void onError(String msg);
    }
    public interface GetQrInfoCallBack{
        void onSuccess(QrResponse response);
        void onError(String msg);
    }

    public interface GetQrDiscountCallBack{
        void onSuccess(QrDiscountResponse response);
        void onError(String msg);
    }

    public interface GetConfirmCallBack{
        void onSuccess(QrConfirmResponse response);
        void onError(String msg);
    }



}
