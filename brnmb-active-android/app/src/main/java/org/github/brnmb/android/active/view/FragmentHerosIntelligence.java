package org.github.brnmb.android.active.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import org.github.brnmb.android.active.R;
import org.github.brnmb.android.active.adapter.RVAdapter;
import org.github.brnmb.android.active.model.Hero;
import org.github.brnmb.android.active.model.HeroAttribute;

import java.util.List;

public class FragmentHerosIntelligence extends Fragment {

    private RecyclerView intelligenceHerosRecyvlerView;

    public static Fragment newInstance(Context context) {
        return new FragmentHerosIntelligence();
    }

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_heros_intelligence, null);

        HeroAttribute intelligence = new HeroAttribute("Intelligence");
        intelligence = new Select()
                .from(HeroAttribute.class)
                .where("hero_attribute_name = ?", intelligence.name)
                .executeSingle();

        List<Hero> storedIntHeros = HeroAttribute.getHerosByAttribute(intelligence);

        RVAdapter adapter = new RVAdapter(storedIntHeros);

        intelligenceHerosRecyvlerView = (RecyclerView) rootView.findViewById(R.id.intelligence_hero_rv);
        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        intelligenceHerosRecyvlerView.setLayoutManager(llm);
        intelligenceHerosRecyvlerView.setAdapter(adapter);

        return rootView;
    }
}
