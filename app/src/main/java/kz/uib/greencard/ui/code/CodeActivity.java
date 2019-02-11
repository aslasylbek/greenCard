package kz.uib.greencard.ui.code;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class CodeActivity extends BaseActivity implements CodeMvpContract.CodeMvpView, PassCodeView.TextChangeListener {

    private static final String TAG = "CodeActivity";
    @BindView(R.id.pass_code_view)
    PassCodeView passCodeView;

    @BindView(R.id.codeText)
    TextView mCodeText;

    @BindView(R.id.code_toolbar)
    Toolbar mToolbar;

    private boolean isFirstAttampt = true;
    private CodePresenter presenter;

    public static Intent getStartIntent(Context context){
        return new Intent(context, CodeActivity.class);
    }

    @Override
    protected void init(@Nullable Bundle state) {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        passCodeView.setOnTextChangeListener(this);
        DataManager manager = ((MvpApp)getApplicationContext()).getDataManager();
        presenter = new CodePresenter(manager);
        presenter.attachView(this);

    }

    @Override
    protected int getContentResource() {
        return R.layout.activity_code;
    }

    @Override
    public void updateUI() {
        passCodeView.reset();
        mCodeText.setText("Повторите пароль!");
        isFirstAttampt = false;
    }

    @Override
    public void openMenuActivity() {
        Intent intent = MenuActivity.getMenuIntent(this);
        startActivity(intent);
    }

    @Override
    public void setWrong() {
        passCodeView.setError(true);
        isFirstAttampt = true;
        mCodeText.setText("Создайте код - пароль");
    }

    @Override
    public void onTextChanged(String text) {
        if (text.length()==passCodeView.getDigitLength()){
            if(isFirstAttampt){
                presenter.saveCode(text);
            }
            else{
                presenter.checkCode(text);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.code_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.code_close:
                presenter.dontCreateEasyCode();
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
