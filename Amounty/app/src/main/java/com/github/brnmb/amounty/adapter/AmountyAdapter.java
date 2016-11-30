package com.github.brnmb.amounty.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.brnmb.amounty.R;
import com.github.brnmb.amounty.model.Amounty;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AmountyAdapter extends RecyclerView.Adapter<AmountyAdapter.AmountyViewHolder> implements Filterable {
    private final List<Amounty> amountyList;
    private final List<Amounty> filteredAmountyList;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AmountyAdapter(List<Amounty> myDataset) {
        this.amountyList = myDataset;
        this.filteredAmountyList = new ArrayList<>(myDataset);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AmountyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View amountyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_amounty, parent, false);
        return new AmountyViewHolder(amountyView);
    }

    @Override
    public void onBindViewHolder(AmountyViewHolder holder, int position) {
        holder.personName.setText(filteredAmountyList.get(position).getName());
        //  holder.personAge.setText(amountyList[position].getCreationDate().toString());

        //  Bitmap bitmap = BitmapFactory.decodeByteArray(heros.get(position).heroImage, 0, heros.get(position).heroImage.length);
        //  holder.personPhoto.setImageBitmap(bitmap);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return filteredAmountyList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class AmountyViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        AmountyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            personName = (TextView) itemView.findViewById(R.id.person_name);
//            personAge = (TextView) itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
        }
    }

    @Override
    public Filter getFilter() {
        Log.v("AmountyList",amountyList.size() + "");
        return new AmountyFilter(this, amountyList);
    }

    public static class AmountyFilter extends Filter {

        private final AmountyAdapter adapter;
        private final List<Amounty> originalList;
        private final List<Amounty> filteredList;

        private AmountyFilter(AmountyAdapter adapter, List<Amounty> originalList) {
            super();
            this.adapter = adapter;
            Log.v("AmountyList2",originalList.size() + "");
            this.originalList = new LinkedList<>(originalList);
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                filteredList.addAll(originalList);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();

                for (final Amounty amounty : originalList) {
                    if (amounty.getName().contains(filterPattern)) {
                        filteredList.add(amounty);
                    }
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.filteredAmountyList.clear();
            adapter.filteredAmountyList.addAll((ArrayList<Amounty>) results.values);
            adapter.notifyDataSetChanged();
        }
    }
}

