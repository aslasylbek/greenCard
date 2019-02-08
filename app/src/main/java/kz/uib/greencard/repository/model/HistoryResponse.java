package kz.uib.greencard.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryResponse {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("error_code")
    @Expose
    private Integer error_code;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("monthes")
    @Expose
    private List<Monthe> monthes = null;
    @SerializedName("count_all")
    @Expose
    private Integer countAll;
    @SerializedName("count_shown")
    @Expose
    private Integer countShown;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<Monthe> getMonthes() {
        return monthes;
    }

    public void setMonthes(List<Monthe> monthes) {
        this.monthes = monthes;
    }

    public Integer getCountAll() {
        return countAll;
    }

    public void setCountAll(Integer countAll) {
        this.countAll = countAll;
    }

    public Integer getCountShown() {
        return countShown;
    }

    public void setCountShown(Integer countShown) {
        this.countShown = countShown;
    }

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
