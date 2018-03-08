package gtf.nutricion.test.activites;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import gtf.nutricion.test.R;
import gtf.nutricion.test.other.Constants;
import gtf.nutricion.test.utils.ClientSharedPreferences;

/**
 * Created by bootavo on 1/27/18.
 */

public class Age extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title_activity) TextView mTitleActivity;

    @BindView(R.id.et_age) EditText mAge;
    @BindView(R.id.tv_back) TextView mBack;
    @BindView(R.id.tv_next) TextView mTVNext;
    @BindView(R.id.v_tab) View mViewTab;
    @BindView(R.id.btn_next) Button btnNext;
    @BindView(R.id.tv_age_error) TextView mAgeError;
    @BindView(R.id.v_age) View mView;

    private ClientSharedPreferences mSharedPreferences;
    private Context ctx = this;
    private String TAG = Age.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
        initButterKnife();
        init();
        setViewStep();
        clearFields();
    }

    public void initButterKnife(){
        ButterKnife.bind(this);
    }

    public String getAge(){
        return mAge.getText().toString();
    }

    public void init(){
        btnNext.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mTVNext.setOnClickListener(this);
        mSharedPreferences = new ClientSharedPreferences();
    }

    public void setViewStep(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/8;
        int height = size.y;
        FrameLayout.LayoutParams parms = new FrameLayout.LayoutParams(width*2,6);
        mViewTab.setLayoutParams(parms);
    }

    public void clearFields(){
        mAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(verify()){
                    mAgeError.setVisibility(View.INVISIBLE);
                    mView.setBackgroundColor(getResources().getColor(R.color.secondary_text));
                    mTVNext.setVisibility(View.VISIBLE);
                    Constants.AGE = Integer.parseInt(mAge.getText().toString());
                }else {
                    mAgeError.setVisibility(View.VISIBLE);
                    mView.setBackgroundColor(getResources().getColor(R.color.red));
                    mTVNext.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public boolean verify(){
        Log.d("asdasdadasd", "-----------------------> "+getAge());
        if(getAge() == null || getAge().equals("")){
            return false;
        }

        if(getAge().equals("Fecha de Nacimiento")){
            return false;
        }

        if(getAge().length() == 0){
            return false;
        }

        if(Integer.parseInt(getAge()) < 18 || Integer.parseInt(getAge()) > 130){
            return false;
        }
        return true;
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

    public void nextActivity(){
        if(verify()){
            next(Difficult.class, false);
        }else{
            Toast.makeText(ctx, "Complete los campos", Toast.LENGTH_SHORT).show();
        }
    }

}
