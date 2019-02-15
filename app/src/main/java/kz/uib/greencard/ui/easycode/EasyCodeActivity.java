package kz.uib.greencard.ui.easycode;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import in.arjsna.passcodeview.PassCodeView;
import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseActivity;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.ui.menu.MenuActivity;
import kz.uib.greencard.ui.splash.SplashActivity;

public class EasyCodeActivity extends BaseActivity implements EasyCodeMvpContract.EasyCodeMvpView, PassCodeView.TextChangeListener, DialogInterface.OnClickListener {


    private EasyCodePresenter presenter;

    @BindView(R.id.easy_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.easyText)
    TextView mCodeText;

    @BindView(R.id.passEasycodeView)
    PassCodeView mEasyCodeView;

    private int attempt = 0;

    public static Intent getStartIntent(Context context){
        return new Intent(context, EasyCodeActivity.class);
    }

    @Override
    protected void init(@Nullable Bundle state) {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mEasyCodeView.setOnTextChangeListener(this);
        DataManager manager = ((MvpApp)getApplicationContext()).getDataManager();
        presenter = new EasyCodePresenter(manager);
        presenter.attachView(this);
    }

    @Override
    protected int getContentResource() {
        return R.layout.activity_easy_code;
    }

    @Override
    public void onTextChanged(String text) {
        if(text.length() == mEasyCodeView.getDigitLength()){
            presenter.checkEasyCode(text);
        }
    }

    @Override
    public void setError() {
        mEasyCodeView.setError(true);
    }

    @Override
    public void openSplashActivity() {
        Intent intent = SplashActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openMenuActivity() {
        Intent intent = MenuActivity.getMenuIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.easy_code_menu, menu);
        return true;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which==-2){
            dialog.cancel();
        }
        else{
            presenter.userLogout();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.easy_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Вы точно хотете выйти?")
                        .setCancelable(true)
                        .setNegativeButton("Cancel", this)
                        .setPositiveButton("OK", this);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
