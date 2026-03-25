package uc8;

public class Reservation {

    private String customerName;
    private String roomType;
    private int roomsRequested;

    public Reservation(String customerName, String roomType, int roomsRequested) {
        this.customerName = customerName;
        this.roomType = roomType;
        this.roomsRequested = roomsRequested;
    }

    public String getCustomerName() { return customerName; }
    public String getRoomType() { return roomType; }
    public int getRoomsRequested() { return roomsRequested; }
}
