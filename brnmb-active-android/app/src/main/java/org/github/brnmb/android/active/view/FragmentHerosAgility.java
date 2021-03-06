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


/**
 * The type Fragment heros agility.
 */
public class FragmentHerosAgility extends Fragment {

    private RecyclerView agilityHerosRecyvlerView;

    /**
     * New instance fragment.
     *
     * @param context the context
     * @return the fragment
     */
    public static Fragment newInstance(Context context) {
        return new FragmentHerosAgility();
    }

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_heros_agility, null);

        HeroAttribute agility = new HeroAttribute("Agility");
        agility = new Select()
                .from(HeroAttribute.class)
                .where("hero_attribute_name = ?", agility.name)
                .executeSingle();

        List<Hero> storedAgiHeros = HeroAttribute.getHerosByAttribute(agility);

        RVAdapter adapter = new RVAdapter(storedAgiHeros);

        agilityHerosRecyvlerView = (RecyclerView) rootView.findViewById(R.id.agility_hero_rv);
        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        agilityHerosRecyvlerView.setLayoutManager(llm);
        agilityHerosRecyvlerView.setAdapter(adapter);

        return rootView;
    }
}
