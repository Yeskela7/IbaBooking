package dao.controllers;

import booking.Client;
import dao.services.BookingDaoService;
import flights.Flight;

import java.io.IOException;

public class BookingDaoController {

    public BookingDaoService bookingDaoService = new BookingDaoService();

    public boolean cancelBooking(Client client, int FlightId) {
        return bookingDaoService.cancelBooking(client, FlightId);
    }

    public void addToDataBase(Client client) throws IOException, ClassNotFoundException {
        bookingDaoService.addToDataBase(client);
    }

    public void myFlights(String name, String surname) throws IOException, ClassNotFoundException {
        bookingDaoService.myFlights(name, surname);
    }
    public void addFlight(Client client, Flight flight) throws IOException, ClassNotFoundException{
        bookingDaoService.addFlight(client,flight);
    }
}