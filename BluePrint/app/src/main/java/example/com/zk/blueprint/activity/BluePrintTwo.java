package example.com.zk.blueprint.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import example.com.zk.blueprint.adapter.RoomResult;
import example.com.zk.blueprint.adapter.RoomsAdapter;
import example.com.zk.blueprint.dialog.RoomsDialogs;
import example.com.zk.blueprint.enums.RoomStatus;
import example.com.zk.blueprint.model.RoomPoint;
import example.com.zk.blueprint.view.CircleView;
import example.com.zk.blureprint.R;

import java.util.ArrayList;

public class BluePrintTwo extends AppCompatActivity {

    private Context mContext;
    private ArrayList<CircleView> circles;
    private ArrayList<RoomPoint> rooms = new ArrayList<>();
    private ArrayList<RoomResult> resultList = new ArrayList<>();
    private static int x;
    private static int y;
    private RoomsDialogs roomsDialog;
    private static RoomsAdapter roomsAdapter;
    private FrameLayout bluePrint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_print_two);
        mContext = this;

        bluePrint = (FrameLayout) findViewById(R.id.blue_print_bg);
        bluePrint.setTag("blue_print");

        RoomPoint rp1 = new RoomPoint("Room 01","1");
        RoomPoint rp2 = new RoomPoint("Room 13","13");
        RoomPoint rp3 = new RoomPoint("Room 20","20");
        RoomPoint rp4 = new RoomPoint("Room 42","42");
        rp1.setStatus(RoomStatus.EMPTY);
        rp2.setStatus(RoomStatus.CLEAN);
        rp3.setStatus(RoomStatus.BUSY);
        rp4.setStatus(RoomStatus.DIRTY);
        rooms.add(rp1);
        rooms.add(rp2);
        rooms.add(rp3);
        rooms.add(rp4);

        Receiver mReceiver = new Receiver();
        IntentFilter filter = new IntentFilter("example.com.zk.ROOM_SELECTED");
        this.registerReceiver(mReceiver, filter);

        circles = new ArrayList<>();

        bluePrint.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x = (int) event.getX();
                    y = (int) event.getY();

//                        TextView c = new TextView(mContext);
//                        Drawable d = getResources().getDrawable(R.drawable.zone_green);
//                        int n = d.getIntrinsicWidth()/2;
//                        c.setBackground(d);
//                        c.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
//                        c.setTextColor(getResources().getColor(R.color.white));
//                        c.setTextSize(18);
//                        c.setText((count * 5) + "");
//                        c.setTag("ZONE " + count);
//
//                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
//                        params.setMargins((int) event.getX() - n, (int) event.getY() -n, 0, 0);
//                        c.setLayoutParams(params);
//
//                        c.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v2) {
//                                Log.e(">>>>", v2.getTag() + "");
//                            }
//                        });
//
//                        Log.i("Event", "" + event.getX() + ", " + event.getY());
//                        Log.i("Size", circles.size() + " <");
//                        bluePrint.addView(c);
//                        count++;

//                        ImageCircleView c = new ImageCircleView(mContext, event.getX(), event.getY(), getResources().getColor(R.color.black), getResources().getColor(R.color.red));
//                        Drawable d = getResources().getDrawable(R.drawable.zone_green);
//                        int n = d.getIntrinsicWidth()/2;
//                        c.setBackground(d);
//                        c.setTag("ZONE " + count);
//
//                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
//                        params.setMargins((int) event.getX() - n, (int) event.getY() -n, 0, 0);
//                        c.setLayoutParams(params);
//
//                        c.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v2) {
//                                Log.e(">>>>", v2.getTag() + "");
//                            }
//                        });
//
//                        Log.i("Event", "" + event.getX() + ", " + event.getY());
//                        Log.i("Size", circles.size() + " <");
//                        bluePrint.addView(c);
//                        count++;
                }
                Log.i(">>>>>>>>>>>>>>>>>>>>>>", v.getTag() + " <");
                return false;
            }
        });

        bluePrint.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                createRooms();
                return true;
            }
        });
    }

    private void createRooms() {
        roomsAdapter = new RoomsAdapter(this, R.layout.room_search_result, resultList);
        roomsAdapter.resetSelected();
        roomsAdapter.clear();
        roomsAdapter.populateList(rooms);
        roomsDialog = RoomsDialogs.newInstance(roomsAdapter);
        roomsDialog.show(getFragmentManager(), "dialog_rooms");
        roomsDialog.setPosition(x, y);
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            Log.i("> > > > >","< < < < <");
            String selectedName = arg1.getStringExtra("SELECTED_ROOM_NAME");
            int selectedX = arg1.getIntExtra("SELECTED_ROOM_X", 0);
            int selectedY = arg1.getIntExtra("SELECTED_ROOM_Y", 0);

            drawPoint(selectedName, selectedX, selectedY);
        }
    }

    private void drawPoint(String selectedName, int selectedX, int selectedY) {
        for (RoomPoint room : rooms) {
            if (selectedName.equals(room.getId())) {
                room.setPositionX(selectedX);
                room.setPositionY(selectedY);

                TextView c = new TextView(mContext);
                Drawable d;

                switch (room.getStatus()) {
                    case EMPTY:
                        d = getResources().getDrawable(R.drawable.zone_green);
                        break;
                    case BUSY:
                        d = getResources().getDrawable(R.drawable.zone_red);
                        break;
                    case CLEAN:
                        d = getResources().getDrawable(R.drawable.zone_yellow);
                        break;
                    case DIRTY:
                        d = getResources().getDrawable(R.drawable.zone_blue);
                        break;
                    default:
                        d = getResources().getDrawable(R.drawable.zone_black);
                        break;
                }

                int n = d.getIntrinsicWidth()/2;
                c.setBackground(d);
                c.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                c.setTextColor(getResources().getColor(R.color.white));
                c.setTextSize(18);
                c.setText(room.getNumber());
                c.setTag(room.getId());

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins((int) room.getPositionX() - n, (int) room.getPositionY() - n, 0, 0);
                c.setLayoutParams(params);

                c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {
                        Log.e(">>>>", v2.getTag() + "");
                    }
                });
                bluePrint.addView(c);
            }
        }
    }
}

