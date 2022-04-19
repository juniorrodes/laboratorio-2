package cinema;

import cinema.exceptions.InvalidBookingException;

public class Room {
    private int seatsStates[];
    private int count;
    public static final int MAX_COLUMN_VALUE = 14;
    public static final int MAX_ROW_VALUE = 12;

    public Room() { this.seatsStates = new int[168]; }

    public void setSeat(Seat seat, boolean reserving) throws InvalidBookingException {
        if ((seat.row() - 'A') > MAX_ROW_VALUE) {
            throw new InvalidBookingException("Row " + seat.row() + " is invalid.");
        }
        if (seat.column() > MAX_COLUMN_VALUE) {
            throw new InvalidBookingException("Column " + seat.column() + " is invalid.");
        }
        int index = (seat.row() - 'A') * MAX_COLUMN_VALUE + seat.column() - 1;
        int seatState = this.seatsStates[index];
        if (reserving && seatState == 1) {
            throw new InvalidBookingException("This seat is already booked");
        }
        this.seatsStates[index] = reserving ? 1 : 0;
        this.count += reserving ? 1 : -1;
    }

    public int getCount() {
        return this.count;
    }

    public int[] getSeats() {
        return this.seatsStates;
    }
}
