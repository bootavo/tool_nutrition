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
import android.widget.LinearLayout;
import android.widget.Switch;
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

public class EdemaStep1 extends BaseActivity implements TextWatcher, View.OnClickListener{

    @BindView(R.id.tv_title_activity) TextView mTitleActivity;

    @BindView(R.id.tv_back) TextView mBack;
    @BindView(R.id.tv_next) TextView mTVNext;
    @BindView(R.id.v_tab) View mViewTab;
    @BindView(R.id.btn_next) Button btnNext;

    @BindView(R.id.et_current_weight) EditText mCurrentWeight;
    @BindView(R.id.et_weight) EditText mWeight;
    @BindView(R.id.et_size) EditText mSize;

    @BindView(R.id.rl_el) LinearLayout mLLEL;
    @BindView(R.id.rl_em) LinearLayout mLLEM;
    @BindView(R.id.rl_es) LinearLayout mLLES;
    @BindView(R.id.sw_e) Switch mSwitchEdema;
    @BindView(R.id.sw_el) Switch mSwitchEdemaL;
    @BindView(R.id.sw_em) Switch mSwitchEdemaM;
    @BindView(R.id.sw_es) Switch mSwitchEdemaS;

    @BindView(R.id.rl_al) LinearLayout mLLAL;
    @BindView(R.id.rl_am) LinearLayout mLLAM;
    @BindView(R.id.rl_as) LinearLayout mLLAS;
    @BindView(R.id.sw_a) Switch mSwitchAscitis;
    @BindView(R.id.sw_al) Switch mSwitchAscitisL;
    @BindView(R.id.sw_am) Switch mSwitchAscitisM;
    @BindView(R.id.sw_as) Switch mSwitchAscitisS;

    @BindView(R.id.tv_imc) TextView mImc;

    private String TAG = EdemaStep1.class.getSimpleName();
    private double final_weight = 0.0;
    private double final_size = 0.0;
    private double resta_edema = 0.0;
    private double resta_ascitis = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edema_weight);
        initButterKnife();
        init();
        initSwitchesEdema();
        initSwitchesAscitis();
        setViewStep();
    }

    public void initButterKnife(){
        ButterKnife.bind(this);
    }

    public void init(){
        btnNext.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mTVNext.setOnClickListener(this);

        mCurrentWeight.addTextChangedListener(this);
        mSize.addTextChangedListener(this);
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

    public String getCurrentWeight(){
        if(mCurrentWeight.getText().toString().equals("")){
            return "0.0";
        }else{
            return mCurrentWeight.getText().toString();
        }
    }

    public String getWeight(){
        if(mWeight.getText().toString().equals("")){
            return "0.0";
        }else{
            return mWeight.getText().toString();
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
        if(!mCurrentWeight.getText().toString().equals("")){
            setWeight();
        }
        showImc();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void setWeight(){
        if(!mCurrentWeight.getText().toString().equals("") && mCurrentWeight.getText().toString() != null){
            final_weight = Double.parseDouble(mCurrentWeight.getText().toString());
            Log.d(TAG, "-------------------------------> current_weight: "+final_size);
            Log.d(TAG, "-------------------------------> resta_edema: "+resta_edema);
            double calculate_weight = final_weight - resta_edema - resta_ascitis;
            mWeight.setText(calculate_weight+"");
            showImc();
        }
    }

    public void showImc(){
        if(verify()) {
            float body_weight = Float.parseFloat(getWeight());
            float size = Float.parseFloat(getSize());
            float imc = body_weight / (size * size);

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

        }else if(mCurrentWeight.getText().toString().equals("")){
            mImc.setText("0.00");
            mTVNext.setVisibility(View.GONE);
        }else if(mSize.getText().toString().equals("")){
            mImc.setText("0.00");
            mTVNext.setVisibility(View.GONE);
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

    public boolean verify(){
        if (!mWeight.getText().toString().equals("") && !mSize.getText().toString().equals("")){
            return true;
        }else{
            return false;
        }
    }

    public void nextActivity(){
        if (verify()){
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

    public void initSwitchesEdema(){
        mSwitchEdema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSwitchEdema.isChecked()){
                    mLLEL.setVisibility(View.VISIBLE);
                    mLLEM.setVisibility(View.VISIBLE);
                    mLLES.setVisibility(View.VISIBLE);
                }else{
                    mSwitchEdemaL.setChecked(false);
                    mSwitchEdemaM.setChecked(false);
                    mSwitchEdemaS.setChecked(false);
                    mLLEL.setVisibility(View.GONE);
                    mLLEM.setVisibility(View.GONE);
                    mLLES.setVisibility(View.GONE);
                    resta_edema = 0.0;
                    setWeight();
                }
            }
        });

        mSwitchEdemaL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSwitchEdemaL.isChecked()){
                    resta_edema = 1.0;
                    mSwitchEdemaM.setChecked(false);
                    mSwitchEdemaS.setChecked(false);
                    setWeight();
                }else{
                    resta_edema = 0.0;
                    setWeight();
                }
            }
        });

        mSwitchEdemaM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSwitchEdemaM.isChecked()){
                    resta_edema = 5.0;
                    mSwitchEdemaL.setChecked(false);
                    mSwitchEdemaS.setChecked(false);
                    setWeight();
                }else{
                    resta_edema = 0.0;
                    setWeight();
                }
            }
        });

        mSwitchEdemaS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSwitchEdemaS.isChecked()){
                    resta_edema = 10.0;
                    mSwitchEdemaL.setChecked(false);
                    mSwitchEdemaM.setChecked(false);
                    setWeight();
                }else{
                    resta_edema = 0.0;
                    setWeight();
                }
            }
        });
    }

    public void initSwitchesAscitis(){
        mSwitchAscitis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSwitchAscitis.isChecked()){
                    mLLAL.setVisibility(View.VISIBLE);
                    mLLAM.setVisibility(View.VISIBLE);
                    mLLAS.setVisibility(View.VISIBLE);
                }else{
                    mSwitchAscitisL.setChecked(false);
                    mSwitchAscitisM.setChecked(false);
                    mSwitchAscitisS.setChecked(false);
                    mLLAL.setVisibility(View.GONE);
                    mLLAM.setVisibility(View.GONE);
                    mLLAS.setVisibility(View.GONE);
                    resta_ascitis = 0.0;
                    setWeight();
                }
            }
        });

        mSwitchAscitisL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSwitchAscitisL.isChecked()){
                    resta_ascitis = 2.2;
                    mSwitchAscitisM.setChecked(false);
                    mSwitchAscitisS.setChecked(false);
                    setWeight();
                }else{
                    resta_ascitis = 0.0;
                    setWeight();
                }
            }
        });

        mSwitchAscitisM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSwitchAscitisM.isChecked()){
                    resta_ascitis = 6.0;
                    mSwitchAscitisL.setChecked(false);
                    mSwitchAscitisS.setChecked(false);
                    setWeight();
                }else{
                    resta_ascitis = 0.0;
                    setWeight();
                }
            }
        });

        mSwitchAscitisS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSwitchAscitisS.isChecked()){
                    resta_ascitis = 10.0;
                    mSwitchAscitisL.setChecked(false);
                    mSwitchAscitisM.setChecked(false);
                    setWeight();
                }else{
                    resta_ascitis = 0.0;
                    setWeight();
                }
            }
        });
    }

}
