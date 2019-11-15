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
    ;

    public ArrayList<Flight> getAllFlight() throws IOException, ClassNotFoundException {
        return flightDao.getAll();
    }

    public ArrayList<Flight> getAvailableFlight(String cities, int freeSeats, Date date) throws IOException, ClassNotFoundException {
        ArrayList<Flight> availableFlight;
        availableFlight = (ArrayList<Flight>) flightDao.getAll().stream()
                .filter(flight -> flight.getDestinationCity().equals(cities))
                .filter(flight -> flight.getNumberOfFreeSeats() >= freeSeats)
                .filter(flight -> date.getTime() + DateConverter.hour(12) >= flight.getDestinationDate())
                .collect(Collectors.toList());
        availableFlight.addAll(flightDao.getAll().stream()
                .filter(flight -> flight.getDestinationCity().equals(cities))
                .filter(flight -> flight.getNumberOfFreeSeats() >= freeSeats)
                .filter(flight -> date.getTime() - DateConverter.hour(12) <= flight.getDestinationDate())
                .collect(Collectors.toCollection(ArrayList::new)));
        System.out.println(availableFlight);
        return availableFlight;
    }

    public void addClient(int flightId, Client client) throws IOException, ClassNotFoundException {
//        if (flightDao.get(flightId).getNumberOfFreeSeats() > 0) {
//            flightDao.get(flightId).getSeats().put(client.getUserId(), client);
//            flightDao.update(flightDao.get(flightId));
//            client.addFlight(flightDao.get(flightId));
//        } else {
//            System.out.println("add client error");
//        }
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

    public Flight getInfoAboutFlight(int flightId) throws IOException, ClassNotFoundException {
        return getFlightById(flightId);
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
