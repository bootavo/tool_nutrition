package gtf.nutricion.test.activites;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gtf.nutricion.test.R;
import gtf.nutricion.test.other.Constants;
import gtf.nutricion.test.utils.ClientSharedPreferences;

/**
 * Created by bootavo on 1/27/18.
 */

public class Gender extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title_activity)
    TextView mTitleActivity;

    @BindView(R.id.cv_male)
    CardView mMale;
    @BindView(R.id.cv_female) CardView mFemale;
    @BindView(R.id.v_tab)
    View mViewTab;
    @BindView(R.id.tv_back) TextView mBack;

    private ClientSharedPreferences mSharedPreferences;
    private Context ctx = this;
    private String TAG = Gender.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        initButterKnife();
        setViewStep();
        init();
    }

    public void initButterKnife(){
        ButterKnife.bind(this);
    }

    public void init(){
        mMale.setOnClickListener(this);
        mFemale.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mSharedPreferences = new ClientSharedPreferences();
    }

    public void setViewStep(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/8;
        int height = size.y;
        FrameLayout.LayoutParams parms = new FrameLayout.LayoutParams(width,6);
        mViewTab.setLayoutParams(parms);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_male:
                Constants.GENDER = "M";
                nextActivity();
                break;
            case R.id.cv_female:
                Constants.GENDER = "F";
                nextActivity();
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }

    public void nextActivity(){
        next(Age.class, false);
    }

}
