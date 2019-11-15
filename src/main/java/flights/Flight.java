package flights;


import booking.service.Client;
import converter.DateConverter;

import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Objects;

public class Flight implements Serializable {

    private int id;
    private int numberOfSeats;

    private int numberOfFreeSeats;

    private HashMap<Integer, Client> seats;


    private long startingDate;
    private long destinationDate;

    private String startingCity;
    private String destinationCity;

    public Flight(int id, int numberOfSeats,
                  String startingDate, String destinationDate,
                  String startingCity, String destinationPoint) throws ParseException {
        this.id = id;
        this.numberOfSeats = numberOfSeats;
        this.seats = new HashMap<Integer, Client>();
        this.startingDate = DateConverter.stringToMills(startingDate);
        this.destinationDate = DateConverter.stringToMills(destinationDate);
        this.startingCity = startingCity;
        this.destinationCity = destinationPoint;
        this.numberOfFreeSeats = getNumberOfFreeSeats();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flight{");
        sb.append("id: ").append(id);
        sb.append(", number of seats: ").append(numberOfSeats);
        sb.append(", available seats:").append(numberOfFreeSeats);
        sb.append(", starting date: ").append(DateConverter.millsToString(startingDate));
        sb.append(", starting city: '").append(startingCity).append('\'');
        sb.append(", destination city: '").append(destinationCity).append('\'');
        sb.append(", destination date: ").append(DateConverter.millsToString(destinationDate)); 
        sb.append('}');
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public HashMap<Integer, Client> getSeats() {
        return seats;
    }

    public Long getStartingDate() {
        return startingDate;
    }

    public Long getDestinationDate() {
        return destinationDate;
    }

    public String getStartingCity() {
        return startingCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public int getNumberOfFreeSeats() {
        numberOfFreeSeats = numberOfSeats - seats.size();
        return numberOfFreeSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this.hashCode() != o.hashCode()) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id &&
                numberOfSeats == flight.numberOfSeats &&
                startingDate == flight.startingDate &&
                destinationDate == flight.destinationDate &&
                Objects.equals(startingCity, flight.startingCity) &&
                Objects.equals(destinationCity, flight.destinationCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfSeats, startingDate, destinationDate, startingCity, destinationCity);
    }
}
