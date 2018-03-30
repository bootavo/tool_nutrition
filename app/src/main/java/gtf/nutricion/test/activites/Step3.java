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

public class Step3 extends BaseActivity implements TextWatcher, View.OnClickListener{

    @BindView(R.id.tv_title_activity) TextView mTitleActivity;

    @BindView(R.id.tv_back) TextView mBack;
    @BindView(R.id.tv_next) TextView mTVNext;
    @BindView(R.id.v_tab) View mViewTab;
    @BindView(R.id.btn_next) Button btnNext;

    @BindView(R.id.et_hemoglobine) EditText mHemoglobine;
    @BindView(R.id.et_albumina) EditText mAlbumina;
    @BindView(R.id.et_linfacitary) TextView mLinfacitary;

    private String TAG = Step3.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_three);
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

        mHemoglobine.addTextChangedListener(this);
        mAlbumina.addTextChangedListener(this);
        mLinfacitary.addTextChangedListener(this);
    }

    public void setViewStep(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/8;
        int height = size.y;
        FrameLayout.LayoutParams parms = new FrameLayout.LayoutParams(width*6,6);
        mViewTab.setLayoutParams(parms);
    }

    public String getHemoglobine(){
        if(mHemoglobine.getText().toString().equals("")){
            return "0.0";
        }else{
            return mHemoglobine.getText().toString();
        }
    }

    public String geAlbumina(){
        if(mAlbumina.getText().toString().equals("")){
            return "0.0";
        }else{
            return mAlbumina.getText().toString();
        }
    }

    public String getLinfacitary(){
        if(mLinfacitary.getText().toString().equals("")){
            return "0.0";
        }else{
            return mLinfacitary.getText().toString();
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
        float _hemoglobine = Float.parseFloat(getHemoglobine());
        float _albumina = Float.parseFloat(geAlbumina());
        float _linfacitary = Float.parseFloat(getLinfacitary());

        //SAVES NEW SCORES
        Constants.HEMOGOBLINA = Double.parseDouble(getHemoglobine());
        Constants.ALBUMINA = Double.parseDouble(geAlbumina());
        Constants.RECUENTO_TOTAL_LINFOCITARIO = Double.parseDouble(getLinfacitary());

        String hemogobline = String.format("%.2f", _hemoglobine);
        String albumina = String.format("%.2f", _albumina);
        String linfacitary = String.format("%.2f", _linfacitary);

        Log.d(TAG, "genero: "+Constants.GENDER);
        Log.d(TAG, "hemogobline: "+hemogobline);
        Log.d(TAG, "albumina: "+albumina);
        Log.d(TAG, "linfacitary: "+linfacitary);

        if(Constants.GENDER.equals("M")){
            //HEMOGLOBINE
            if(Double.parseDouble(hemogobline) >= 10.0 && Double.parseDouble(hemogobline) <= 11.99){
                Constants.PT_5 = 1;
            }else if(Double.parseDouble(hemogobline) >= 12.0 && Double.parseDouble(hemogobline) <= 13.49){
                Constants.PT_5 = 2;
            }else if(Double.parseDouble(hemogobline) >= 13.50){
                Constants.PT_5 = 3;
            }else{
                Constants.PT_5 = 0;
            }
        }else if(Constants.GENDER.equals("F")) {
            //HEMOGLOBINE
            if(Double.parseDouble(hemogobline) >= 8.0 && Double.parseDouble(hemogobline) <= 9.99){
                Constants.PT_5 = 1;
            }else if(Double.parseDouble(hemogobline) >= 10.0 && Double.parseDouble(hemogobline) <= 11.99){
                Constants.PT_5 = 2;
            }else if(Double.parseDouble(hemogobline) >= 12.0){
                Constants.PT_5 = 3;
            }else{
                Constants.PT_5 = 0;
            }
        }

        //ALBUMINA
        if(Double.parseDouble(albumina) >= 2.10 && Double.parseDouble(albumina) <= 2.79) {
            Constants.PT_6 = 1;
        }else if(Double.parseDouble(albumina) >= 2.80 && Double.parseDouble(albumina) <= 3.59){
            Constants.PT_6 = 2;
        }else if(Double.parseDouble(albumina) >= 3.60){
            Constants.PT_6 = 3;
        }else{
            Constants.PT_6 = 0;
        }

        //LINFACITARY
        if(Double.parseDouble(linfacitary) >= 1.0 && Double.parseDouble(linfacitary) <= 1199.99) {
            Constants.PT_7 = 0;
        }else if(Double.parseDouble(linfacitary) >= 1200.00 && Double.parseDouble(linfacitary) <= 1499.99){
            Constants.PT_7 = 1;
        }else if(Double.parseDouble(linfacitary) >= 1500.0 && Double.parseDouble(linfacitary) <= 1999.99){
            Constants.PT_7 = 2;
        }else if(Double.parseDouble(linfacitary) >= 2000.0){
            Constants.PT_7 = 3;
        }
    }

    public boolean verify(){
        if (!mHemoglobine.getText().toString().equals("") && !mAlbumina.getText().toString().equals("") && !mLinfacitary.getText().toString().equals("")){
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
            Log.d(TAG, "PT 5: "+Constants.PT_5);
            Log.d(TAG, "PT 6: "+Constants.PT_6);
            Log.d(TAG, "PT 7: "+Constants.PT_7);

            Toast.makeText(this, "Siguiente", Toast.LENGTH_SHORT).show();
            next(PreResult.class, false);
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
