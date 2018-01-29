package gtf.nutricion.test.activites;

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
    @BindView(R.id.tv_total) TextView mTotal;

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
        mImc.setText(imc+" Pts");

        float pc = Float.parseFloat(Constants.PT_2+"");
        mPliegueCutaneo.setText(pc+" Pts");

        float cmb = Float.parseFloat(Constants.PT_3+"");
        mCircunferenceHands.setText(cmb+" Pts");

        float pa = Float.parseFloat(Constants.PT_4+"");
        mPerimeterAbs.setText(pa+" Pts");

        float h = Float.parseFloat(Constants.PT_5+"");
        mHemoglogine.setText(h+" Pts");

        float a = Float.parseFloat(Constants.PT_6+"");
        mAlbumina.setText(a+" Pts");

        float l = Float.parseFloat(Constants.PT_7+"");
        mLinfocitary.setText(l+" Pts");

        total = imc+pc+cmb+pa+h+a+l;

        String totalFormat = String.format("%.2f", total);
        Constants.PT_TOTAL = Double.parseDouble(totalFormat);

        setValues();
    }

    public void setValues(){
        animateResults();
    }

    public void animateResults(){
        AnimateCounter animateCounter = new AnimateCounter.Builder(mTotal)
                .setCount(0f, total, 2)
                .setDuration(1000)
                .setInterpolator(new DecelerateInterpolator())
                .build();
        animateCounter.execute();
    }

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
