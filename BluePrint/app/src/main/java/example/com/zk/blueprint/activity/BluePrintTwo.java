package example.com.zk.blueprint.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import example.com.zk.blueprint.view.CircleView;
import example.com.zk.blueprint.view.ImageCircleView;
import example.com.zk.blureprint.R;

import java.util.ArrayList;

public class BluePrintTwo extends AppCompatActivity {

    private Context mContext;
    private ArrayList<CircleView> circles;
    private FrameLayout bluePrint;
    static int count = 0;
    static ArrayList<View> views = new ArrayList<>();
    static Integer blueprintHeight;
    static Integer blueprintWidth;
    static Integer initialOrientation;
    static Integer activityHeight;
    static Integer activityWidth;
    private RelativeLayout activityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_print_two);
        activityLayout = (RelativeLayout) findViewById(R.id.activity);
        mContext = this;
        bluePrint = (FrameLayout) findViewById(R.id.blue_print_bg);
        bluePrint.setTag("blue_print");

        circles = new ArrayList<>();


        bluePrint.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (count <= 10) {
                        ImageCircleView c = new ImageCircleView(mContext, event.getX(), event.getY(), getResources().getColor(R.color.black), getResources().getColor(R.color.red));
                        Drawable d = getResources().getDrawable(R.drawable.zone_green);
                        int n = d.getIntrinsicWidth() / 2;
                        c.setBackground(d);
                        c.setTag("ZONE " + count);
                        ArrayList<View> textos = new ArrayList<>(1);
                        TextView tv = new TextView(BluePrintTwo.this);
                        tv.setText("ZONE " + count);
                        textos.add(tv);
                        c.addChildrenForAccessibility(textos);
                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                        params.setMargins((int) event.getX() - n, (int) event.getY() - n, 0, 0);
                        c.setLayoutParams(params);
                        Log.d(">>>> CTAG", c.getTag() + "");
                        c.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v2) {
                                Log.e(">>>>", v2.getTag() + "");
                            }
                        });

                        c.setInitialOrientation(getResources().getConfiguration().orientation);
                        Log.i("Event", "" + event.getX() + ", " + event.getY());
                        Log.i("Initial Orientation", "" + c.getInitialOrientation());
                        Log.i("Size", circles.size() + " <");
                        bluePrint.addView(c);

                        views.add(c);
                        count++;
                    }
                }
                Log.i(">>>>>>>>>>>>>>>>>>>>>>", v.getTag() + " < ");
                return true;
            }
        });

        ViewTreeObserver vtoActivity = activityLayout.getViewTreeObserver();
        vtoActivity.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                activityLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int width = activityLayout.getMeasuredWidth();
                int height = activityLayout.getMeasuredHeight();
                if (activityHeight == null && activityWidth == null) {
                    if (getResources().getConfiguration().orientation == initialOrientation) {
                        activityHeight = height;
                        activityWidth = width;
                    } else {
                        activityHeight = width;
                        activityWidth = height;
                    }
                } else {
//                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//                    params.width = blueprintWidth;
//                    params.height = blueprintHeight;
//
//
//                    activityLayout.setLayoutParams(params);
                }
                Log.d("ActivityLayout - ", "Heigth:" + activityHeight + " --- Width:" + activityWidth);
            }
        });



        ViewTreeObserver vto = bluePrint.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bluePrint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int width = bluePrint.getMeasuredWidth();
                int height = bluePrint.getMeasuredHeight();
                if (blueprintHeight == null && blueprintWidth == null) {
                    if (getResources().getConfiguration().orientation == initialOrientation) {
                        blueprintHeight = height;
                        blueprintWidth = width;
                    } else {
                        blueprintHeight = width;
                        blueprintWidth = height;
                    }
                } else {
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.width = blueprintWidth;
                    params.height = blueprintHeight;


                    bluePrint.setLayoutParams(params);
                }
                Log.d("Blueprint - ", "Heigth:" + blueprintHeight + " --- Width:" + blueprintWidth);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        if (initialOrientation == null) {
            initialOrientation = getResources().getConfiguration().orientation;
        }
        bluePrint.removeAllViewsInLayout();
        if (views != null && !views.isEmpty()) {
            for (View aView : views) {
                Log.d("OnStart", "" + aView.getTag());
                ((FrameLayout) aView.getParent())
                        .removeView(aView);

                bluePrint.addView(aView);
            }
        }

        Log.d("ORIENTATION:", "" + getResources().getConfiguration().orientation);
    }
}
