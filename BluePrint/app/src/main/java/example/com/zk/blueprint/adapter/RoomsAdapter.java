package example.com.zk.blueprint.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import example.com.zk.blueprint.model.RoomPoint;
import example.com.zk.blureprint.R;

import java.util.ArrayList;

public class RoomsAdapter extends ArrayAdapter<RoomResult> {

    private Context mContext;
    private ArrayList<RoomResult> results;
    private static int selectedRoom = -1;
    private int mLayoutResourceId;

    public RoomsAdapter(Context context, int resource, ArrayList<RoomResult> data) {
        super(context, resource, data);
        mContext = context;
        results = data;
        mLayoutResourceId = resource;
    }

    public RoomResult getSelectedRoom() {
        if (selectedRoom != -1) {
            return results.get(selectedRoom);
        } else {
            return null;
        }
    }

    public void populateList(ArrayList<RoomPoint> rooms) {
        for (RoomPoint room : rooms) {
            results.add(new RoomResult(room.getId(), room.getNumber(), room.getStatus().getStatus()));
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mLayoutResourceId, parent, false);
        }

        RadioButton radio = (RadioButton) convertView.findViewById(R.id.radioButton);

        if (position == selectedRoom) {
            radio.setChecked(true);
        } else {
            radio.setChecked(false);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRoom = position;
                RoomsAdapter.this.notifyDataSetChanged();
            }
        });

        RoomResult result = results.get(position);

        TextView textViewName = (TextView) convertView.findViewById(R.id.tv_room_name_result);
        textViewName.setText(result.getName());
        TextView textViewNumber = (TextView) convertView.findViewById(R.id.tv_room_number_result);
        textViewNumber.setText(result.getNumber());
        TextView textViewStatus = (TextView) convertView.findViewById(R.id.tv_room_status_result);
        textViewStatus.setText(result.getStatus());

        return convertView;
    }

    public void resetSelected() {
        selectedRoom = -1;
    }
}
