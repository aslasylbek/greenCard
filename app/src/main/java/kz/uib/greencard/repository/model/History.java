package kz.uib.greencard.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History {

    @SerializedName("error_code")
    @Expose
    private int error_code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    private int success;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("company_icon_url")
    @Expose
    private String companyIconUrl;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("point")
    @Expose
    private String point;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("amount_final")
    @Expose
    private Double amountFinal;
    @SerializedName("sale_percent")
    @Expose
    private String salePercent;

    @SerializedName("is_combo")
    @Expose
    private String isCombo;

    public String getIsCombo() {
        return isCombo;
    }

    public void setIsCombo(String isCombo) {
        this.isCombo = isCombo;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyIconUrl() {
        return companyIconUrl;
    }

    public void setCompanyIconUrl(String companyIconUrl) {
        this.companyIconUrl = companyIconUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmountFinal() {
        return amountFinal;
    }

    public void setAmountFinal(Double amountFinal) {
        this.amountFinal = amountFinal;
    }

    public String getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(String salePercent) {
        this.salePercent = salePercent;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

