package kz.uib.greencard.repository;

import android.os.Build;
import android.util.Log;


import java.util.List;
import java.util.Map;

import kz.uib.greencard.BuildConfig;
import kz.uib.greencard.repository.local.PreferenceHelper;
import kz.uib.greencard.repository.local.SharedPrefsHelper;
import kz.uib.greencard.repository.remote.ApiFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aslan on 17.05.2018.
 */

public class DataManager  {

    private static final String TAG = "DataManager";
    private PreferenceHelper prefsHelper;

    public DataManager(SharedPrefsHelper prefsHelper) {
        this.prefsHelper = prefsHelper;
    }

}
