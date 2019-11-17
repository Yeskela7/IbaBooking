package controller;

import booking.service.Client;
import converter.DateConverter;
import flights.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FlightsControllerTest {

    private FlightsController fc;
    private Flight flight1;
    private Flight flight2;
    private Flight flight3;
    private Client client;

    @BeforeEach
    void setUp() throws ParseException {
        this.fc = new FlightsController();
        this.flight1 = new Flight(1337, 20, "12:00 02/11/2019",
                "15:00 02/11/2019", "Baku", "Kiev");
        this.flight2 = new Flight(2000, 20, "15:00 02/11/2019",
                "19:00 02/11/2019", "Baku", "London");
        this.flight3 = new Flight(2120, 20, "16:00 02/11/2019",
                "19:00 02/11/2019", "Baku", "Paris");
        this.client = new Client(1020, "Markus", "Berg");
    }

    @Test
    void addFlightTest() throws IOException, ClassNotFoundException {
        assertEquals(0, fc.getAllFlight().size());
        fc.addFlight(flight1);
        assertEquals(1, fc.getAllFlight().size());
    }

    @Test
    void getAllFlightTest() throws IOException, ClassNotFoundException {
        assertEquals(0, fc.getAllFlight().size());
        fc.addFlight(flight1);
        assertEquals(1, fc.getAllFlight().size());
        fc.addFlight(flight2);
        assertEquals(2, fc.getAllFlight().size());
    }

    @Test
    void getAvailableFlightDateTest1() throws IOException, ClassNotFoundException, ParseException {
        fc.addFlight(flight1);
        fc.addFlight(flight2);
        fc.addFlight(flight3);
        Date date1 = new Date();
        date1.setTime(DateConverter.stringToMills("11:00 02/11/2019"));
        assertEquals(1, fc.getAvailableFlight("Kiev", 1, date1).size());
    }

    @Test
    void getAvailableFlightDateTest2() throws IOException, ClassNotFoundException, ParseException {
        fc.addFlight(flight1);
        fc.addFlight(flight2);
        fc.addFlight(flight3);
        Date date = new Date();
        date.setTime(DateConverter.stringToMills("13:00 02/11/2019"));
        assertEquals(1, fc.getAvailableFlight("Kiev", 1, date).size());
    }

    @Test
    void getAvailableFlightCityTest() throws IOException, ClassNotFoundException, ParseException {
        fc.addFlight(flight1);
        fc.addFlight(flight2);
        fc.addFlight(flight3);
        Date date = new Date();
        date.setTime(DateConverter.stringToMills("13:00 02/11/2019"));
        assertEquals(0, fc.getAvailableFlight("Madrid", 1, date).size());
    }

    @Test
    void getAvailableFlightSeatsTest1() throws IOException, ClassNotFoundException, ParseException {
        fc.addFlight(flight1);
        fc.addFlight(flight2);
        fc.addFlight(flight3);
        Date date = new Date();
        date.setTime(DateConverter.stringToMills("13:00 02/11/2019"));
        assertEquals(0, fc.getAvailableFlight("Kiev", 21, date).size());
    }

    @Test
    void getAvailableFlightSeatsTest2() throws IOException, ClassNotFoundException, ParseException {
        fc.addFlight(flight1);
        fc.addFlight(flight2);
        fc.addFlight(flight3);
        Date date = new Date();
        date.setTime(DateConverter.stringToMills("13:00 02/11/2019"));
        assertEquals(1, fc.getAvailableFlight("Kiev", 19, date).size());
    }

    @Test
    void addClientTest() throws IOException, ClassNotFoundException {
        fc.addFlight(flight1);
        assertEquals(20, fc.getFlightById(1337).getNumberOfFreeSeats());
        fc.addClient(1337, client);
        assertEquals(19, fc.getFlightById(1337).getNumberOfFreeSeats());
    }

    @Test
    void removeClientTest() throws IOException, ClassNotFoundException {
        fc.addFlight(flight1);
        fc.addClient(1337, client);
        assertEquals(19, fc.getFlightById(1337).getNumberOfFreeSeats());
        fc.removeClient(1337, client);
        assertEquals(20, fc.getFlightById(1337).getNumberOfFreeSeats());
    }

    @Test
    void getFlightByIdTest() throws IOException, ClassNotFoundException {
        fc.addFlight(flight1);
        fc.addFlight(flight2);
        fc.addFlight(flight3);
        assertEquals("Kiev", fc.getFlightById(1337).getDestinationCity());
    }
}