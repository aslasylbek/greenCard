package kz.uib.greencard.ui.qr;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.google.zxing.Result;

import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import kz.uib.greencard.MvpApp;
import kz.uib.greencard.R;
import kz.uib.greencard.base.BaseAdapter;
import kz.uib.greencard.base.BaseFragment;
import kz.uib.greencard.base.EmptyRecyclerView;
import kz.uib.greencard.repository.DataManager;
import kz.uib.greencard.repository.model.Combo;
import kz.uib.greencard.repository.model.ComboQr;
import kz.uib.greencard.repository.model.QrDiscountResponse;
import kz.uib.greencard.repository.model.QrResponse;
import kz.uib.greencard.ui.bills.BillActivity;
import kz.uib.greencard.ui.splash.SplashActivity;


public class QRFragment extends BaseFragment implements QrMvpContract.QrMvpView, DecodeCallback, ErrorCallback, BaseAdapter.OnItemClickListener {


    private static final String TAG = "QRFragment";
    private static final int RC_PERMISSION = 10;
    private static final String BILL_ID = "bill_id";
    private static final String IS_COMBO = "is_combo";

    private OnFragmentInteractionListener mListener;
    private QrPresenter presenter;
    private CodeScanner mCodeScanner;
    private boolean mPermissionGranted;
    private String qrString;

    @BindView(R.id.scanner_view)
    CodeScannerView scannerView;

