package gtf.nutricion.test.other;

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
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gtf.nutricion.test.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_back) Button btnBack;
    @BindView(R.id.btn_next) Button btnNext;

    @BindView(R.id.spb_menu) StateProgressBar mStateProgressBar;

    ArrayList<Fragment> fragments;
    Fragment currentFragment;
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
        //fragmentTransaction.add(R.id.container, fragmentTwo);
        //fragmentTransaction.add(R.id.container, fragmentThree);
        //fragmentTransaction.add(R.id.container, fragmentFour);
        //fragmentTransaction.add(R.id.container, fragmentFive);

        fragmentTransaction.commit();
        //fragmentTransaction.hide(fragmentTwo);
        //fragmentTransaction.hide(fragmentThree);
        //fragmentTransaction.hide(fragmentFour);
        //fragmentTransaction.hide(fragmentFive);

        fragments = new ArrayList<>();

        fragments.add(fragmentOne);
        fragments.add(fragmentTwo);
        fragments.add(fragmentThree);
        fragments.add(fragmentFour);
        fragments.add(fragmentFive);

        currentFragment = fragments.get(0);

        getBackYNext();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                int back = current-1;
                if(back <= 0){
                    btnBack.setEnabled(false);
                    btnBack.setBackground(getResources().getDrawable(R.color.grey));
                    if (back == 0){
                        showBackView(back);
                    }
                }else{
                    btnNext.setEnabled(true);
                    btnNext.setBackground(getResources().getDrawable(R.color.colorAccent));
                    btnBack.setEnabled(true);
                    btnBack.setBackground(getResources().getDrawable(R.color.colorAccent));
                    showBackView(back);
                }

                break;
            case R.id.btn_next:

                Log.d(TAG, "current fragment switch -------------------------------------------> "+ current);
                int next = current +1;

                if(next == 1){
                    Log.d(TAG, "current fragment -------------------------------------------> "+ next);
                    btnBack.setEnabled(true);
                    btnBack.setBackground(getResources().getDrawable(R.color.colorAccent));
                    btnNext.setEnabled(true);
                    btnNext.setBackground(getResources().getDrawable(R.color.colorAccent));
                    if(FragmentOne.verify()){
                        showNextView(next);
                    }else{
                        Toast.makeText(this, "Por favor complete los datos solicitados", Toast.LENGTH_SHORT).show();
                    }
                }else if(next == 2){
                    Log.d(TAG, "current fragment -------------------------------------------> "+ next);
                    btnBack.setEnabled(true);
                    btnBack.setBackground(getResources().getDrawable(R.color.colorAccent));
                    btnNext.setEnabled(true);
                    btnNext.setBackground(getResources().getDrawable(R.color.colorAccent));

                    if(FragmentTwo.verify()){
                        showNextView(next);
                    }else{
                        Toast.makeText(this, "Por favor complete los datos solicitados", Toast.LENGTH_SHORT).show();
                    }
                }else if(next == 3){
                    Log.d(TAG, "current fragment -------------------------------------------> "+ next);
                    btnBack.setEnabled(true);
                    btnBack.setBackground(getResources().getDrawable(R.color.colorAccent));
                    btnNext.setEnabled(true);
                    btnNext.setBackground(getResources().getDrawable(R.color.colorAccent));
                    showNextView(next);
                }else if(next == 4){
                    Log.d(TAG, "current fragment -------------------------------------------> "+ next);
                    btnNext.setEnabled(false);
                    btnNext.setBackground(getResources().getDrawable(R.color.grey));
                    showNextView(next);
                }
                break;
        }
        getBackYNext();
    }

    public void showBackView(int step){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Log.d(TAG, "CURRENT FRAGMENT BACK: "+currentFragment);
        //fragmentTransaction.hide(currentFragment);
        fragmentTransaction.remove(fragments.get(step+1));
        fragmentTransaction.show(fragments.get(step));
        fragmentTransaction.commit();
        currentFragment = fragments.get(step);
        currentStep(step);
    }

    public void showNextView(int step){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        /*
        if(step == 1){
            fragment = new FragmentTwo();
        }else if(step == 2){
            fragment = new FragmentThree();
        }else if(step == 3){
            fragment = new FragmentFour();
        }else if(step == 4){
            fragment = new FragmentFive();
        }
        */

        fragmentTransaction.hide(currentFragment);
        fragmentTransaction.add(R.id.container, fragments.get(step));
        fragmentTransaction.show(fragments.get(step));
        fragmentTransaction.commit();
        currentFragment = fragments.get(step);
        Log.d(TAG, "CURRENT FRAGMENT NEXT: "+fragments.get(step));
        currentStep(step);
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
                current = i;
            }
        }

        Log.d(TAG, "Fragment Back: -----------------------> "+ (current-1));
        Log.d(TAG, "Fragment Current: --------------------> "+current);
        Log.d(TAG, "Fragnent Next: -----------------------> "+(current+1));

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
