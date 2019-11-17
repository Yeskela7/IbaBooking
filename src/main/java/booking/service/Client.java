package booking.service;

import flights.Flight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Client implements Serializable {
    private int userId;
    private String name;
    private String surname;
    private List<Flight> myFlights = new ArrayList<>();
    private Random rand = new Random();

    public List<Flight> getMyFlights() {
        return myFlights;
    }

    public Client(int userId, String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass() || this.hashCode() != that.hashCode()) return false;
        Client client = (Client) that;
        return userId == client.userId &&
                Objects.equals(name, client.name) &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(myFlights, client.myFlights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, surname, myFlights, rand);
    }


    public String toString() {
        return "Client{" +
                "UserId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", MyFlights=" + myFlights +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void addFlight(Flight flight) {
        myFlights.add(flight);

    }

    public boolean cancelFlight(Flight flight) {
        if (!myFlights.contains(flight)) {
            return false;
        }
        myFlights.remove(flight);
        return true;
    }

    public int getUserId() {
        return userId;
    }
}
