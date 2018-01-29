package gtf.nutricion.test.activites;

import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import gtf.nutricion.test.R;
import gtf.nutricion.test.other.AnimateCounter;
import gtf.nutricion.test.other.Constants;

/**
 * Created by bootavo on 1/27/18.
 */

public class PostrateStep1 extends BaseActivity implements TextWatcher, View.OnClickListener{

    @BindView(R.id.tv_title_activity) TextView mTitleActivity;

    @BindView(R.id.tv_back) TextView mBack;
    @BindView(R.id.tv_next) TextView mTVNext;
    @BindView(R.id.v_tab) View mViewTab;
    @BindView(R.id.btn_next) Button btnNext;

    @BindView(R.id.et_eb) EditText mEb;
    @BindView(R.id.et_pa) EditText mPa;
    @BindView(R.id.et_pab) EditText mPab;
    @BindView(R.id.et_pb) EditText mPb;
    @BindView(R.id.et_pmc) EditText mPmc;
    @BindView(R.id.et_pmd) EditText mPmd;
    @BindView(R.id.et_pp) EditText mPp;
    @BindView(R.id.tv_weight) TextView mWeight;

    @BindView(R.id.et_ar) EditText mAr;
    @BindView(R.id.tv_size) TextView mSize;

    @BindView(R.id.tv_imc) TextView mImc;

