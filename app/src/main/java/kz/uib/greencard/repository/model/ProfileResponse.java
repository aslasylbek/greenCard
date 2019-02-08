package kz.uib.greencard.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileResponse {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("point")
    @Expose
    private String point;
    @SerializedName("iin")
    @Expose
    private String iin;
    @SerializedName("onay_pan")
    @Expose
    private String onayPan;

    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("message")
    @Expose
    private String message;

    public ProfileResponse(String firstname, String lastname, String instagram) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.instagram = instagram;
    }

    public ProfileResponse(String iin, String onayPan) {
        this.iin = iin;
        this.onayPan = onayPan;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getOnayPan() {
        return onayPan;
    }

    public void setOnayPan(String onayPan) {
        this.onayPan = onayPan;
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
