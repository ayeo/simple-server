import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class RequestHandler extends FutureTask<String> {
    private PrintWriter printer;
    private Socket connection;

    private RequestHandler(Callable responseRenderer, Socket connection) throws IOException {
        super(responseRenderer);
        this.printer = new PrintWriter(connection.getOutputStream(), true);
        this.connection = connection;
    }

    public static RequestHandler build(Socket connection) throws IOException {
        return new RequestHandler(new ResponseRenderer(), connection);
    }

    @Override
    public void done() {
        try {
            this.printer.println(get());
            this.connection.shutdownOutput();
        } catch (InterruptedException e) {
            this.printer.println(e);
        } catch (ExecutionException e) {
            this.printer.println(e);
        } catch (IOException e) {
            //log me
        }
    }
}