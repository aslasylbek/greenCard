package kz.uib.greencard.repository.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by aslan on 17.05.2018.
 */

public class SharedPrefsHelper implements PreferenceHelper{

    private SharedPreferences mSharedPreferences;

    public SharedPrefsHelper(Context context) {
        mSharedPreferences = context.getApplicationContext().getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
    }

    @Override
    public void clear(){
        mSharedPreferences.edit().clear().apply();
    }

    @Override
    public void putPassword(String password){
        mSharedPreferences.edit().putString(PASSWORD, password).apply();
    }

    public String getPrefPassword(){
        return mSharedPreferences.getString(PASSWORD, null);
    }

    @Override
    public void putCode(String code){
        mSharedPreferences.edit().putString(TOKEN, code).apply();
    }

    @Override
    public String getPrefCode(){
        return mSharedPreferences.getString(TOKEN, null);
    }

    @Override
    public void putUserId(int user_id){
        mSharedPreferences.edit().putInt(USER_ID, user_id).apply();
    }

    @Override
    public int getPrefUserid(){
        return mSharedPreferences.getInt(USER_ID, 0);
    }


    @Override
    public void putBarcode(String barcode){
        mSharedPreferences.edit().putString(BARCODE, barcode).apply();
    }

    @Override
    public String getBarcode(){
        return mSharedPreferences.getString(BARCODE, null);
    }

    @Override
    public boolean getLoggedMode(){
        return mSharedPreferences.getBoolean("IS_LOGGED_IN", false);
    }

    @Override
    public void setLoggedMode(boolean loggedIn){
        mSharedPreferences.edit().putBoolean("IS_LOGGED_IN", loggedIn).apply();
    }

    @Override
    public void putSessionId(String session_id) {
        mSharedPreferences.edit().putString(SESSION, session_id).apply();
    }

    @Override
    public String getSessionId() {
        return mSharedPreferences.getString(SESSION, null);
    }

    @Override
    public void setOnaiPanEntered(boolean onai_pan) {
        mSharedPreferences.edit().putBoolean(ONAI, onai_pan).apply();
    }

    @Override
    public boolean isOnaiPanEntered() {
        return mSharedPreferences.getBoolean(ONAI, false);
    }

    @Override
    public void setStudent(boolean isStudent) {
        mSharedPreferences.edit().putBoolean(IS_STUDENT, isStudent).apply();
    }

    @Override
    public boolean isStudent() {
        return mSharedPreferences.getBoolean(IS_STUDENT, false);
    }
}