    private String TAG = PostrateStep1.class.getSimpleName();
    private double final_weight = 0.0;
    private double final_size = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postrate_weight);
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

        mEb.addTextChangedListener(this);
        mPa.addTextChangedListener(this);
        mPab.addTextChangedListener(this);
        mPb.addTextChangedListener(this);
        mPmc.addTextChangedListener(this);
        mPmd.addTextChangedListener(this);
        mPp.addTextChangedListener(this);
        mAr.addTextChangedListener(this);
    }

    public void setViewStep(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/8;
        int height = size.y;
        FrameLayout.LayoutParams parms = new FrameLayout.LayoutParams(width*4,6);
        mViewTab.setLayoutParams(parms);
    }

    public String getEb(){
        if(mEb.getText().toString().equals("")){
            return "0.0";
        }else{
            return mEb.getText().toString();
        }
    }

    public String getPa(){
        if(mPa.getText().toString().equals("")){
            return "0.0";
        }else{
            return mPa.getText().toString();
        }
    }

    public String getPab(){
        if(mPab.getText().toString().equals("")){
            return "0.0";
        }else{
            return mPab.getText().toString();
        }
    }

    public String getPb(){
        if(mPb.getText().toString().equals("")){
            return "0.0";
        }else{
            return mPb.getText().toString();
        }
    }

    public String getPmc(){
        if(mPmc.getText().toString().equals("")){
            return "0.0";
        }else{
            return mPmc.getText().toString();
        }
    }

    public String getPmd(){
        if(mPmd.getText().toString().equals("")){
            return "0.0";
        }else{
            return mPmd.getText().toString();
        }
    }

    public String getPp(){
        if(mPp.getText().toString().equals("")){
            return "0.0";
        }else{
            return mPp.getText().toString();
        }
    }

    public String getWeight(){
        if(mWeight.getText().toString().equals("")){
            return "0.0";
        }else{
            return mWeight.getText().toString();
        }
    }

    public String getAr(){
        if(mAr.getText().toString().equals("")){
            return "0.0";
        }else{
            return mAr.getText().toString();
        }
    }

    public String getSize(){
        if(mSize.getText().toString().equals("")){
            return "0.0";
        }else{
            return mSize.getText().toString();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (verifyWeight() && verifySize()){
            mTVNext.setVisibility(View.VISIBLE);
        }else{
            mTVNext.setVisibility(View.GONE);
        }
        saveWeight();
        saveSize();
        showImc();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void saveWeight(){
        if(verifyWeight()){
            Log.d(TAG, "------------------>saveWeight");
            float _eb = Float.parseFloat(getEb());
            float _pa = Float.parseFloat(getPa());
            float _pab = Float.parseFloat(getPab());
            float _pb = Float.parseFloat(getPb());
            float _pmc = Float.parseFloat(getPmc());
            float _pmd = Float.parseFloat(getPmd());
            float _pp = Float.parseFloat(getPp());

            float weight = 0.0f;

            if(Constants.GENDER.equals("M")){
                //WEIGHT
                weight = (0.22272f * _eb) + (1.01586f * _pab) + (0.90424f * _pmd) + (0.38020f * _pp) + (0.32395f * _pa) + (0.52246f * _pmc) - (91.4048f);
            }else if(Constants.GENDER.equals("F")) {
                //WEIGHT
                weight = (0.26548f * _eb) + (0.65723f * _pab) + (0.45102f * _pmd) + (0.62714f * _pp) + (0.35192f * _pa) + (0.04222f * Constants.AGE) - (68.0767f);
            }

            String weightFormat = String.format("%.2f", weight);
            final_weight = Double.parseDouble(weightFormat);

            AnimateCounter animateCounterBodyWeight = new AnimateCounter.Builder(mWeight)
                    .setCount(0f, weight, 2)
                    .setDuration(500)
                    .setInterpolator(new DecelerateInterpolator())
                    .build();
            animateCounterBodyWeight.execute();
        }
    }

    public void saveSize(){
        if(verifySize()){
            Log.d(TAG, "------------------>saveSize");
            float _ar = Float.parseFloat(getAr());
            float size = 0.0f;

            if(Constants.AGE >= 18 && Constants.AGE <= 59){
                if(Constants.GENDER.equals("M")){
                    size = (70.25f) + (0.05f * Constants.AGE) + (1.86f * _ar);
                }else if (Constants.GENDER.equals("F")){
                    size = (1.885f * _ar) + (71.85f);
                }
            }else if(Constants.AGE >= 60){
                if(Constants.GENDER.equals("M")){
                    size = (64.19f) - (0.04f * Constants.AGE) + (2.02f * _ar);
                }else if (Constants.GENDER.equals("F")){
                    size = (84.88f) - (0.24f * Constants.AGE) + (1.83f * _ar);
                }
            }

            String sizeFormat = String.format("%.2f", _ar);
            final_size = Double.parseDouble(sizeFormat);

            AnimateCounter animateCounterBodyWeight = new AnimateCounter.Builder(mSize)
                    .setCount(0f, size, 2)
                    .setDuration(500)
                    .setInterpolator(new DecelerateInterpolator())
                    .build();
            animateCounterBodyWeight.execute();
        }
    }

    public void showImc(){
        if(verifyWeight() && verifySize()) {

            float body_weight = Float.parseFloat(final_weight+"");
            float size = Float.parseFloat(final_size+"");
            float imc = body_weight / (size * size);

            Log.d(TAG, "------------------>weight: "+body_weight);
            Log.d(TAG, "------------------>size: "+size);
            Log.d(TAG, "------------------>age: "+Constants.AGE);
            Log.d(TAG, "------------------>gender: "+Constants.GENDER);

            String imcFormat = String.format("%.2f", imc);

            Constants.IMC = Double.parseDouble(imcFormat);
            saveScoreStepOne(Constants.IMC);
            Log.d("IMC: ", "" + Constants.IMC);

            AnimateCounter animateCounter = new AnimateCounter.Builder(mImc)
                    .setCount(0f, imc, 2)
                    .setDuration(1000)
                    .setInterpolator(new DecelerateInterpolator())
                    .build();

            animateCounter.execute();
            mTVNext.setVisibility(View.VISIBLE);

            if(mEb.getText().toString().equals("") || mPa.getText().toString().equals("") || mPab.getText().toString().equals("")
                    || mPb.getText().toString().equals("") || mPmc.getText().toString().equals("") || mPmd.getText().toString().equals("")
                    || mPp.getText().toString().equals("")){
                mWeight.setText("0.00");
                mImc.setText("0.00");
                mTVNext.setVisibility(View.GONE);
            }else if(mAr.getText().toString().equals("")){
                mSize.setText("0.00");
                mImc.setText("0.00");
                mTVNext.setVisibility(View.GONE);
            }

        }
    }

    public void saveScoreStepOne(double value){
        if(value >= 18.6 && value <= 21.0){
            Constants.PT_1 = 1;
        }else if (value >= 21.1 && value <= 23.0){
            Constants.PT_1 = 2;
        }else if (value >= 23.1 && value <= 24.9){
            Constants.PT_1 = 3;
        }else{
            Constants.PT_1 = 0;
        }
    }

    public boolean verifyWeight(){
        if (!mEb.getText().toString().equals("") && !mPa.getText().toString().equals("") && !mPab.getText().toString().equals("")
                && !mPb.getText().toString().equals("") && !mPmc.getText().toString().equals("") && !mPmd.getText().toString().equals("")
                && !mPp.getText().toString().equals("")){
            return true;
        }else{
            return false;
        }
    }

    public boolean verifySize(){
        Log.d(TAG, "------------------>verifySize");
        if (!mAr.getText().toString().equals("")){
            Log.d(TAG, "------------------>if");
            return true;
        }else{
            Log.d(TAG, "------------------>else");
            return false;
        }
    }

    public void nextActivity(){
        if (verifyWeight() && verifySize()){
            Toast.makeText(this, "Siguiente", Toast.LENGTH_SHORT).show();
            next(Step2.class, false);
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
