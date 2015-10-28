package example.com.zk.customlistfragment.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import example.com.zk.customlistfragment.R;
import example.com.zk.customlistfragment.activity.MainActivity;

public class OnPersonClickListener implements OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Context context = view.getContext();

        TextView textViewName = ((TextView) view.findViewById(R.id.item_person_name));
        TextView textViewAge = ((TextView) view.findViewById(R.id.item_person_age));
        TextView textViewCity = ((TextView) view.findViewById(R.id.item_person_city));

        String name = textViewName.getText().toString();
        String age = textViewAge.getText().toString();
        String city = textViewCity.getText().toString();

        Intent intent = new Intent();
        intent.setAction("example.com.zk.SELECT_PERSON");
        intent.putExtra("SELECT_PERSON_NAME", name);
        intent.putExtra("SELECT_PERSON_AGE", age);
        intent.putExtra("SELECT_PERSON_CITY", city);
        context.sendBroadcast(intent);

        ((MainActivity) context).clearDialog();
    }

}