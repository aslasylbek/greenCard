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
    public void putToken(String password){
        mSharedPreferences.edit().putString(TOKEN, password).apply();
    }

    @Override
    public String getPrefToken(){
        return mSharedPreferences.getString(TOKEN, null);
    }

    @Override
    public void putUserId(String user_id){
        mSharedPreferences.edit().putString(USER_ID, user_id).apply();
    }

    @Override
    public String getPrefUserid(){
        return mSharedPreferences.getString(USER_ID, null);
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
    public void putCourseId(String course_id) {
        mSharedPreferences.edit().putString(COURSE_ID, course_id).apply();
    }

    @Override
    public String getCourseId() {
        return mSharedPreferences.getString(COURSE_ID, null);
    }

    @Override
    public void putName(String name) {
        mSharedPreferences.edit().putString(NAME, name).apply();
    }

    @Override
    public String getName() {
        return mSharedPreferences.getString(NAME, null);
    }

    @Override
    public void putGroup(String group) {
        mSharedPreferences.edit().putString(GROUP, group).apply();
    }

    @Override
    public String getGroup() {
        return mSharedPreferences.getString(GROUP, null);
    }

    @Override
    public void putProgram(String program) {
        mSharedPreferences.edit().putString(PROGRAM, program).apply();
    }

    @Override
    public String getProgram() {
        return mSharedPreferences.getString(PROGRAM, null);
    }
}
