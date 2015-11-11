package example.com.zk.blueprint.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import example.com.zk.blureprint.R;

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
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);
        //set the paint color using the circle color specified
        circlePaint.setColor(circleCol);

        Log.i("DRAW: ", " " + getMX() + ", " + getMY() + "<--");
        //set the text color using the color specified
        circlePaint.setColor(labelCol);

        //set text properties
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(500);

        //draw the text using the string attribute and chosen properties
        canvas.drawText(circleText, mX, mY + 8, circlePaint);
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
