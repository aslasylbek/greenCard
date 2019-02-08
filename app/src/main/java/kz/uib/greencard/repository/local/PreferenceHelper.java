package kz.uib.greencard.repository.local;

public interface PreferenceHelper {

    String MY_PREFS = "MY_PREF";
    String BARCODE = "BARCODE";
    String PASSWORD = "PASSWORD";
    String TOKEN = "TOKEN";
    String USER_ID = "USER_ID";
    String IS_STUDENT = "STUDENT";

    String SESSION = "SESSION";
    String ONAI = "ONAI";

    void clear();

    void putPassword(String password);
    String getPrefPassword();

    void putCode(String code);
    String getPrefCode();

    void putUserId(int user_id);
    int getPrefUserid();

    void putBarcode(String barcode);
    String getBarcode();

    boolean getLoggedMode();
    void setLoggedMode(boolean loggedIn);

    void putSessionId(String session_id);
    String getSessionId();

    void setOnaiPanEntered(boolean onai_pan);
    boolean isOnaiPanEntered();

    void setStudent(boolean isStudent);
    boolean isStudent();
}
