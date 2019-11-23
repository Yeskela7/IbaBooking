package dao.controllers;

import booking.Client;
import flights.Flight;
import dao.services.FlightDaoService;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class FlightsDaoController {

    private FlightDaoService flightDaoService = new FlightDaoService();

    public ArrayList<Flight> getAllFlight() throws IOException, ClassNotFoundException {
        return flightDaoService.getAllFlight();
    }

    public ArrayList<Flight> getAvailableFlight(String cities, int freeSeats, Date date)
            throws IOException, ClassNotFoundException {
        return flightDaoService.getAvailableFlight(cities, freeSeats, date);
    }

    public void addClient(int flightId, Client client) throws IOException, ClassNotFoundException {
        flightDaoService.addClient(flightId, client);
    }

    public void removeClient(int flightId, Client client) throws IOException, ClassNotFoundException {
        flightDaoService.removeClient(flightId, client);
    }

    public HashMap<Integer, Client> getPassengers(int flightId) throws IOException, ClassNotFoundException {
        return flightDaoService.getPassengers(flightId);
    }

    public void addFlight(Flight flight) throws IOException, ClassNotFoundException {
        flightDaoService.addFlight(flight);
    }

    public Flight getFlightById(int flightId) throws IOException, ClassNotFoundException {
        return flightDaoService.getFlightById(flightId);
    }

    public void createRandomFlight() throws ParseException, IOException, ClassNotFoundException {
        flightDaoService.createRandomFlight();
    }

}
