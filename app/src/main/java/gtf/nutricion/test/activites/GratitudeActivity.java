package gtf.nutricion.test.activites;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;
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

public class GratitudeActivity extends AppCompatActivity {

    @BindView(R.id.cl_splash) ConstraintLayout mConstraintLayout;
    @BindView(R.id.tv_title) TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gratitude);

        ButterKnife.bind(this);

        /*
        Animation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(0000);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        mTitle.startAnimation(fadeIn);
        */

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
                startActivity(new Intent(GratitudeActivity.this, SplashActivity.class));
                finish();
            }
        }, 3500);

    }
}
