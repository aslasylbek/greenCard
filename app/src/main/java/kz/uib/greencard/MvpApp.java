package kz.uib.greencard;

import android.app.Application;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.local.SharedPrefsHelper;
import kz.uib.greencard.repository.remote.ApiFactory;

/**
 * Created by aslan on 17.05.2018.
 */

public class MvpApp extends Application {

    private DataManager dataManager;
    private SharedPrefsHelper sharedPrefsHelper;


    @Override
    public void onCreate() {
        super.onCreate();
        sharedPrefsHelper = new SharedPrefsHelper(this);
        dataManager = new DataManager(sharedPrefsHelper);
        ApiFactory.recreate();
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Roboto-Light.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build())).
                        build());

    }

    public DataManager getDataManager(){
        if (dataManager==null){
            sharedPrefsHelper = new SharedPrefsHelper(this);
            dataManager = new DataManager(sharedPrefsHelper);
        }
        return dataManager;
    }

}
