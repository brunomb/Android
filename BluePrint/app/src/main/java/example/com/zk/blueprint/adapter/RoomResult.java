package example.com.zk.blueprint.adapter;

public class RoomResult {
    private String mName;
    private String mNumber;
    private String mStatus;

    public RoomResult(String name, String number, String status) {
        super();
        this.mName = name.trim();
        this.mNumber = number.trim();
        this.mStatus = status.trim();
    }

    public String getName() {
        return mName;
    }

    public String getNumber() {
        return mNumber;
    }

    public String getStatus() {
        return mStatus;
    }

    @Override
    public String toString() {
        return mName + " " + "(" + mNumber + ":" + mStatus + ")";
    }
}