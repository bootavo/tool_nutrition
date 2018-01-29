package gtf.nutricion.test.activites;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import gtf.nutricion.test.R;
import gtf.nutricion.test.other.Constants;
import gtf.nutricion.test.utils.ClientSharedPreferences;

/**
 * Created by gtufinof on 17/01/18.
 */

public class Difficult extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title_activity) TextView mTitleActivity;

    @BindView(R.id.tv_back) TextView mBack;
    @BindView(R.id.tv_next) TextView mTVNext;
    @BindView(R.id.v_tab) View mViewTab;
    @BindView(R.id.btn_next) Button btnNext;
    @BindView(R.id.rg_dificults) RadioGroup mRGDifficults;
    @BindView(R.id.tv_error) TextView mError;

    private ClientSharedPreferences mSharedPreferences;
    private Context ctx = this;
    private String TAG = Difficult.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficult);
        initButterKnife();
        init();
        setViewStep();
        checked();
    }

    public void initButterKnife(){
        ButterKnife.bind(this);
    }

    public String getDifficult(){
        int difficult = mRGDifficults.getCheckedRadioButtonId();
        if(difficult == R.id.rb_dificult_up) {
            Constants.CONDITION = "DIFFICULT_UP";
            return "DIFFICULT_UP";
        }else if(difficult == R.id.rb_postrate){
            Constants.CONDITION = "POSTRATE";
            return "POSTRATE";
        }else if(difficult == R.id.rb_edema){
            Constants.CONDITION = "EDEMA";
            return "EDEMA";
        }else{
            return "";
        }
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
        FrameLayout.LayoutParams parms = new FrameLayout.LayoutParams(width*3,6);
        mViewTab.setLayoutParams(parms);
    }

    public boolean verify(){

        if(getDifficult().equals("")){
            mError.setVisibility(View.VISIBLE);
            return false;
        }else{
            mError.setVisibility(View.INVISIBLE);
        }
        return true;
    }

    public void checked(){
        mRGDifficults.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_dificult_up:
                        mTVNext.setVisibility(View.VISIBLE);
                        mError.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.rb_postrate:
                        mTVNext.setVisibility(View.VISIBLE);
                        mError.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.rb_edema:
                        mTVNext.setVisibility(View.VISIBLE);
                        mError.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        });
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
        if (verify()) {
            if(Constants.CONDITION.equals("DIFFICULT_UP")){
                next(Step1.class, false);
            }else if(Constants.CONDITION.equals("POSTRATE")){
                next(PostrateStep1.class, false);
            }else if(Constants.CONDITION.equals("EDEMA")){
                next(EdemaStep1.class, false);
            }
        } else {
            Toast.makeText(ctx, "Complete los campos", Toast.LENGTH_SHORT).show();
        }
    }

}
