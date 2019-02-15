package kz.uib.greencard.repository.remote;


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
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {


    @FormUrlEncoded
    @POST("green_card/login.php")
    Call<LoginResponse> studentAuthentication(@Field("barcode") String barcode, @Field("password") String password);

    @FormUrlEncoded
    @POST("green_card/login.php")
    Call<LoginResponse> userAuthentication(@Field("phone") String phone, @Field("password") String password);


    @FormUrlEncoded
    @POST("green_card/user.php")
    Call<ProfileResponse> getUserInformation(@Field("session_id") String session_id, @Field("get") String count);

    @FormUrlEncoded
    @POST("green_card/user.php")
    Call<PostResponse> setUserInformation(
            @Field("session_id") String session_id,
            @Field("set") String count,
            @Field("lastname") String lastname,
            @Field("firstname") String firstname,
            @Field("instagram") String instagram);

    @FormUrlEncoded
    @POST("green_card/user.php")
    Call<PostResponse> setPaymentInformation(
            @Field("session_id") String session_id,
            @Field("set") String count,
            @Field("iin") String iin,
            @Field("onay_pan") String onay_pan);

    @FormUrlEncoded
    @POST("green_card/user.php")
    Call<PostResponse> resetPassword(
            @Field("session_id") String session_id,
            @Field("set") String count,
            @Field("new_password") String new_pass,
            @Field("old_password") String old_pass);


    @FormUrlEncoded
    @POST("green_card/history.php")
    Call<HistoryResponse> getAllHistory(@Field("session_id") String session_id, @Field("all") String count);

    @FormUrlEncoded
    @POST("green_card/history.php")
    Call<HistoryResponse> getRecentHistory(@Field("session_id") String session_id, @Field("recent") String count);

    @FormUrlEncoded
    @POST("green_card/history.php")
    Call<History> getHistoryById(@Field("session_id") String session_id, @Field("history_id") String id);

    @FormUrlEncoded
    @POST("green_card/history.php")
    Call<ComboResponse> getComboHistoryById(@Field("session_id") String session_id, @Field("history_id") String id);




    @FormUrlEncoded
    @POST("green_card/company.php")
    Call<CompanyResponse> getCompanyById(@Field("company_id") String id);

    @FormUrlEncoded
    @POST("green_card/category.php")
    Call<CategoriesResponse> getCategories(@Field("session_id") String session_id, @Field("get_all_categories") String id);

    @FormUrlEncoded
    @POST("green_card/category.php")
    Call<CompaniesResponse> getCategoryById(@Field("session_id") String session_id, @Field("category_id") String id);


    @FormUrlEncoded
    @POST("green_card/checkout.php")
    Call<QrResponse> getQrInformation(@Field("session_id") String session_id, @Field("qr_code") String code, @Field("get_info") String cnt);

    @FormUrlEncoded
    @POST("green_card/checkout.php")
    Call<QrDiscountResponse> getQrDiscount(@Field("session_id") String session_id, @Field("qr_code") String code, @Field("get_discount") String cnt, @Field("amount") int amount);

    @FormUrlEncoded
    @POST("green_card/checkout.php")
    Call<QrConfirmResponse> confirmDiscount(@Field("session_id") String session_id, @Field("history_id") String id, @Field("confirm") String cnt, @Field("xonlinecode") String code);


    @FormUrlEncoded
    @POST("green_card/combo.php")
    Call<ComboResponse> getComboById(@Field("session_id") String session_id, @Field("combo_id") String id);

    @FormUrlEncoded
    @POST("green_card/checkout_combo.php")
    Call<PurchaseComboResponse> purchaseCombo(@Field("session_id") String session_id, @Field("combo_id") String id, @Field("xonlinecode") String xonlinecode);


    @FormUrlEncoded
    @POST("green_card/checkout_combo.php")
    Call<PurchaseComboResponse> confirmCombo(@Field("session_id") String session_id, @Field("activity_combo_item_id") String id, @Field("confirm") String confirm);



}
