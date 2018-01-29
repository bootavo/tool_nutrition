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

public class Step1 extends BaseActivity implements TextWatcher, View.OnClickListener{

    @BindView(R.id.tv_title_activity) TextView mTitleActivity;

    @BindView(R.id.tv_back) TextView mBack;
    @BindView(R.id.tv_next) TextView mTVNext;
    @BindView(R.id.v_tab) View mViewTab;
    @BindView(R.id.btn_next) Button btnNext;

    @BindView(R.id.et_weight) EditText mWeight;
    @BindView(R.id.et_size) EditText mSize;
    @BindView(R.id.tv_imc) TextView mImc;
    //@BindView(R.id.ll_calculate_weight) LinearLayout mLLCalculateWeight;
    //@BindView(R.id.ll_calculate_size) LinearLayout mLLCalculateSize;
    //@BindView(R.id.btn_calculate_weight) Button btnCalculateWeight;
    //@BindView(R.id.btn_calculate_size) Button btnCalculateSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_one);
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
        mWeight.addTextChangedListener(this);
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
        showImc();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void showImc(){
        if(!mWeight.getText().toString().equals("") && !mSize.getText().toString().equals("")) {
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
        }else if(mWeight.getText().toString().equals("")){
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

    public void nextActivity(){
        if (!mWeight.getText().toString().equals("") && !mSize.getText().toString().equals("")){
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
