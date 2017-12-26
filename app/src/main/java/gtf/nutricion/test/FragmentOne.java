package gtf.nutricion.test;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by bootavo on 21/12/2017.
 */

public class FragmentOne extends Fragment {

    static RadioGroup mRGDifficults, mRGGender;
    static RadioButton mDifficultUp, mMale;
    static EditText mAge;

    private View mView;
    private Context ctx;

    static Activity activity;

    private String TAG = FragmentOne.class.getSimpleName();

    private OnNext onNext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_one, container, false);
        ctx = container.getContext();
        activity = getActivity();

        mDifficultUp = (RadioButton) mView.findViewById(R.id.rb_dificult_up);
        mMale = (RadioButton) mView.findViewById(R.id.rb_male);

        mRGDifficults = (RadioGroup) mView.findViewById(R.id.rg_difficults);
        mRGGender = (RadioGroup) mView.findViewById(R.id.rg_gender);

        mAge = (EditText) mView.findViewById(R.id.et_age);

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "-----------------------------------> onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "-----------------------------------> onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "-----------------------------------> onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "-----------------------------------> onPause");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        verify();
        Log.d(TAG, "-----------------------------------> View "+hidden);
        Log.d(TAG, "-----------------------------------> onHiddenChanged");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "-----------------------------------> onDestroyView");
    }

    public static String getGender(){
        int gender = mRGGender.getCheckedRadioButtonId();
        if(gender == R.id.rb_male){
            Constants.GENDER = "M";
            return "M";
        }else if(gender == R.id.rb_female){
            Constants.GENDER = "F";
            return "F";
        }else{
            return "";
        }
    }

    public static String getDifficults(){
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

    public static String getAge(){
        Constants.AGE = Integer.parseInt(mAge.getText().toString());
        return mAge.getText().toString();
    }

    public static boolean verify() {
        if (getGender().equals("")) {
            return false;
        }
        if(getDifficults().equals("")){
            return false;
        }
        if(getAge() == null || getAge().equals("") || Integer.parseInt(getAge()) < 18){
            mAge.setError("Ingrese una edad valida [18+]");
            return false;
        }

        hideInputSoft();
        return true;
    }

    static void hideInputSoft() {

        final InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            if (activity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

}
