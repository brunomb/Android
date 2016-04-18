package org.github.brnmb.android.active.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.activeandroid.query.Select;
import org.github.brnmb.android.active.R;
import org.github.brnmb.android.active.model.Hero;
import org.github.brnmb.android.active.model.HeroAttribute;

import java.util.List;

public class FragmentHome extends Fragment {

    public static Fragment newInstance(Context context) {
        return new FragmentHome();
    }

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, null);

        HeroAttribute agility = new Select()
                .from(HeroAttribute.class)
                .where("hero_attribute_name = ?", "Strength")
                .executeSingle();

        List<Hero> storedAgiHeros = HeroAttribute.getHerosByAttribute(agility);

        Log.i("AGI HEROS"," " + storedAgiHeros.size());

        final Hero agiHero = storedAgiHeros.get(0);

        Button profileButton = (Button) rootView.findViewById(R.id.button_profile);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), HeroProfile.class);
                i.putExtra("hero", agiHero);
                startActivity(i);
            }
        });

        return rootView;
    }
}
