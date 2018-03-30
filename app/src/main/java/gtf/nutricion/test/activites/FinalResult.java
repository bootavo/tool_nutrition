package gtf.nutricion.test.activites;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
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

/**
 * Created by bootavo on 1/27/18.
 */

public class FinalResult extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.tv_title_activity) TextView mTitleActivity;

    @BindView(R.id.tv_back) TextView mBack;
    @BindView(R.id.tv_next) TextView mTVNext;
    @BindView(R.id.v_tab) View mViewTab;
    @BindView(R.id.btn_next) Button btnNext;

    @BindView(R.id.et_value_results) EditText mValueResults;
    @BindView(R.id.et_email) EditText mEmail;
    @BindView(R.id.tv_action_plan) TextView mActionPlan;
    @BindView(R.id.et_patient) EditText mPatient;

    private String TAG = FinalResult.class.getSimpleName();
    private Context ctx = this;

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

    public void showActionPlan(){
        String message = "";
        message = "Si es que el paciente está próximo a una intervención quirurgica, se debe establecer una terapía nutricional de carácter preventivo.";
        mActionPlan.setText(message);
    }

    private String getPatient(){
        return mPatient.getText().toString().trim();
    }

    private boolean verifyPatient(){
        if(getPatient() == null || getPatient().equals("")){
            return false;
        }else {
            return true;
        }
    }

    private String getEmail(){
        return mEmail.getText().toString().trim();
    }

    private boolean verifyEmail(){
        if(getEmail() == null || getEmail().equals("")){
            return false;
        }else {
            return true;
        }
    }

    private String getDiagnostics(){
        return mValueResults.getText().toString().trim();
    }

    private boolean verifyDiagnostics(){
        if(getDiagnostics() == null || getDiagnostics().equals("")){
            return false;
        }else {
            return true;
        }
    }

    private void sendEmail(){
        if(verifyEmail() && verifyDiagnostics() && verifyPatient()){

            SendMail sm = new SendMail(ctx, getEmail(), "DIAGNÓSTICO NUTRICIONAL",
                    "Nombre del paciente: "+getPatient()+"\nDiagnóstico: "+getDiagnostics());
            sm.execute();
            //next(GratitudeActivity.class, false);
        }else{
            if(getEmail().equals("")){
                Toast.makeText(ctx, "Ingrese un email", Toast.LENGTH_SHORT).show();
            }else if(getDiagnostics().equals("")){
                Toast.makeText(ctx, "Ingrese su diagnóstico", Toast.LENGTH_SHORT).show();
            }else if(getPatient().equals("")){
                Toast.makeText(ctx, "Ingrese el nombre del paciente", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void nextActivity(){
        sendEmail();
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
