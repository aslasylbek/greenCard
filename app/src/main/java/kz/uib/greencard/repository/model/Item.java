package kz.uib.greencard.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("provider_company_id")
    @Expose
    private String providerCompanyId;
    @SerializedName("provider_name")
    @Expose
    private String providerName;
    @SerializedName("provider_description")
    @Expose
    private String providerDescription;
    @SerializedName("provider_adress")
    @Expose
    private String providerAdress;
    @SerializedName("provider_location_latitude")
    @Expose
    private Double providerLocationLatitude;
    @SerializedName("provider_location_longitude")
    @Expose
    private Double providerLocationLongitude;
    @SerializedName("provider_icon_url")
    @Expose
    private String providerIconUrl;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("confirmed")
    @Expose
    private String confirmed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProviderCompanyId() {
        return providerCompanyId;
    }

    public void setProviderCompanyId(String providerCompanyId) {
        this.providerCompanyId = providerCompanyId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderDescription() {
        return providerDescription;
    }

    public void setProviderDescription(String providerDescription) {
        this.providerDescription = providerDescription;
    }

    public String getProviderAdress() {
        return providerAdress;
    }

    public void setProviderAdress(String providerAdress) {
        this.providerAdress = providerAdress;
    }

    public Double getProviderLocationLatitude() {
        return providerLocationLatitude;
    }

    public void setProviderLocationLatitude(Double providerLocationLatitude) {
        this.providerLocationLatitude = providerLocationLatitude;
    }

    public Double getProviderLocationLongitude() {
        return providerLocationLongitude;
    }

    public void setProviderLocationLongitude(Double providerLocationLongitude) {
        this.providerLocationLongitude = providerLocationLongitude;
    }

    public String getProviderIconUrl() {
        return providerIconUrl;
    }

    public void setProviderIconUrl(String providerIconUrl) {
        this.providerIconUrl = providerIconUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }
}
