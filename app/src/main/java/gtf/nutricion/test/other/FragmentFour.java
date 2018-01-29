package gtf.nutricion.test.other;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gtf.nutricion.test.R;

/**
 * Created by bootavo on 21/12/2017.
 */

public class FragmentFour extends Fragment{

    private View mView;
    private Context ctx;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_four, container, false);
        ctx = container.getContext();
        return mView;
    }

}
