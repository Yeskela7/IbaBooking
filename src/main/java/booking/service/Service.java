package booking.service;

import dao.Dao;
import flights.Flight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Service {

    public Dao<Client> service = new ClientsStorage();
    private File file = new File("./clientsData.txt");

    public boolean cancelBooking(Client client, int FlightId) {

        for(Flight fl:client.getMyFlights()){
            if (fl.getId()==FlightId){
                client.cancelFlight(fl);
                return true;
            }
        }
        return false;
    }

    public void addToDataBase(Client client) throws IOException {
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
}
