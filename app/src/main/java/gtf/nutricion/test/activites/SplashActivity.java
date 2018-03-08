package gtf.nutricion.test.activites;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gtf.nutricion.test.R;

/**
 * Created by bootavo on 20/12/2017.
 */

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.cl_splash) ConstraintLayout mConstraintLayout;
    @BindView(R.id.tv_title) TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        /*
        Animation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(0000);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        mTitle.startAnimation(fadeIn);
        */

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/4;
        int height = size.y;

        Animation anim = new TranslateAnimation(0, 0, -1000, 0);
        anim.setStartOffset(100);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setFillEnabled(true);
        anim.setFillAfter(true);
        anim.setDuration(1000);
        mTitle.setAnimation(anim);

        /*
        mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, Gender.class));
                finish();
            }
        });
        */

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, Gender.class));
                finish();
            }
        }, 3500);

        hideStatusBar();

    }

    public void hideStatusBar(){
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

}
