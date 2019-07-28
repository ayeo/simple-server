import java.io.IOException;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {
        try {
            new Server(9000, Executors.newFixedThreadPool(3));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
