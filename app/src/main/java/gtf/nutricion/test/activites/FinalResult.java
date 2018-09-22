package gtf.nutricion.test.activites;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindDimen;
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

    @BindView(R.id.et_value_results) EditText mValueResults;
    @BindView(R.id.et_email) EditText mEmail;
    @BindView(R.id.tv_action_plan) TextView mActionPlan;
    @BindView(R.id.et_patient) EditText mPatient;

    @BindView(R.id.tv_age) TextView mAge;
    @BindView(R.id.tv_date) TextView mDate;
    @BindView(R.id.et_start_date) EditText mStartDate;
    @BindView(R.id.et_history_number) EditText mHistoryNumber;
    @BindView(R.id.et_bed_number) EditText mBedNumber;

    @BindView(R.id.et_place_incoming) EditText mPlaceProcedence;
    @BindView(R.id.et_place_coming) EditText mPlaceResidence;

    private String TAG = FinalResult.class.getSimpleName();
    private Context ctx = this;

    private String date = "";

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

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        date = df.format(Calendar.getInstance().getTime());
        mDate.setText(date);
        mAge.setText(""+ Constants.AGE+" años");

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

            Log.d(TAG, "Nombres "+getPatient());
            Log.d(TAG, "Edad "+mAge.getText().toString() + " - "+ Constants.AGE);
            Log.d(TAG, "Historia: "+mHistoryNumber.getText().toString());
            Log.d(TAG, "Procedencia "+mPlaceProcedence.getText().toString());
            Log.d(TAG, "Residencia "+mPlaceResidence.getText().toString());
            Log.d(TAG, "Nro cama: "+mBedNumber.getText().toString());


            String message = "";
            message = "Información del Paciente: "+ System.getProperty ("line.separator")+System.getProperty ("line.separator")+
                    "Nombres: "+getPatient()+System.getProperty ("line.separator")+
                    "Edad: "+ mAge.getText().toString()+ System.getProperty ("line.separator")+System.getProperty ("line.separator")+
                    "Número de Historia Clínica: "+mHistoryNumber.getText().toString()+System.getProperty ("line.separator")+
                    "Número de Cama: "+mBedNumber.getText().toString()+System.getProperty ("line.separator")+
                    "Diagnóstico: "+getDiagnostics()+System.getProperty ("line.separator")+
                    "Lugar de Procedencia: "+mPlaceProcedence.getText().toString()+System.getProperty ("line.separator")+
                    "Lugar de Residencia: "+mPlaceResidence.getText().toString()+ System.getProperty ("line.separator")+System.getProperty ("line.separator")+
                    "Gracias por usar la aplicación NutriGätjens.";

            SendMail sm = new SendMail(ctx, (Activity) ctx, getEmail(), "DIAGNÓSTICO NUTRICIONAL - "+mDate.getText().toString(), message);
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
