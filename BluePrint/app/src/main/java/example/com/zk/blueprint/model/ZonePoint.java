package example.com.zk.blueprint.model;

public class ZonePoint {
    private String mZone;
    private String mNumber;
    private float mXPositon;
    private float mYPosition;

    public ZonePoint(String zone, String number, float xPosition, float yPosition) {
        mZone = zone;
        mNumber = number;
        mXPositon = xPosition;
        mYPosition = yPosition;
    }

    public float getX() {
        return mXPositon;
    }

    public float getY() {
        return mYPosition;
    }

    public String getName() {
        return mZone;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setX(float x) {
        mXPositon = x;
    }

    public void setY(float y) {
        mYPosition = y;
    }
}
