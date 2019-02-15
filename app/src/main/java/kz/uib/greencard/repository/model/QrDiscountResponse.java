package kz.uib.greencard.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QrDiscountResponse {

    @SerializedName("history_id")
    @Expose
    private Integer historyId;
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("amount_final")
    @Expose
    private Integer amountFinal;
    @SerializedName("icon_url")
    @Expose
    private String iconUrl;
    @SerializedName("sale_percent")
    @Expose
    private String salePercent;
    @SerializedName("point")
    @Expose
    private Integer point;
    @SerializedName("confirmed")
    @Expose
    private Object confirmed;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmountFinal() {
        return amountFinal;
    }

    public void setAmountFinal(Integer amountFinal) {
        this.amountFinal = amountFinal;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(String salePercent) {
        this.salePercent = salePercent;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Object getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Object confirmed) {
        this.confirmed = confirmed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
