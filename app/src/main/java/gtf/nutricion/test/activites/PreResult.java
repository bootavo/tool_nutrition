package gtf.nutricion.test.activites;

import android.graphics.ColorSpace;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gtf.nutricion.test.R;
import gtf.nutricion.test.other.AnimateCounter;
import gtf.nutricion.test.other.Constants;

/**
 * Created by bootavo on 1/27/18.
 */

public class PreResult extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.tv_title_activity) TextView mTitleActivity;

    @BindView(R.id.tv_back) TextView mBack;
    @BindView(R.id.tv_next) TextView mTVNext;
    @BindView(R.id.v_tab) View mViewTab;
    @BindView(R.id.btn_next) Button btnNext;

    @BindView(R.id.tv_imc) TextView mImc;
    @BindView(R.id.tv_pc) TextView mPliegueCutaneo;
    @BindView(R.id.tv_cmb) TextView mCircunferenceHands;
    @BindView(R.id.tv_pa) TextView mPerimeterAbs;
    @BindView(R.id.tv_h) TextView mHemoglogine;
    @BindView(R.id.tv_a) TextView mAlbumina;
    @BindView(R.id.tv_l) TextView mLinfocitary;
    //@BindView(R.id.tv_total) TextView mTotal;

    private String TAG = PreResult.class.getSimpleName();
    private float total = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_result);
        initButterKnife();
        init();
        getValues();
        setViewStep();
    }

    public void initButterKnife(){
        ButterKnife.bind(this);
    }

    public void init(){
        btnNext.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mTVNext.setOnClickListener(this);
    }

    public void setViewStep(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/8;
        int height = size.y;
        FrameLayout.LayoutParams parms = new FrameLayout.LayoutParams(width*7,6);
        mViewTab.setLayoutParams(parms);
    }

    public void getValues(){

        float imc = Float.parseFloat(Constants.PT_1+"");
        //mImc.setText(imc+" Pts");

        if(Constants.IMC >= 0.0 && Constants.IMC <= 15.99){
            mImc.setText("DELGADEZ SEVERA");
        }else if (Constants.IMC >= 16.00 && Constants.IMC <= 16.99){
            mImc.setText("DELGADEZ MODERADA");
        }else if (Constants.IMC >= 17.00 && Constants.IMC <= 18.49){
            mImc.setText("DELGADEZ ACEPTABLE");
        }else if (Constants.IMC >= 18.50 && Constants.IMC <= 24.99){
            mImc.setText("NORMALIDAD");
        }else if (Constants.IMC >= 25.00 && Constants.IMC <= 29.99){
            mImc.setText("SOBREPESO");
        }else if (Constants.IMC >= 30.00 && Constants.IMC <= 34.99){
            mImc.setText("OBESIDAD I");
        }else if (Constants.IMC >= 35.00 && Constants.IMC <= 39.99){
            mImc.setText("OBESIDAD II");
        }else if (Constants.IMC >= 40.00){
            mImc.setText("OBESIDAD III");
        }

        float pc = Float.parseFloat(Constants.PT_2+"");
        //mPliegueCutaneo.setText(pc+" Pts");

        if(Constants.GENDER.equals("M")){

            if(Constants.PLIEGUE_CUTANEO_TRICIPITAL >= 1.0 && Constants.PLIEGUE_CUTANEO_TRICIPITAL <= 7.59){
                mPliegueCutaneo.setText("DESNUTRICIÓN SEVERA");
            }else if(Constants.PLIEGUE_CUTANEO_TRICIPITAL >= 7.60 && Constants.PLIEGUE_CUTANEO_TRICIPITAL <= 8.89){
                mPliegueCutaneo.setText("DESNUTRICIÓN MODERADA");
            }else if(Constants.PLIEGUE_CUTANEO_TRICIPITAL >= 8.9 && Constants.PLIEGUE_CUTANEO_TRICIPITAL <= 10.99){
                mPliegueCutaneo.setText("DESNUTRICIÓN LEVE");
            }else if(Constants.PLIEGUE_CUTANEO_TRICIPITAL >= 11.0 && Constants.PLIEGUE_CUTANEO_TRICIPITAL <= 19.99){
                mPliegueCutaneo.setText("NORMALIDAD");
            }else if(Constants.PLIEGUE_CUTANEO_TRICIPITAL >= 20.0){
                mPliegueCutaneo.setText("SOBREPESO");
            }

        }else if(Constants.GENDER.equals("F")){
            if(Constants.PLIEGUE_CUTANEO_TRICIPITAL >= 1.0 && Constants.PLIEGUE_CUTANEO_TRICIPITAL <= 9.99){
                mPliegueCutaneo.setText("DESNUTRICIÓN SEVERA");
            }else if(Constants.PLIEGUE_CUTANEO_TRICIPITAL >= 10.0 && Constants.PLIEGUE_CUTANEO_TRICIPITAL <= 11.59){
                mPliegueCutaneo.setText("DESNUTRICIÓN MODERADA");
            }else if(Constants.PLIEGUE_CUTANEO_TRICIPITAL >= 11.60 && Constants.PLIEGUE_CUTANEO_TRICIPITAL <= 13.99){
                mPliegueCutaneo.setText("DESNUTRICIÓN LEVE");
            }else if(Constants.PLIEGUE_CUTANEO_TRICIPITAL >= 14.0 && Constants.PLIEGUE_CUTANEO_TRICIPITAL <= 24.99){
                mPliegueCutaneo.setText("NORMALIDAD");
            }else if(Constants.PLIEGUE_CUTANEO_TRICIPITAL >= 25.0){
                mPliegueCutaneo.setText("SOBREPESO");
            }
        }

        float cmb = Float.parseFloat(Constants.PT_3+"");
        //mCircunferenceHands.setText(cmb+" Pts");

        if(Constants.GENDER.equals("M")){
            if(Constants.CIRCUNFERENCIA_MUSCULAR_BRAZO >= 1.0 && Constants.CIRCUNFERENCIA_MUSCULAR_BRAZO <= 15.29){
                mCircunferenceHands.setText("DESNUTRICIÓN SEVERA");
            }else if(Constants.CIRCUNFERENCIA_MUSCULAR_BRAZO >= 15.30 && Constants.CIRCUNFERENCIA_MUSCULAR_BRAZO <= 17.79){
                mCircunferenceHands.setText("DESNUTRICIÓN MODERADA");
            }else if (Constants.CIRCUNFERENCIA_MUSCULAR_BRAZO >= 17.80 && Constants.CIRCUNFERENCIA_MUSCULAR_BRAZO <= 20.29){
                mCircunferenceHands.setText("DESNUTRICIÓN LEVE");
            }else if(Constants.CIRCUNFERENCIA_MUSCULAR_BRAZO >= 20.30){
                mCircunferenceHands.setText("NORMALIDAD");
            }
        }else if(Constants.GENDER.equals("F")){
            if(Constants.CIRCUNFERENCIA_MUSCULAR_BRAZO >= 1.0 && Constants.CIRCUNFERENCIA_MUSCULAR_BRAZO <= 13.99){
                mCircunferenceHands.setText("DESNUTRICIÓN SEVERA");
            }else if(Constants.CIRCUNFERENCIA_MUSCULAR_BRAZO >= 14.00 && Constants.CIRCUNFERENCIA_MUSCULAR_BRAZO <= 16.29){
                mCircunferenceHands.setText("DESNUTRICIÓN MODERADA");
            }else if (Constants.CIRCUNFERENCIA_MUSCULAR_BRAZO >= 16.30 && Constants.CIRCUNFERENCIA_MUSCULAR_BRAZO <= 18.69){
                mCircunferenceHands.setText("DESNUTRICIÓN LEVE");
            }else if(Constants.CIRCUNFERENCIA_MUSCULAR_BRAZO >= 18.70){
                mCircunferenceHands.setText("NORMALIDAD");
            }
        }


        float pa = Float.parseFloat(Constants.PT_4+"");
        mPerimeterAbs.setText(pa+" Pts");

        if(Constants.GENDER.equals("M")){
            if(Constants.CIRCUNFERENCIA_CINTURA >= 0.0 && Constants.CIRCUNFERENCIA_CINTURA <= 93.99){
                mPerimeterAbs.setText("NORMALIDAD");
            }else if(Constants.CIRCUNFERENCIA_CINTURA >= 94.0 && Constants.CIRCUNFERENCIA_CINTURA <= 101.99){
                mPerimeterAbs.setText("RIESGO ELEVADO");
            }else if (Constants.CIRCUNFERENCIA_CINTURA >= 102.0){
                mPerimeterAbs.setText("RIESGO MUY ELEVADO");
            }
        }else if(Constants.GENDER.equals("F")){
            if(Constants.CIRCUNFERENCIA_CINTURA >= 0.0 && Constants.CIRCUNFERENCIA_CINTURA <= 79.99){
                mPerimeterAbs.setText("NORMALIDAD");
            }else if(Constants.CIRCUNFERENCIA_CINTURA >= 80.0 && Constants.CIRCUNFERENCIA_CINTURA <= 87.99){
                mPerimeterAbs.setText("RIESGO ELEVADO");
            }else if (Constants.CIRCUNFERENCIA_CINTURA >= 88.0){
                mPerimeterAbs.setText("RIESGO MUY ELEVADO");
            }
        }

        float h = Float.parseFloat(Constants.PT_5+"");
        //mHemoglogine.setText(h+" Pts");

        if(Constants.GENDER.equals("M")){
            if(Constants.HEMOGOBLINA >= 0.0 && Constants.HEMOGOBLINA <= 9.99){
                mHemoglogine.setText("ANEMIA GRAVE");
            }else if(Constants.HEMOGOBLINA >= 10.0 && Constants.HEMOGOBLINA <= 11.99){
                mHemoglogine.setText("ANEMIA MODERADA");
            }else if (Constants.HEMOGOBLINA >= 12.0 && Constants.HEMOGOBLINA <= 13.49){
                mHemoglogine.setText("ANEMIA LEVE");
            }else if (Constants.HEMOGOBLINA >= 13.50){
                mHemoglogine.setText("NORMALIDAD");
            }
        }else if(Constants.GENDER.equals("F")){
            if(Constants.HEMOGOBLINA >= 0.0 && Constants.HEMOGOBLINA <= 79.99){
                mHemoglogine.setText("NORMALIDAD");
            }else if(Constants.HEMOGOBLINA >= 80.0 && Constants.HEMOGOBLINA <= 87.99){
                mHemoglogine.setText("RIESGO ELEVADO");
            }else if (Constants.HEMOGOBLINA >= 88.0){
                mHemoglogine.setText("RIESGO MUY ELEVADO");
            }
        }

        float a = Float.parseFloat(Constants.PT_6+"");
        //mAlbumina.setText(a+" Pts");

        if(Constants.ALBUMINA >= 0.0 && Constants.ALBUMINA <= 2.09){
            mAlbumina.setText("DESNUTRICIÓN SEVERA");
        }else if(Constants.ALBUMINA >= 2.10 && Constants.ALBUMINA <= 2.79){
            mAlbumina.setText("DESNUTRICIÓN MODERADA");
        }else if (Constants.ALBUMINA >= 2.80 && Constants.ALBUMINA <= 3.39){
            mAlbumina.setText("DESNUTRICIÓN LEVE");
        }else if (Constants.ALBUMINA >= 3.40){
            mAlbumina.setText("NORMALIDAD");
        }

        float l = Float.parseFloat(Constants.PT_7+"");
        //mLinfocitary.setText(l+" Pts");

        if(Constants.RECUENTO_TOTAL_LINFOCITARIO >= 0.0 && Constants.RECUENTO_TOTAL_LINFOCITARIO <= 799.0){
            mLinfocitary.setText("DESNUTRICIÓN SEVERA");
        }else if(Constants.RECUENTO_TOTAL_LINFOCITARIO >= 800.0 && Constants.RECUENTO_TOTAL_LINFOCITARIO <= 1199.00){
            mLinfocitary.setText("DESNUTRICIÓN MODERADA");
        }else if (Constants.RECUENTO_TOTAL_LINFOCITARIO >= 1200.0 && Constants.RECUENTO_TOTAL_LINFOCITARIO <= 1499.00){
            mLinfocitary.setText("DESNUTRICIÓN LEVE");
        }else if (Constants.RECUENTO_TOTAL_LINFOCITARIO >= 1500){
            mLinfocitary.setText("NORMALIDAD");
        }

        total = imc+pc+cmb+pa+h+a+l;

        String totalFormat = String.format("%.2f", total);
        Constants.PT_TOTAL = Double.parseDouble(totalFormat);

        //setValues();
    }

    /*
    public void setValues(){
        animateResults();
    }
    */

    /*
    public void animateResults(){
        AnimateCounter animateCounter = new AnimateCounter.Builder(mTotal)
                .setCount(0f, total, 2)
                .setDuration(1000)
                .setInterpolator(new DecelerateInterpolator())
                .build();
        animateCounter.execute();
    }
    */

    public void nextActivity(){
        next(FinalResult.class, false);
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
