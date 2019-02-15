package kz.uib.greencard.ui.companies.company;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseActivity;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.CompanyResponse;
import kz.uib.greencard.ui.companies.CompanyListActivity;

public class CompanyActivity extends BaseActivity implements CompanyMvpContract.CompanyMvpView {

    private static final String TAG = "CompanyActivity";

    private CompanyPresenter presenter;
    private static final String COMPANY_ID = "company_id";
    private String companyId;

    @BindView(R.id.company_toolbar)
    Toolbar toolbar;

    @BindView(R.id.mDescription)
    TextView mDescription;

    @BindView(R.id.mSale)
    TextView mSale;

    @BindView(R.id.iv_nav)
    ImageView mLogo;

    @BindView(R.id.mTitle)
    TextView mTitle;

    @BindView(R.id.mCompanyName)
    TextView mCompanyName;
    @BindView(R.id.mCompanyAddress)
    TextView mAddress;

    @BindView(R.id.constraintLayout)
    ConstraintLayout layout;

    @BindView(R.id.nestedScrollViewRoot)
    NestedScrollView rootView;

    public static Intent getStartIntent(Context context){
        return new Intent(context, CompanyActivity.class);
    }

    @Override
    protected void init(@Nullable Bundle state) {
        rootView.setVisibility(View.INVISIBLE);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        companyId = getIntent().getStringExtra(COMPANY_ID);
        DataManager manager = ((MvpApp)getApplicationContext()).getDataManager();
        presenter = new CompanyPresenter(manager);
        presenter.attachView(this);
        presenter.getCompanyInformationById(companyId);

    }

    @Override
    protected int getContentResource() {
        return R.layout.activity_company;
    }

    @Override
    public void updateUI(CompanyResponse companyResponse) {
        rootView.setVisibility(View.VISIBLE);
        mDescription.setText(companyResponse.getDescription());
        mSale.setText(companyResponse.getSalePercent()+"%");
        mTitle.setText(companyResponse.getName());
        mAddress.setText(companyResponse.getAdress());
        mCompanyName.setText(companyResponse.getName());
        Picasso.with(this)
                .load(companyResponse.getIconUrl())
                .resizeDimen(R.dimen.list_item_img_width, R.dimen.list_item_img_height)
                .centerCrop()
                .placeholder(R.drawable.image_place_holder)
                .into(mLogo);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .add(R.id.frameMap,
                MapsFragment.newInstance(
                        companyResponse.getLocationLatitude(),
                        companyResponse.getLocationLongitude(),
                        companyResponse.getName()))
                .commit();
        layout.bringToFront();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
