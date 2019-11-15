import java.io.IOException;
import java.text.ParseException;

public class MainApp {
    public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException {
        Console temp = new Console();

        while (true)
            temp.mainMenu();
    }

}
