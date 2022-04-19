package services;

import cinema.Room;
import cinema.exceptions.InvalidBookingException;
import cinema.Seat;

public class Booking {

    private Room room;

    public Booking() {
        this.room = new Room();
    }

    public void setSeatState(String seatLocation, boolean reserving) throws InvalidBookingException {
        try {
            Seat seat = getSeat(seatLocation);
            this.room.setSeat(seat, reserving);
        } catch (InvalidBookingException ex) {
            throw ex;
        }
    }

    private Seat getSeat(String seat) {
        return new Seat(seat.substring(0, 1).charAt(0), Integer.parseInt(seat.substring(1, 3)));
    }

    public int getRoomSeatsBooked() {
        return room.getCount();
    }

    public int[] getRoomSeats() {
        return this.room.getSeats();
    }
}
