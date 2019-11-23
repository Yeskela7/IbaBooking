
import booking.Client;
import dao.controllers.BookingDaoController;
import dao.controllers.FlightsDaoController;
import flights.Flight;
import flights.DataFlight;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Console {

    private FlightsDaoController fc = new FlightsDaoController();
    private BookingDaoController bc = new BookingDaoController();
    private Scanner scan = new Scanner(System.in);
    private DataFlight df = new DataFlight();
    private ArrayList<Flight> flights;

    public Console() throws ParseException, IOException, ClassNotFoundException {
        Random random = new Random();
        if (df.loadFlight() == null) {
            for (int x = 0; x < random.nextInt(20) + 20; x++)
                fc.createRandomFlight();
            df = new DataFlight(fc);
        } else {
            flights = df.loadFlight();
            for (Flight flight : flights) {
                fc.addFlight(flight);
            }
            df = new DataFlight(fc);
        }
    }

    public void printer(String message) {
        System.out.print(message);
    }

    public void searchAndBook() throws IOException, ClassNotFoundException, ParseException {
        String city, data;
        long date;
        int people, userSelection;

        printer("Please enter destination city : ");
        city = scan.next();
        printer("Please enter date\n");
        data = "";
        printer("Enter 'Time' in format HH:mm : ");
        data += scan.next();
        data += " ";
        printer("Enter 'Date' in format dd/MM/yyyy : ");
        data += scan.next();
        date = converter.DateConverter.stringToMills(data);
        if (date == 0) {
            printer("Wrong date input. Please enter date cin correct format. \n");
            return;
        }
        printer("Please how many people will travel : ");
        try {
            people = scan.nextInt();
            ArrayList<Flight> cFlight = fc.getAvailableFlight(city, people, new Date(date));
            System.out.println("\nMost similar results :");
            printer(cFlight.toString() + "\n");
            printer("Select any available flights above : ");
            userSelection = scan.nextInt();
            if (cFlight.stream().noneMatch(item -> item.getId()
                    == userSelection) || userSelection < 0) {
                System.out.println("Wrong flight id");
                return;
            } else if (userSelection == 0)
                return;
            else {
                for (int a = 0; a < people; a++) {
                    printer("Enter name of passenger : \n");
                    String name = scan.next();
                    printer("Enter surname of passenger : \n");
                    String surname = scan.next();
                    printer("Enter user ID of passenger : \n");
                    int userId = scan.nextInt();
                    Client client = new Client(userId, name, surname);
                    fc.addClient(userSelection, client);
                    bc.addToDataBase(client);
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("Wrong input!");
        }
    }

    public void showFlightInfo() throws IOException, ClassNotFoundException {
        printer("Please enter flight id : \n");
        try {
            int query = scan.nextInt();
            if (fc.getFlightById(query) == null) {
                printer("No flight with this Id. ");
            } else printer(fc.getFlightById(query).toString());
        } catch (InputMismatchException ex) {
            printer("Wrong input. ");
        }
    }

    public void showFlights() throws IOException, ClassNotFoundException {
        printer("All available flights and their info :\n ");
        fc.getAllFlight().forEach(item -> System.out.println(item.toString()));
    }

    public void cancelBooking() throws IOException, ClassNotFoundException {
        try {
            printer("Enter your  id : ");
            int userId = scan.nextInt();
            printer("Please enter booking id :");
            int bookingId = scan.nextInt();
            try {
                fc.getFlightById(bookingId).getSeats().get(userId).cancelFlight(fc.getFlightById(bookingId));
                fc.getFlightById(bookingId).getSeats().remove(userId);
            } catch (NullPointerException ex) {
                printer("Incorrect input");
            }
        } catch (InputMismatchException ex) {
            System.out.println("Wrong input!");
        }
    }


    public void myFlight() throws IOException, ClassNotFoundException {
        printer("Enter name of passenger : \n");
        String name = scan.next();
        printer("Enter surname of passenger : \n");
        String surname = scan.next();
        for (Flight f : fc.getAllFlight()) {
            for (Client c : f.getSeats().values()) {
                if (c.getName().equals(name) && c.getSurname().equals(surname))
                    c.getMyFlights().forEach(item -> printer(item.toString()));
            }
        }
    }

    public void mainMenu() throws IOException, ClassNotFoundException, ParseException {

        printer("\nMain Menu: \n" +
                "Please enter one of the following command or use just number :\n" +
                "1) Display All Flights\n" +
                "2) Show FLight Info\n" +
                "3) Search and Book flight\n" +
                "4) Cancel Booking\n" +
                "5) My Flights\n" +
                "6) Exit\n" +
                "\n>>>");

        String command = scan.next().toLowerCase().replace(" ", "");
        switch (command) {
            case "displayallflights":
            case "1":
                showFlights();
                break;
            case "showflightinfo":
            case "2":
                showFlightInfo();
                break;
            case "searchandbookflight":
            case "3":
                searchAndBook();
                break;
            case "cancelbooking":
            case "4":
                cancelBooking();
                break;
            case "myflights":
            case "5":
                myFlight();
                break;
            case "exit":
            case "6":
                df = new DataFlight(fc);
                System.exit(0);
        }
    }


}
