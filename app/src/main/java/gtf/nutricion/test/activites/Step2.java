package gtf.nutricion.test.activites;

import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import gtf.nutricion.test.R;
import gtf.nutricion.test.other.Constants;

/**
 * Created by bootavo on 1/27/18.
 */

public class Step2 extends BaseActivity implements TextWatcher, View.OnClickListener{

    @BindView(R.id.tv_title_activity) TextView mTitleActivity;

    @BindView(R.id.tv_back) TextView mBack;
    @BindView(R.id.tv_next) TextView mTVNext;
    @BindView(R.id.v_tab) View mViewTab;
    @BindView(R.id.btn_next) Button btnNext;

    @BindView(R.id.et_pct) EditText mPct;
    @BindView(R.id.et_cb) EditText mCb;
    @BindView(R.id.et_pa) TextView mPa;

    private String TAG = Step2.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_two);
        initButterKnife();
        init();
        setViewStep();
    }

    public void initButterKnife(){
        ButterKnife.bind(this);
    }

    public void init(){
        btnNext.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mTVNext.setOnClickListener(this);

        mPct.addTextChangedListener(this);
        mCb.addTextChangedListener(this);
        mPa.addTextChangedListener(this);
    }

    public void setViewStep(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/8;
        int height = size.y;
        FrameLayout.LayoutParams parms = new FrameLayout.LayoutParams(width*5,6);
        mViewTab.setLayoutParams(parms);
    }

    public String getPct(){
        if(mPct.getText().toString().equals("")){
            return "0.0";
        }else{
            return mPct.getText().toString();
        }
    }

    public String getCb(){
        if(mCb.getText().toString().equals("")){
            return "0.0";
        }else{
            return mCb.getText().toString();
        }
    }

    public String getPa(){
        if(mPa.getText().toString().equals("")){
            return "0.0";
        }else{
            return mPa.getText().toString();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (verify()){
            mTVNext.setVisibility(View.VISIBLE);
        }else{
            mTVNext.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void saveScore(){
        Log.d(TAG, "------------------>saveScore");
        float _pct = Float.parseFloat(getPct());
        float _cb = Float.parseFloat(getCb());
        float _pa = Float.parseFloat(getPa());

        String pct = String.format("%.2f", _pct);
        String cb = String.format("%.2f", _cb);
        String pa = String.format("%.2f", _pa);

        Log.d(TAG, "genero: "+Constants.GENDER);
        Log.d(TAG, "pct: "+pct);
        Log.d(TAG, "cb: "+cb);
        Log.d(TAG, "pa: "+pa);

        if(Constants.GENDER.equals("M")){
            //PCT
            if(Double.parseDouble(pct) >= 8.0 && Double.parseDouble(pct) <= 9.0){
                Constants.PT_2 = 1;
            }else if(Double.parseDouble(pct) >= 10.0 && Double.parseDouble(pct) <= 11.0){
                Constants.PT_2 = 2;
            }else if(Double.parseDouble(pct) >= 12.0 && Double.parseDouble(pct) <= 20.0){
                Constants.PT_2 = 3;
            }else{
                Constants.PT_2 = 0;
            }

            //CB
            if(Double.parseDouble(cb) >= 19.0 && Double.parseDouble(cb) <= 23.0) {
                Constants.PT_3 = 1;
            }else if(Double.parseDouble(cb) >= 24.0 && Double.parseDouble(cb) <= 26.0){
                Constants.PT_3 = 2;
            }else if(Double.parseDouble(cb) >= 27.0 && Double.parseDouble(cb) <= 30.0){
                Constants.PT_3 = 3;
            }else{
                Constants.PT_3 = 0;
            }

            //PA
            if(Double.parseDouble(pa) >= 1.0 && Double.parseDouble(pa) <= 90.0) {
                Constants.PT_4 = 3;
            }else if(Double.parseDouble(pa) >= 91.0 && Double.parseDouble(pa) <= 93.0){
                Constants.PT_4 = 2;
            }else if(Double.parseDouble(pa) >= 94.0 && Double.parseDouble(pa) <= 101.0){
                Constants.PT_4 = 1;
            }else{
                Constants.PT_4 = 0;
            }

        }else if(Constants.GENDER.equals("F")) {
            //PCT
            if(Double.parseDouble(pct) >= 11.0 && Double.parseDouble(pct) <= 13.0){
                Constants.PT_2 = 1;
            }else if(Double.parseDouble(pct) >= 14.0 && Double.parseDouble(pct) <= 15.0){
                Constants.PT_2 = 2;
            }else if(Double.parseDouble(pct) >= 16.0 && Double.parseDouble(pct) <= 25.0){
                Constants.PT_2 = 3;
            }else{
                Constants.PT_2 = 0;
            }

            //CB
            if(Double.parseDouble(cb) >= 18.0 && Double.parseDouble(cb) <= 19.0) {
                Constants.PT_3 = 1;
            }else if(Double.parseDouble(cb) >= 20.0 && Double.parseDouble(cb) <= 22.0){
                Constants.PT_3 = 2;
            }else if(Double.parseDouble(cb) >= 23.0 && Double.parseDouble(cb) <= 25.0){
                Constants.PT_3 = 3;
            }else{
                Constants.PT_3 = 0;
            }

            //PA
            if(Double.parseDouble(pa) >= 1.0 && Double.parseDouble(pa) <= 80.0) {
                Constants.PT_4 = 3;
            }else if(Double.parseDouble(pa) >= 81.0 && Double.parseDouble(pa) <= 83.0){
                Constants.PT_4 = 2;
            }else if(Double.parseDouble(pa) >= 84.0 && Double.parseDouble(pa) <= 87.0){
                Constants.PT_4 = 1;
            }else{
                Constants.PT_4 = 0;
            }

        }
    }

    public boolean verify(){
        if (!mPct.getText().toString().equals("") && !mCb.getText().toString().equals("") && !mPa.getText().toString().equals("")){
            saveScore();
            return true;
        }else{
            return false;
        }
    }

    public void nextActivity(){
        if (verify()){
            Log.d(TAG, "PT 1: "+Constants.PT_1);
            Log.d(TAG, "PT 2: "+Constants.PT_2);
            Log.d(TAG, "PT 3: "+Constants.PT_3);
            Log.d(TAG, "PT 4: "+Constants.PT_4);
            Toast.makeText(this, "Siguiente", Toast.LENGTH_SHORT).show();
            next(Step3.class, false);
        }else{
            Toast.makeText(this, "Complete todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                nextActivity();
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_next:
                nextActivity();
                break;
        }
    }

}
