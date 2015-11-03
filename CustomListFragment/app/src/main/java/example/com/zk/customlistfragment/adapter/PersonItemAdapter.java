package example.com.zk.customlistfragment.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import example.com.zk.customlistfragment.R;
import example.com.zk.customlistfragment.activity.CustomRadioButton;

public class PersonItemAdapter extends ArrayAdapter<String[]> {

    private Context mContext;
    private String[][] persons;
    private int mLayoutResourceId;
    private static int mSelectedVariation = -1;

    public PersonItemAdapter(Context context, int resource, String[][] objects) {
        super(context, resource, objects);
        mContext = context;
        persons = objects;
        mLayoutResourceId = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mLayoutResourceId, parent, false);
        }

        String[] person = persons[position];

        RadioButton radio = (RadioButton) convertView.findViewById(R.id.radioButton);

        if (position == mSelectedVariation) {
            radio.setChecked(true);
        } else {
            radio.setChecked(false);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedVariation = position;
                PersonItemAdapter.this.notifyDataSetChanged();
            }
        });

        TextView textViewName = (TextView) convertView.findViewById(R.id.item_person_name);
        textViewName.setText(person[0]);
        TextView textViewAge = (TextView) convertView.findViewById(R.id.item_person_age);
        textViewAge.setText(person[1]);
        TextView textViewCity = (TextView) convertView.findViewById(R.id.item_person_city);
        textViewCity.setText(person[2]);

        return convertView;
    }

}