    public static QRFragment newInstance() {
        QRFragment fragment = new QRFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(@Nullable Bundle bundle) {

        mCodeScanner = new CodeScanner(getBaseActivity(), scannerView);
        mCodeScanner.setDecodeCallback(this);
        mCodeScanner.setErrorCallback(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            requestPermissions(new String[] {Manifest.permission.CAMERA}, RC_PERMISSION);

        } else {
            mPermissionGranted = true;
        }

        DataManager manager = ((MvpApp)getBaseActivity().getApplicationContext()).getDataManager();
        presenter = new QrPresenter(manager);
        presenter.attachView(this);

    }

    @Override
    public void onDecoded(@NonNull final Result result) {
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: "+result.getText());
                qrString = result.getText();
                presenter.sendQrToServer(qrString);
            }
        });
    }

    @Override
    public void onError(@NonNull final Exception error) {
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showSnackbar(error.getMessage());
                Log.e(TAG, "run: "+error.getMessage() );
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @android.support.annotation.NonNull String[] permissions, @android.support.annotation.NonNull int[] grantResults) {
        if (requestCode == RC_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mPermissionGranted = true;
                mCodeScanner.startPreview();
            } else {
                mPermissionGranted = false;
            }
        }
    }

    /*  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @OnClick(R.id.scanner_view)
    public void onScannerClick(){
        if (mPermissionGranted)
        mCodeScanner.startPreview();
    }

    @Override
    public void showComboQrResponse(List<ComboQr> comboQrs) {

        final Dialog dialogFragment = new Dialog(getBaseActivity());
        dialogFragment.setContentView(R.layout.combo_dialog);
        dialogFragment.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EmptyRecyclerView recyclerView = dialogFragment.findViewById(R.id.comboRecyclerView);
        Button mCancel = dialogFragment.findViewById(R.id.btn_cancel);
        TextView mTitle = dialogFragment.findViewById(R.id.tv_title_dg);
        TextView mStatic = dialogFragment.findViewById(R.id.tvStatic);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        ComboDialogAdapter adapter = new ComboDialogAdapter(comboQrs);
        adapter.attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(this);

        mTitle.setText("Доступное комбо");
        mStatic.setText("Выберите комбо которое хотите использовать");
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.cancel();
            }
        });
        dialogFragment.show();

    }

    @Override
    public void onItemClick(@android.support.annotation.NonNull Object item) {

        final ComboQr comboQr = (ComboQr)item;
        final Dialog dialogFragment = new Dialog(getBaseActivity());
        dialogFragment.setContentView(R.layout.purchase_dialog);
        dialogFragment.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogFragment.setCancelable(true);

        Button mBuy = dialogFragment.findViewById(R.id.btn_buy);
        Button mCancel = dialogFragment.findViewById(R.id.btn_cancel);
        final EditText mCode = dialogFragment.findViewById(R.id.et_code);
        TextView mTitle = dialogFragment.findViewById(R.id.tv_title_dg);
        TextView mStatic = dialogFragment.findViewById(R.id.tvStatic);
        mCode.setVisibility(View.GONE);
        mCode.setInputType(InputType.TYPE_CLASS_NUMBER);
        mBuy.setText("Подтвердить");
        mTitle.setText("Подтверждение");
        mStatic.setText(String.format("Вы уверены что хотите использовать комбо %s ?", comboQr.getComboName()));
        mCode.setHint("Введите сумму");
        mBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    presenter.confirmCombo(comboQr.getId());
                    dialogFragment.dismiss();
            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.cancel();
            }
        });
        dialogFragment.show();

    }

    @Override
    protected int getContentResource() {
        return R.layout.fragment_qr;
    }

    @Override
    public void informViewAboutQr(QrResponse response) {
        final Dialog dialogFragment = new Dialog(getBaseActivity());
        dialogFragment.setContentView(R.layout.purchase_dialog);
        dialogFragment.setCancelable(true);
        dialogFragment.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button mBuy = dialogFragment.findViewById(R.id.btn_buy);
        Button mCancel = dialogFragment.findViewById(R.id.btn_cancel);
        final EditText mCode = dialogFragment.findViewById(R.id.et_code);
        TextView mTitle = dialogFragment.findViewById(R.id.tv_title_dg);
        TextView mStatic = dialogFragment.findViewById(R.id.tvStatic);

        mCode.setInputType(InputType.TYPE_CLASS_NUMBER);
        mBuy.setText("Оплатить");
        mTitle.setText(response.getName());
        mStatic.setText(String.format("Размер скидки %s. Введите сумму БУЗ учета скидки.", response.getSalePercent()));
        mCode.setHint("Введите сумму");
        mBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCode.getText().toString().equals("")){
                    mCode.setError("введите сумму");
                }
                else {
                    presenter.getDiscount(qrString, mCode.getText().toString());
                    dialogFragment.dismiss();
                }
            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.cancel();
            }
        });
        dialogFragment.show();
    }

    @Override
    public void showConfirmDiscount(final QrDiscountResponse response) {
        //todo make dialog dry
        final Dialog dialogFragment = new Dialog(getBaseActivity());
        dialogFragment.setContentView(R.layout.purchase_dialog);
        dialogFragment.setCancelable(true);
        dialogFragment.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button mBuy = dialogFragment.findViewById(R.id.btn_buy);
        Button mCancel = dialogFragment.findViewById(R.id.btn_cancel);
        final EditText mCode = dialogFragment.findViewById(R.id.et_code);
        TextView mTitle = dialogFragment.findViewById(R.id.tv_title_dg);
        TextView mStatic = dialogFragment.findViewById(R.id.tvStatic);
        mTitle.setText("Подтверждение");

        mBuy.setText("Подтвердить");
        mStatic.setText(String.format("С вашего счета будет списано %s. Для подтверждения введите код для онлайн платежей из приложения Онай. ", response.getAmountFinal()));
        mBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCode.getText().toString().equals("")){
                    mCode.setError("введите код");
                }
                else {
                    presenter.confirmDiscount(String.valueOf(response.getHistoryId()), mCode.getText().toString());
                    dialogFragment.dismiss();
                }
            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.cancel();
            }
        });
        dialogFragment.show();


    }

    @Override
    public void testOpenBill(String historyId, String isCombo) {
        //todo OPEN bill activity success
        Intent intent = BillActivity.getStartIntent(getBaseActivity());
        intent.putExtra(BILL_ID, historyId);
        intent.putExtra(IS_COMBO, isCombo);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPermissionGranted)
            mCodeScanner.startPreview();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void openSplashActivity() {
        Intent intent = SplashActivity.getStartIntent(getBaseActivity());
        startActivity(intent);
        getBaseActivity().finish();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
