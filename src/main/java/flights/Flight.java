package flights;


import converter.DateConverter;

import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;

public class Flight implements Serializable {

    private int id;
    private int numberOfSeats;

    private int numberOfFreeSeats;
    private HashMap<Integer, Client> seats = new HashMap<Integer, Client>(numberOfSeats);

    private long startingDate;
    private long destinationDate;

    private String startingCity;
    private String destinationCity;

    public Flight(int id, int numberOfSeats,
                  HashMap<Integer, Client> seats,
                  String startingDate, String destinationDate,
                  String startingCity, String destinationPoint) throws ParseException {
        this.id = id;
        this.numberOfSeats = numberOfSeats;
        this.seats = seats;
        this.startingDate = DateConverter.stringToMills(startingDate);
        this.destinationDate = DateConverter.stringToMills(destinationDate);
        this.startingCity = startingCity;
        this.destinationCity = destinationPoint;
        this.numberOfFreeSeats = numberOfFreeSeats;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flight{");
        sb.append("id: ").append(id);
        sb.append(", number of seats: ").append(numberOfSeats);
        sb.append(", number of free seats:").append(numberOfFreeSeats);
        sb.append(", seats map: ").append(seats);
        sb.append(", starting date: ").append(DateConverter.millsToString(startingDate)); //Done
        sb.append(", destination date: ").append(DateConverter.millsToString(destinationDate)); //Done
        sb.append(", startingCity: '").append(startingCity).append('\'');
        sb.append(", destinationCity: '").append(destinationCity).append('\'');
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
}
