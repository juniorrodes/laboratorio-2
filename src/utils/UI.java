package utils;

import cinema.exceptions.InvalidBookingException;
import services.Booking;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import static cinema.Room.MAX_COLUMN_VALUE;

public class UI {
    private static final int RESERVE = 1;
    private static final int CANCEL_RESERVE = 2;
    private static final int DISPLAY_MAP_AND_COUNT = 3;
    private static final int QUIT = 4;

    private Booking booking;
    private static final char rows[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L' };
    private Scanner in;

    public UI() {
        this.booking = new Booking();
    }

    public void menu() {
        do {
            printLine("MENU");
            printLine("[1] Reserve");
            printLine("[2] Cancel Reserve");
            printLine("[3] Display map and count");
            printLine("[4] Quit");
            printLine("Choose your option: ");
        } while (this.checkSelection() != QUIT);
    }

    public byte checkSelection() {
        byte selection = this.getSelection();

        switch (selection) {
            case RESERVE:
                this.setSeatReservation(true);
                break;
            case CANCEL_RESERVE:
                this.setSeatReservation(false);
                break;
            case  DISPLAY_MAP_AND_COUNT:
                this.printMap();
            default:
                this.printLine("Please insert a valid option");
                break;
        }

        return selection;
    }


    public void setSeatReservation(boolean reserving) {
        try {
            String seat = this.getStringValue("Choose your seat: ").toUpperCase(Locale.ROOT);
            if (seat.matches("[ABCDEFGHIJKL]\\d\\d")) {
                booking.setSeatState(seat, reserving);
            } else {
                this.printLine("Please insert the seat address with the valid format. \nEx: (A01) or (C13)");
            }
        } catch (InvalidBookingException ex) {
            this.printLine(ex.getMessage());
        }
    }

    public void printMap() {
        int seats[] = this.booking.getRoomSeats();
        this.printLine("This room has " + this.booking.getRoomSeatsBooked());
        for (int i = 0; i < 12; i++) {
            printRow(i, seats);
        }
        this.printLine("\t01\t02\t03\t04\t05\t06\t07\t\t08\t09\t10\t11\t12\t13\t14");

    }

    private void printRow(int i, int seats[]) {
        this.print(rows[i] + "\t");
        for (int j = 0; j < 14; j++) {
            this.print(seats[(i * MAX_COLUMN_VALUE) + j] == 1 ? "[X]" : "[ ]");
            this.print(j == 6 ? "\t\t" : "\t");
        }
        this.printLine(rows[i] + "");
    }

    public void printLine(String message) { System.out.println(message); }

    public void print(String message) { System.out.print(message); }

    private byte getSelection() {
        try {
            this.in = new Scanner(System.in);
            return in.nextByte();
        } catch (InputMismatchException ex) {
            return 0;
        }
    }

    public String getStringValue(String message) {
        this.printLine(message);
        return this.in.next();
    }
}
