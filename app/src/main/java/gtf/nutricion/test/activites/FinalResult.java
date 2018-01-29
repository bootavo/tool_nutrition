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

public class FinalResult extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.tv_title_activity) TextView mTitleActivity;

    @BindView(R.id.tv_back) TextView mBack;
    @BindView(R.id.tv_next) TextView mTVNext;
    @BindView(R.id.v_tab) View mViewTab;
    @BindView(R.id.btn_next) Button btnNext;

    @BindView(R.id.tv_value_results) TextView mValueResults;
    @BindView(R.id.tv_action_plan) TextView mActionPlan;

    private String TAG = FinalResult.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);
        initButterKnife();
        init();
    }

    public void initButterKnife(){
        ButterKnife.bind(this);
    }

    public void init(){
        btnNext.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mTVNext.setOnClickListener(this);
        showValueResults();
        showActionPlan();
        setViewStep();
    }

    public void setViewStep(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/8;
        int height = size.y;
        FrameLayout.LayoutParams parms = new FrameLayout.LayoutParams(width*8,6);
        mViewTab.setLayoutParams(parms);
    }

    public void showValueResults(){
        String message = "";
        if(Constants.PT_TOTAL >= 0.0 && Constants.PT_TOTAL <= 6.0){
            message = "Riesgo Severo";
        }else if(Constants.PT_TOTAL >= 7.0 && Constants.PT_TOTAL <= 13.0){
            message = "Riesgo Moderado";
        }else if(Constants.PT_TOTAL >= 14.0 && Constants.PT_TOTAL <= 17.0){
            message = "Riesgo Leve";
        }else if(Constants.PT_TOTAL >= 18.0 && Constants.PT_TOTAL <= 21.0){
            message = "Normalidad";
        }
        mValueResults.setText(message);
    }

    public void showActionPlan(){
        String message = "";
        if(Constants.PT_TOTAL >= 0.0 && Constants.PT_TOTAL <= 17.0){
            message = "El paciente presenta un riesgo nutricional, la terapía nutricional debe ser iniciada lo antes posible.";
        }else if(Constants.PT_TOTAL >= 18.0 && Constants.PT_TOTAL <= 21.0){
            message = "El paciente debe ser evaluado semanalmente. Si es que el paciente está próximo a una intervención quirurgica, se debe establecer una terapía nutricional de carácter preventivo.";
        }
        mActionPlan.setText(message);
    }

    public void nextActivity(){
        next(GratitudeActivity.class, false);
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
