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
    private List<Flight> MyFlights = new ArrayList<>();
    private Random rand = new Random();

    public List<Flight> getMyFlights() {
        return MyFlights;
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
                Objects.equals(MyFlights, client.MyFlights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, surname, MyFlights, rand);
    }


    public String toString() {
        return "Client{" +
                "UserId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", MyFlights=" + MyFlights +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void addFlight(Flight flight) {
        MyFlights.add(flight);

    }

    public boolean cancelFlight(Flight flight) {
        if (!MyFlights.contains(flight)) {
            return false;
        }
        MyFlights.remove(flight);
        return true;
    }

    public int getUserId() {
        return userId;
    }
}
