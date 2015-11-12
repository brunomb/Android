//package example.com.zk.blueprint.view;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.drawable.Drawable;
//import android.util.Log;
//import android.widget.ImageView;
//
//public class ImageCircleView extends ImageView {
//
//    private int circleCol, labelCol;
//    private String circleText;
//    private Paint circlePaint;
//    private float mX;
//    private float mY;
//
//    //1 is for Potrait and 2 for Landscape.
//    private int initialOrientation;
//
//    public ImageCircleView(Context context) {
//        super(context);
//        //1 is for Potrait and 2 for Landscape.
//        initialOrientation = getResources().getConfiguration().orientation;
//    }
//
//    public ImageCircleView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//
//        //1 is for Potrait and 2 for Landscape.
//        initialOrientation = getResources().getConfiguration().orientation;
//
//        circlePaint = new Paint();
//        //get the attributes specified in attrs.xml using the name we included
//        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LovelyView, 0, 0);
//
//        try {
//            //get the text and colors specified using the names in attrs.xml
//            circleText = a.getString(R.styleable.LovelyView_circleLabel);
//            circleCol = a.getInteger(R.styleable.LovelyView_circleColor, 0);
//            labelCol = a.getInteger(R.styleable.LovelyView_labelColor, 0);
//        } finally {
//            a.recycle();
//        }
//    }
//
//    public int getInitialOrientation() {
//        return this.initialOrientation;
//    }
//
//    public void setInitialOrientation(int initialOrientation) {
//        this.initialOrientation = initialOrientation;
//    }
//
//    public ImageCircleView(Context context,  float x, float y, int color1, int color2) {
//        super(context);
//        Log.i("NEW CIRCLE CREATED", "NEW CIRCLE CREATED " + x + ", " + y);
//        setMX(x);
//        setMY(y);
//        circlePaint = new Paint();
//        circleText = "1";
//        circleCol = color1;
//        labelCol = color2;
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//    }
//
//    public float getMX(){
//        return mX;
//    }
//
//    public float getMY(){
//        return mY;
//    }
//
//    public void setMX(float a){
//        this.mX = a;
//    }
//
//    public void setMY(float a){
//        this.mY = a;
//    }
//
//    @Override
//    public void setBackground(Drawable background) {
//        super.setBackground(background);
//    }
//}
