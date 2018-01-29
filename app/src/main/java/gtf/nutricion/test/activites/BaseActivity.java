package gtf.nutricion.test.activites;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

import gtf.nutricion.test.R;

/**
 * Created by bootavo on 20/11/2017.
 */



public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //Method better thet start activity
    public void next(Class<?> activity, boolean isDestroy){

        startActivity(new Intent(this,activity));
        if (isDestroy) finish();
    }

    public void next(String name, @Nullable Object object, Class<?> activity, boolean isDestroy){
        Intent intent = new Intent(this,activity);
        if(object != null) intent.putExtra(name, (Serializable) object);
        startActivity(intent);
        if (isDestroy) finish();
    }

    public void next(String name, String data, Class<?> activity, boolean isDestroy){

        Intent intent = new Intent(this,activity);
        if(data != null) intent.putExtra(name, data);
        startActivity(intent);
        if (isDestroy) finish();
    }

}