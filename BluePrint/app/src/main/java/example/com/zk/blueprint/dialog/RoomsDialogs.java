package example.com.zk.blueprint.dialog;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import example.com.zk.blueprint.adapter.RoomResult;
import example.com.zk.blueprint.adapter.RoomsAdapter;
import example.com.zk.blureprint.R;

public class RoomsDialogs extends DialogFragment{

    private static RoomsAdapter mData;
    private ListView results;
    private static int clickedPositionX;
    private static int clickedPositionY;

    public RoomsDialogs(){}

    public static RoomsDialogs newInstance(RoomsAdapter rooms) {
        RoomsDialogs myFragment = new RoomsDialogs();
        myFragment.setRetainInstance(true);
        mData = rooms;
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.dialog_rooms, container, false);
        Button btOk = (Button) rootView.findViewById(R.id.button_ok);
        Button btCancel = (Button) rootView.findViewById(R.id.button_cancel);

        results = (ListView) rootView.findViewById(R.id.lv_search_results);

        results.setAdapter(mData);

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectResult();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    public void setPosition(int x, int y) {
        clickedPositionX = x;
        clickedPositionY = y;
    }

    public void selectResult() {
        if (mData.getSelectedRoom() != null) {
            RoomResult result = mData.getSelectedRoom();
            Intent intent = new Intent();
            intent.setAction("example.com.zk.ROOM_SELECTED");
            intent.putExtra("SELECTED_ROOM_NAME", result.getName());
            intent.putExtra("SELECTED_ROOM_NUMBER", result.getNumber());
            intent.putExtra("SELECTED_ROOM_X", clickedPositionX);
            intent.putExtra("SELECTED_ROOM_Y", clickedPositionY);
            intent.putExtra("SELECTED_ROOM_STATUS", result.getStatus());

            Log.d(" > > > X", " " + clickedPositionX);
            Log.d(" > > > Y", " " + clickedPositionY);

            getActivity().sendBroadcast(intent);
            dismiss();
        }
    }

//    public void showResult(RoomsAdapter data) {
//        setCancelable(true);
//        state = ON_RESULT;
//        mData = data;
//        results.setAdapter(data);
//    }

//    public void showResult() {
//        state = ON_RESULT;
//        results.setAdapter(mData);
//    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }
}