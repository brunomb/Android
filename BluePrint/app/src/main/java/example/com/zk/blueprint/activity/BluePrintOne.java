package example.com.zk.blueprint.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import example.com.zk.blueprint.view.CircleView;
import example.com.zk.blureprint.R;

import java.util.ArrayList;

public class BluePrintOne extends AppCompatActivity {

    private Context mContext;
    private ArrayList<CircleView> circles;
    private FrameLayout bluePrint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_print_one);
        mContext = this;
        bluePrint  = (FrameLayout) findViewById(R.id.blue_print_bg);
        bluePrint.setTag("blue_print");

        circles = new ArrayList<>();

        bluePrint.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.i(">>>>>>>>>>>>>>>>>>>>>>", v.getTag() + " <");

                    CircleView c = new CircleView(mContext, event.getX(), event.getY(), getResources().getColor(R.color.black), getResources().getColor(R.color.red));
                    c.setTag("ZONE 1");
                    Log.i("Event", "" + event.getX() + ", " + event.getY());
                    Log.i("Size", circles.size() + " <");
                    bluePrint.addView(c);
                }
                return true;
            }
        });
    }
}
