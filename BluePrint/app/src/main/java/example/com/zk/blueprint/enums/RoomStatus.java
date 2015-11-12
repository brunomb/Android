package example.com.zk.blueprint.enums;

public enum RoomStatus {
    EMPTY ("EMPTY"),
    BUSY ("BUSY"),
    DIRTY ("DIRTY"),
    CLEAN ("CLEAN");

    private String mStatus;

    RoomStatus(String status) {
        this.mStatus = status;
    }

    public String getStatus() {
        return mStatus;
    }
}
