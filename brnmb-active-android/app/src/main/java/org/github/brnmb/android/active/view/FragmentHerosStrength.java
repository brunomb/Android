package org.github.brnmb.android.active.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.activeandroid.query.Select;
import org.github.brnmb.android.active.R;
import org.github.brnmb.android.active.adapter.RVAdapter;
import org.github.brnmb.android.active.model.Hero;
import org.github.brnmb.android.active.model.HeroAttribute;

import java.util.List;

public class FragmentHerosStrength extends Fragment {

    private RecyclerView strengthHerosRecyvlerView;

    public static Fragment newInstance(Context context) {
        return new FragmentHerosStrength();
    }

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_heros_strength, null);

        HeroAttribute strength;

        strength = new Select()
                .from(HeroAttribute.class)
                .where("hero_attribute_name = ?", "Strength")
                .executeSingle();

        List<Hero> storedStrHeros = HeroAttribute.getHerosByAttribute(strength);

        Log.v("Size", " " + storedStrHeros.size());

        RVAdapter adapter = new RVAdapter(storedStrHeros);

        strengthHerosRecyvlerView = (RecyclerView) rootView.findViewById(R.id.strength_hero_rv);
        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        strengthHerosRecyvlerView.setLayoutManager(llm);
        strengthHerosRecyvlerView.setAdapter(adapter);

        return rootView;
    }
}
