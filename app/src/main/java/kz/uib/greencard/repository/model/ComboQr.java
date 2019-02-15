package kz.uib.greencard.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComboQr {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("combo_name")
    @Expose
    private String comboName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

}
