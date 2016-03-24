package org.github.brnmb.android.active.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.github.brnmb.android.active.R;
import org.github.brnmb.android.active.model.Hero;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.HeroViewHolder> {

    private List<Hero> heros;

    public RVAdapter(List<Hero> heros){
        this.heros = heros;
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_hero, parent, false);
        HeroViewHolder heroViewHolder = new HeroViewHolder(v);
        return heroViewHolder;
    }

    @Override
    public void onBindViewHolder(HeroViewHolder holder, int position) {
        holder.personName.setText(heros.get(position).name);
        holder.personAge.setText(heros.get(position).heroAttribute.name);
    }

    @Override
    public int getItemCount() {
        return heros.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class HeroViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        HeroViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            personName = (TextView) itemView.findViewById(R.id.person_name);
            personAge = (TextView) itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
        }
    }
}

