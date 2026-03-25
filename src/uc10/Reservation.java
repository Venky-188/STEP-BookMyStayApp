package uc10;

public class Reservation {

    private String reservationId;
    private String roomType;
    private int roomsBooked;

    public Reservation(String reservationId, String roomType, int roomsBooked) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomsBooked = roomsBooked;
    }

    public String getReservationId() { return reservationId; }
    public String getRoomType() { return roomType; }
    public int getRoomsBooked() { return roomsBooked; }
}