package example.com.zk.blueprint.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

public class ImageCircleView extends ImageView {

    private int circleCol, labelCol;
    private String circleText;
    private Paint circlePaint;
    private float mX;
    private float mY;

    public ImageCircleView(Context context) {
        super(context);
    }

    public ImageCircleView(Context context,  float x, float y, int color1, int color2) {
        super(context);
        Log.i("NEW CIRCLE CREATED", "NEW CIRCLE CREATED " + x + ", " + y);
        setMX(x);
        setMY(y);
        circlePaint = new Paint();
        circleText = "1";
        circleCol = color1;
        labelCol = color2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public float getMX(){
        return mX;
    }

    public float getMY(){
        return mY;
    }

    public void setMX(float a){
        this.mX = a;
    }

    public void setMY(float a){
        this.mY = a;
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
    }
}
