package kz.uib.greencard.ui.login;

public class User {

    private String barcodeOrPhone;
    private String password;
    private boolean isStudent;

    public User(String barcodeOrPhone, String password, boolean isStudent) {
        this.barcodeOrPhone = barcodeOrPhone;
        this.password = password;
        this.isStudent = isStudent;
    }

    public User() {
    }

    public String getBarcodeOrPhone() {
        return barcodeOrPhone;
    }

    public void setBarcodeOrPhone(String barcodeOrPhone) {
        this.barcodeOrPhone = barcodeOrPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }
}
