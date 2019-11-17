package services;

import booking.service.Client;
import converter.DateConverter;
import dao.Dao;
import flights.Flight;
import flights.FlightRandomGenerator;
import storage.StorageFlights;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class FlightService {

    private Dao<Flight> flightDao = new StorageFlights();

    public ArrayList<Flight> getAllFlight() throws IOException, ClassNotFoundException {
        return flightDao.getAll();
    }

    public ArrayList<Flight> getAvailableFlight(String cities, int freeSeats, Date date)
            throws IOException, ClassNotFoundException {
        ArrayList<Flight> availableFlight;
        availableFlight = (ArrayList<Flight>) flightDao.getAll().stream()
                .filter(flight -> flight.getDestinationCity().equals(cities))
                .filter(flight -> flight.getNumberOfFreeSeats() >= freeSeats)
                .filter(flight -> date.getTime() - flight.getStartingDate() >= -DateConverter.hour(12))
                .filter(flight -> date.getTime() - flight.getStartingDate() < DateConverter.hour(12))
                .collect(Collectors.toList());
        return availableFlight;
    }

    public void addClient(int flightId, Client client) throws IOException, ClassNotFoundException {
        Flight flight = getFlightById(flightId);
        flight.getSeats().put(client.getUserId(), client);
        flightDao.update(flight);
        client.addFlight(flight);
    }

    public void removeClient(int flightId, Client client) throws IOException, ClassNotFoundException {
        Flight flight = getFlightById(flightId);
        flight.getSeats().remove(client.getUserId(), client);
        client.cancelFlight(flight);
    }

    public HashMap<Integer, Client> getPassengers(int flightId) throws IOException, ClassNotFoundException {
        return flightDao.get(flightId).getSeats();
    }

    public void addFlight(Flight flight) throws IOException, ClassNotFoundException {
        flightDao.save(flight);
    }

    public Flight getFlightById(int flightId) throws IOException, ClassNotFoundException {
        return flightDao.getAll().stream()
                .filter(flight1 -> flight1.getId() == flightId)
                .collect(Collectors.toList()).get(0);
    }

    public void createRandomFlight() throws ParseException, IOException, ClassNotFoundException {
        FlightRandomGenerator flightRandom = new FlightRandomGenerator();
        flightDao.save(flightRandom.buildRandom());
    }
}
