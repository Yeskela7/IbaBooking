import booking.Client;
import dao.services.BookingDaoService;
import flights.Flight;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BookingDaoServiceTest {

    private BookingDaoService bookingDaoService = new BookingDaoService();


    @Test
    void equals() {
        Client client1 = new Client(12, "Dexter", "Johnson");
        Client client2 = new Client(13, "Sasha", "Petrov");

        assertEquals(false, client1.equals(client2));

        Client client3 = new Client(31, "Dexter", "Johnson");
        assertEquals(false, client1.equals(client3));
    }

    @Test
    void get() throws IOException, ClassNotFoundException {
        Client client = new Client(44, "Petr", "Mitrich");
        assertNull(bookingDaoService.service.get(2));

        bookingDaoService.service.save(client);
        assertEquals(client, bookingDaoService.service.get(44));
    }

    @Test
    void cancelBooking() throws ParseException {
        Client client = new Client(2, "Javid", "Mammadli");
        Map<Integer, Client> map = new HashMap<>();
        map.put(10, client);

        Flight flight = new Flight(2, 100, "11:21 01/11/2019", "11:21 21/11/2019", "London", "New-York");

        client.addFlight(flight);
        assertEquals(true, bookingDaoService.cancelBooking(client, 2));

        Flight flight2 = new Flight(3, 111, "11:21 01/11/2019", "11:21 21/11/2019", "London", "New-York");
        Flight flight3 = new Flight(4, 10, "11:21 01/11/2019", "11:21 21/11/2019", "London", "New-York");
        Flight flight4 = new Flight(5, 41, "11:21 01/11/2019", "11:21 21/11/2019", "London", "New-York");
        client.addFlight(flight);
        client.addFlight(flight2);
        client.addFlight(flight3);
        client.addFlight(flight4);

        assertEquals(true, bookingDaoService.cancelBooking(client, 2));
        assertEquals(true, bookingDaoService.cancelBooking(client, 3));
        assertEquals(false, bookingDaoService.cancelBooking(client, 2121));
        assertEquals(true, bookingDaoService.cancelBooking(client, 4));
        assertEquals(true, bookingDaoService.cancelBooking(client, 5));
    }

}
