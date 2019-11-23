package dao.services;

import booking.Client;
import dao.storages.BookingDaoStorage;
import dao.interfaces.Dao;
import flights.Flight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BookingDaoService {

    public Dao<Client> service = new BookingDaoStorage();
    private File file = new File("./clientsData.txt");

    public boolean cancelBooking(Client client, int FlightId) {

        for (Flight fl : client.getMyFlights()) {
            if (fl.getId() == FlightId) {
                client.cancelFlight(fl);
                return true;
            }
        }
        return false;
    }

    public void addToDataBase(Client client) throws IOException, ClassNotFoundException {
        for (Client oldClient : service.getAll()) {
            if (oldClient.equals(client)) {
                service.update(client);
            } else {
                service.save(client);
            }
        }
        FileWriter writer = new FileWriter(file);
        writer.write("\n");
        writer.write(client.toString());
        writer.close();
    }

    public void myFlights(String name, String surname) throws IOException, ClassNotFoundException {
        for (Client client : service.getAll()) {
            if (client.getName().equals(name) && client.getSurname().equals(surname)) {
                System.out.print("Your flights: ");
                System.out.print(client.getMyFlights());
            }
        }
    }

    public void addFlight(Client client, Flight flight) throws IOException, ClassNotFoundException {
        for (Client oldClient : service.getAll()) {
            if (oldClient.equals(client)) {
                oldClient.addFlight(flight);
                service.update(client);
            } else {
                client.addFlight(flight);
                service.save(client);
            }
        }
    }
}
