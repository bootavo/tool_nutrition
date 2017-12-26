package gtf.nutricion.test;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by bootavo on 21/12/2017.
 */

public class FragmentTwo extends Fragment{

    private View mView;
    private Context ctx;
    static Activity activity;
    static String TAG = FragmentTwo.class.getSimpleName();

    static LinearLayout llDifficultUp, llDifficultPostrate;
    static EditText mBodyWeight, mSize;
    static TextView mIMC;

    static EditText mEB, mPA, mPAB, mPB, mPMC, mPMD, mPP, mAR;
    static TextView mBodyWeightPostrate, mSizePostrate, mIMCPpostrate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_two, container, false);
        ctx = container.getContext();
        activity = getActivity();

        llDifficultUp = (LinearLayout) mView.findViewById(R.id.ll_difficult_up);
        llDifficultPostrate = (LinearLayout) mView.findViewById(R.id.ll_difficult_postrate);

        Log.d("CODNITION", " "+Constants.CONDITION);

        if(Constants.CONDITION.equals("DIFFICULT_UP")){
            llDifficultPostrate.setVisibility(View.GONE);
            llDifficultUp.setVisibility(View.VISIBLE);
            init();
        }else if(Constants.CONDITION.equals("POSTRATE")){
            llDifficultUp.setVisibility(View.GONE);
            llDifficultPostrate.setVisibility(View.VISIBLE);
            initPostrate();
        }

        return mView;
    }

    public void init(){
        Log.d(TAG, "---------------------------> Init");
        mBodyWeight = (EditText) mView.findViewById(R.id.et_body_weight);
        mSize = (EditText) mView.findViewById(R.id.et_size);
        mIMC = (TextView) mView.findViewById(R.id.tv_imc);

        mBodyWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                showResult();
            }
        });

        mSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                showResult();
            }
        });

        mIMC.setText("0.00");

        Log.d(TAG, "---------------------------> Fin Init");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        //Log.d("onHiddenChanged", " "+hidden);
        super.onHiddenChanged(hidden);
        //FragmentTransaction ft = getFragmentManager().beginTransaction();
        //ft.detach(this).attach(this).commit();
    }

    public void initPostrate(){
        Log.d(TAG, "---------------------------> InitPostrate");

        mEB = (EditText) mView.findViewById(R.id.et_eb);
        mPA = (EditText) mView.findViewById(R.id.et_pa);
        mPAB = (EditText) mView.findViewById(R.id.et_pab);
        mPB = (EditText) mView.findViewById(R.id.et_pb);
        mPMC = (EditText) mView.findViewById(R.id.et_pmc);
        mPMD = (EditText) mView.findViewById(R.id.et_pmd);
        mPP = (EditText) mView.findViewById(R.id.et_pp);
        mAR = (EditText) mView.findViewById(R.id.et_ar);

        mBodyWeightPostrate = (TextView) mView.findViewById(R.id.tv_body_weight);
        mSizePostrate = (TextView) mView.findViewById(R.id.tv_size);
        mIMCPpostrate = (TextView) mView.findViewById(R.id.tv_imc_postrate);

        mEB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                showResultPostrateBodyWeight();
            }
        });

        mPA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                showResultPostrateBodyWeight();
            }
        });

        mPAB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                showResultPostrateBodyWeight();
            }
        });

        mPB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                showResultPostrateBodyWeight();
            }
        });

        mPMC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                showResultPostrateBodyWeight();
            }
        });

        mPMD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                showResultPostrateBodyWeight();
            }
        });

        mPP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                showResultPostrateBodyWeight();
            }
        });

        mAR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                showResultPostrateSize();
            }
        });

    }

    public static boolean verify(){
        Log.d(TAG, "---------------------------> verify");
        if(Constants.CONDITION.equals("DIFFICULT_UP")){
            if(mBodyWeight.getText().toString().trim() == null || mBodyWeight.getText().toString().trim().equals("")) {
                mBodyWeight.setError("Ingrese un peso");
                return false;
            }

            if(mSize.getText().toString().trim() == null || mSize.getText().toString().trim().equals("")) {
                mSize.setError("Ingrese una talla");
                return false;
            }
            return true;
        }else if(Constants.CONDITION.equals("POSTRATE")){
            if(mEB.getText().toString().trim() == null || mEB.getText().toString().trim().equals("")) {
                mEB.setError("Ingrese la medida");
                return false;
            }

            if(mPA.getText().toString().trim() == null || mPA.getText().toString().trim().equals("")) {
                mEB.setError("Ingrese la medida");
                return false;
            }

            if(mPAB.getText().toString().trim() == null || mPAB.getText().toString().trim().equals("")) {
                mPAB.setError("Ingrese la medida");
                return false;
            }

            if(mPB.getText().toString().trim() == null || mPB.getText().toString().trim().equals("")) {
                mPB.setError("Ingrese la medida");
                return false;
            }

            if(mPMC.getText().toString().trim() == null || mPMC.getText().toString().trim().equals("")) {
                mPMC.setError("Ingrese la medida");
                return false;
            }

            if(mPMD.getText().toString().trim() == null || mPMD.getText().toString().trim().equals("")) {
                mPMD.setError("Ingrese la medida");
                return false;
            }
            if(mPP.getText().toString().trim() == null || mPP.getText().toString().trim().equals("")) {
                mPP.setError("Ingrese la medida");
                return false;
            }

            if(mAR.getText().toString().trim() == null || mAR.getText().toString().trim().equals("")) {
                mAR.setError("Ingrese la medida");
                return false;
            }

        }

        return true;
    }

    public void showResult(){

        float body_weight = getBodyWeight();
        float size = getSize();
        float imc = body_weight / (size*size);

        String imcFormat = String.format("%.2f", imc);
        Constants.IMC = Double.parseDouble(imcFormat);

        Log.d("IMC: ", ""+Constants.IMC);

        AnimateCounter animateCounter = new AnimateCounter.Builder(mIMC)
                .setCount(0f, imc, 2)
                .setDuration(2000)
                .setInterpolator(new DecelerateInterpolator())
                .build();

        animateCounter.execute();
    }

    public void showResultPostrateBodyWeight(){

        Log.d(TAG, "---------------------------> showResultPostrateBodyWeight");

        float eb = getEB();
        float pa = getPA();
        float pab = getPAB();
        float pb = getPB();
        float pmc = getPMC();
        float pmd = getPMD();
        float pp = getPP();

        if (Constants.GENDER.equals("M")){


            float body_weight = (0.22272f * eb) + (1.01586f * pab) + (0.90424f * pmd) + (0.38020f * pp) + (0.32395f * pa) + (0.52246f * pmc) - (91.4048f + 2.48f);

            String bodyWeightFormat = String.format("%.2f", body_weight);
            Constants.BODY_WEIGHT = Double.parseDouble(bodyWeightFormat);

            Log.d("BODY WEIGHT: ", ""+Constants.BODY_WEIGHT);

            AnimateCounter animateCounterBodyWeight = new AnimateCounter.Builder(mBodyWeightPostrate)
                    .setCount(0f, body_weight, 2)
                    .setDuration(2000)
                    .setInterpolator(new DecelerateInterpolator())
                    .build();
            animateCounterBodyWeight.execute();
        }else if(Constants.GENDER.equals("F")){

            float body_weight = (0.26548f * eb) + (0.65723f * pab) + (0.45102f * pmd) + (0.62714f * pp) + (0.35192f * pa) + (0.04222f * Constants.AGE) - (68.0767f + 2.48f);

            String bodyWeightFormat = String.format("%.2f", body_weight);
            Constants.BODY_WEIGHT = Double.parseDouble(bodyWeightFormat);

            Log.d("BODY WEIGHT: ", ""+Constants.BODY_WEIGHT);

            AnimateCounter animateCounterBodyWeight = new AnimateCounter.Builder(mBodyWeightPostrate)
                    .setCount(0f, body_weight, 2)
                    .setDuration(2000)
                    .setInterpolator(new DecelerateInterpolator())
                    .build();
            animateCounterBodyWeight.execute();
            showIMCPostrate();
        }

    }

    public void showResultPostrateSize(){

        Log.d(TAG, "---------------------------> showResultPostrateSize");

        float ar = getAR();
        float size = 0.0f;

        if(Constants.AGE >= 18 && Constants.AGE <= 59){
            if(Constants.GENDER.equals("M")){
                size = (70.25f) + (0.05f * Constants.AGE) + (1.86f * ar);
            }else if (Constants.GENDER.equals("F")){
                size = (1.885f * ar) + (71.85f);
            }
        }else if(Constants.AGE >= 60){
            if(Constants.GENDER.equals("M")){
                size = (64.19f) - (0.04f * Constants.AGE) + (2.02f * ar);
            }else if (Constants.GENDER.equals("F")){
                size = (84.88f) - (0.24f * Constants.AGE) + (1.83f * ar);
            }
        }

        String sizeFormat = String.format("%.2f", size);
        Constants.SIZE = Double.parseDouble(sizeFormat);

        Log.d("SIZE : ", ""+Constants.SIZE);

        AnimateCounter animateCounterBodyWeight = new AnimateCounter.Builder(mSizePostrate)
                .setCount(0f, size, 2)
                .setDuration(2000)
                .setInterpolator(new DecelerateInterpolator())
                .build();
        animateCounterBodyWeight.execute();
        showIMCPostrate();
    }

    static void showIMCPostrate(){
        Log.d(TAG, "---------------------------> showIMCPostrate");
        float body_weight = Float.parseFloat(Constants.BODY_WEIGHT+"");
        float size = Float.parseFloat(Constants.SIZE+"");
        float imc = body_weight / (size*size);

        String imcFormat = String.format("%.2f", imc);
        Constants.IMC = Double.parseDouble(imcFormat);

        Log.d("IMC: ", ""+Constants.IMC);

        AnimateCounter animateCounter = new AnimateCounter.Builder(mIMC)
                .setCount(0f, imc, 2)
                .setDuration(2000)
                .setInterpolator(new DecelerateInterpolator())
                .build();

        animateCounter.execute();
    }

    static float getEB(){
        if(mEB.getText().toString().trim() != null && !mEB.getText().toString().trim().equals("")){
            return Float.parseFloat(mEB.getText().toString());
        }else{
            return 0.1f;
        }
    }

    static float getPA(){
        if(mPA.getText().toString().trim() != null && !mPA.getText().toString().trim().equals("")){
            return Float.parseFloat(mPA.getText().toString());
        }else{
            return 0.1f;
        }
    }

    static float getPAB(){
        if(mPAB.getText().toString().trim() != null && !mPAB.getText().toString().trim().equals("")){
            return Float.parseFloat(mPAB.getText().toString());
        }else{
            return 0.1f;
        }
    }

    static float getPB(){
        if(mPB.getText().toString().trim() != null && !mPB.getText().toString().trim().equals("")){
            return Float.parseFloat(mPB.getText().toString());
        }else{
            return 0.1f;
        }
    }

    static float getPMC(){
        if(mPMC.getText().toString().trim() != null && !mPMC.getText().toString().trim().equals("")){
            return Float.parseFloat(mPMC.getText().toString());
        }else{
            return 0.1f;
        }
    }

    static float getPMD(){
        if(mPMD.getText().toString().trim() != null && !mPMD.getText().toString().trim().equals("")){
            return Float.parseFloat(mPMD.getText().toString());
        }else{
            return 0.1f;
        }
    }

    static float getPP(){
        if(mPP.getText().toString().trim() != null && !mPP.getText().toString().trim().equals("")){
            return Float.parseFloat(mPP.getText().toString());
        }else{
            return 0.1f;
        }
    }

    static float getAR(){
        if(mAR.getText().toString().trim() != null && !mAR.getText().toString().trim().equals("")){
            return Float.parseFloat(mAR.getText().toString());
        }else{
            return 0.1f;
        }
    }

    static void hideInputSoft() {

        final InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            if (activity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    static float getBodyWeight(){
        if(mBodyWeight.getText().toString().trim() == null || mBodyWeight.getText().toString().trim().equals("")){
            return 0.0f;
        }else{
            return Float.parseFloat(mBodyWeight.getText().toString());
        }
    }

    static float getSize(){
        if(mSize.getText().toString().trim() != null && !mSize.getText().toString().trim().equals("")){
            return Float.parseFloat(mSize.getText().toString());
        }else{
            return 0.0f;
        }

    }

}
