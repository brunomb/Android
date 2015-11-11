package example.com.zk.blueprint.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import example.com.zk.blureprint.R;

public class CircleView extends View{

    private int circleCol, labelCol;
    private String circleText;
    private Paint circlePaint;
    private float mX;
    private float mY;

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //paint object for drawing in onDraw
        circlePaint = new Paint();
        //get the attributes specified in attrs.xml using the name we included
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LovelyView, 0, 0);

        try {
            //get the text and colors specified using the names in attrs.xml
            circleText = a.getString(R.styleable.LovelyView_circleLabel);
            circleCol = a.getInteger(R.styleable.LovelyView_circleColor, 0);
            labelCol = a.getInteger(R.styleable.LovelyView_labelColor, 0);
        } finally {
            a.recycle();
        }
    }

    public CircleView(Context context,  float x, float y, int color1, int color2) {
        super(context);
        Log.i("NEW CIRCLE CREATED","NEW CIRCLE CREATED " + x +", " + y);
        setMX(x);
        setMY(y);
        circlePaint = new Paint();
        circleText = "1";
        circleCol = color1;
        labelCol = color2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw the View
        //get half of the width and height as we are working with a circle
        int radius = 30;
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);
        //set the paint color using the circle color specified
        circlePaint.setColor(circleCol);
        canvas.drawCircle(getMX(), getMY(), radius, circlePaint);

        Log.i("DRAW: ", " " + getMX() + ", " + getMY() + "<--");
        //set the text color using the color specified
        circlePaint.setColor(labelCol);

        //set text properties
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(20);

        //draw the text using the string attribute and chosen properties
        canvas.drawText(circleText, mX, mY + 8, circlePaint);
    }

    public int getCircleColor(){
        return circleCol;
    }

    public int getLabelColor(){
        return labelCol;
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

    public String getLabelText(){
        return circleText;
    }

    public void setCircleColor(int newColor){
        //update the instance variable
        circleCol=newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }
    public void setLabelColor(int newColor){
        //update the instance variable
        labelCol=newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public void setLabelText(String newLabel){
        //update the instance variable
        circleText=newLabel;
        //redraw the view
        invalidate();
        requestLayout();
    }

}
