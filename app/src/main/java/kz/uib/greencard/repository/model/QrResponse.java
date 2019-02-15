package kz.uib.greencard.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QrResponse {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sale_percent")
    @Expose
    private String salePercent;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("comboes")
    @Expose
    private List<ComboQr> comboes = null;

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

    public String getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(String salePercent) {
        this.salePercent = salePercent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public List<ComboQr> getComboes() {
        return comboes;
    }

    public void setComboes(List<ComboQr> comboes) {
        this.comboes = comboes;
    }


}
