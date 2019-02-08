package kz.uib.greencard.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("is_onay_pan_entered")
    @Expose
    private Boolean isOnayPanEntered;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getIsOnayPanEntered() {
        return isOnayPanEntered;
    }

    public void setIsOnayPanEntered(Boolean isOnayPanEntered) {
        this.isOnayPanEntered = isOnayPanEntered;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
