package gtf.nutricion.test;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_back) Button btnBack;
    @BindView(R.id.btn_next) Button btnNext;

    @BindView(R.id.spb_menu) StateProgressBar mStateProgressBar;

    ArrayList<Fragment> fragments;
    Fragment currentFragment;
    int back = 0;
    int next = 0;
    int current = 0;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        ButterKnife.bind(this);
        btnBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        loadFragments();
    }

    private void loadFragments() {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragmentOne = new FragmentOne(); //0 CONDITION, GENDER, AGE
        Fragment fragmentTwo = new FragmentTwo(); //1 IMC
        Fragment fragmentThree = new FragmentThree(); //1 IMC
        Fragment fragmentFour = new FragmentFour(); //1 IMC
        Fragment fragmentFive = new FragmentFive(); //1 IMC

        fragmentTransaction.add(R.id.container, fragmentOne);
        fragmentTransaction.add(R.id.container, fragmentTwo);
        fragmentTransaction.add(R.id.container, fragmentThree);
        fragmentTransaction.add(R.id.container, fragmentFour);
        fragmentTransaction.add(R.id.container, fragmentFive);

        fragmentTransaction.commit();
        fragmentTransaction.hide(fragmentTwo);
        fragmentTransaction.hide(fragmentThree);
        fragmentTransaction.hide(fragmentFour);
        fragmentTransaction.hide(fragmentFive);

        currentFragment = fragmentOne;

        fragments = new ArrayList<>();

        fragments.add(fragmentOne);
        fragments.add(fragmentTwo);
        fragments.add(fragmentThree);
        fragments.add(fragmentFour);
        fragments.add(fragmentFive);

        getBackYNext();

    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment fragment;
        switch (view.getId()){
            case R.id.btn_back:
                if(back <= 0){
                    btnBack.setEnabled(false);
                    btnBack.setBackground(getResources().getDrawable(R.color.grey));
                    if (back == 0){
                        fragmentTransaction.hide(currentFragment);
                        fragmentTransaction.show(fragments.get(back));
                        fragmentTransaction.commit();
                        currentFragment = fragments.get(back);

                        currentStep(back);
                    }
                }else{
                    btnNext.setEnabled(true);
                    btnNext.setBackground(getResources().getDrawable(R.color.colorAccent));
                    btnBack.setEnabled(true);
                    btnBack.setBackground(getResources().getDrawable(R.color.colorAccent));

                    fragmentTransaction.hide(currentFragment);
                    fragmentTransaction.show(fragments.get(back));
                    fragmentTransaction.commit();
                    currentFragment = fragments.get(back);

                    currentStep(back);
                }

                break;
            case R.id.btn_next:
                if(next == 4){
                    btnNext.setEnabled(false);
                    btnNext.setBackground(getResources().getDrawable(R.color.grey));

                    fragmentTransaction.hide(currentFragment);
                    fragmentTransaction.show(fragments.get(next));
                    fragmentTransaction.commit();
                    currentFragment = fragments.get(next);

                    currentStep(next);
                }else{
                    btnBack.setEnabled(true);
                    btnBack.setBackground(getResources().getDrawable(R.color.colorAccent));
                    btnNext.setEnabled(true);
                    btnNext.setBackground(getResources().getDrawable(R.color.colorAccent));

                    fragmentTransaction.hide(currentFragment);
                    fragmentTransaction.show(fragments.get(next));
                    fragmentTransaction.commit();
                    currentFragment = fragments.get(next);

                    currentStep(next);
                }
                break;
        }

        getBackYNext();

    }

    public void currentStep(int current){
        mStateProgressBar.enableAnimationToCurrentState(true);
        mStateProgressBar.setCurrentStateNumber(getStringName(current));
        mStateProgressBar.checkStateCompleted(true);
        mStateProgressBar.setAnimationDuration(1000);
    }

    public StateProgressBar.StateNumber getStringName(int current){
        if(current == 0){
            return StateProgressBar.StateNumber.ONE;
        }else if(current == 1){
            return StateProgressBar.StateNumber.TWO;
        }else if (current == 2){
            return StateProgressBar.StateNumber.THREE;
        }else if (current == 3){
            return StateProgressBar.StateNumber.FOUR;
        }else if(current == 4){
            return StateProgressBar.StateNumber.FIVE;
        }else{
            return StateProgressBar.StateNumber.ONE;
        }
    }

    public void getBackYNext(){
        for (int i=0; i<fragments.size(); i++){
            if(currentFragment == fragments.get(i)){
                if(i == 0){
                    back = i-1;
                    next = i+1;
                }else if(i == 1){
                    back = i-1;
                    next = i+1;
                }else if(i == 2){
                    back = i-1;
                    next = i+1;
                }else if(i == 3){
                    back = i-1;
                    next = i+1;
                }else if(i == 4){
                    back = i-1;
                    next = i+1;
                }
                current = i;
            }
        }

        Log.d(TAG, "Fragment Back: -----------------------> "+back);
        Log.d(TAG, "Fragment Current: --------------------> "+current);
        Log.d(TAG, "Fragnent Next: -----------------------> "+next);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
