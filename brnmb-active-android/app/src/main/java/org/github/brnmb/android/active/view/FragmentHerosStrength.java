package org.github.brnmb.android.active.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.github.brnmb.android.active.R;

public class FragmentHerosStrength extends Fragment {

    public static Fragment newInstance(Context context) {
        return new FragmentHerosStrength();
    }

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_heros_strength, null);
    }
}
