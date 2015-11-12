package example.com.zk.blueprint.model;

import example.com.zk.blueprint.enums.RoomStatus;

public class RoomPoint {
    private String mId;
    private String mNumber;
    private float MPositionX;
    private float MPositionY;
    private RoomStatus mStatus;

    public RoomPoint(String id, String number, float positionX, float positionY) {
        mId = id;
        mNumber = number;
        MPositionX = positionX;
        MPositionY = positionY;
    }

    public RoomPoint(String id, String number) {
        mId = id;
        mNumber = number;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public float getPositionX() {
        return MPositionX;
    }

    public void setPositionX(float MPositionX) {
        this.MPositionX = MPositionX;
    }

    public float getPositionY() {
        return MPositionY;
    }

    public void setPositionY(float MPositionY) {
        this.MPositionY = MPositionY;
    }

    public RoomStatus getStatus() {
        return mStatus;
    }

    public void setStatus(RoomStatus mStatus) {
        this.mStatus = mStatus;
    }
}
