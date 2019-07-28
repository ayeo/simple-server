import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class RequestHandler extends FutureTask<String> {
    private PrintWriter printer;
    private Socket connection;

    public RequestHandler(Callable responseRenderer, PrintWriter responseOutput, Socket connection) {
        super(responseRenderer);
        this.printer = responseOutput;
        this.connection = connection;
    }

    @Override
    public void done() {
        try {
            this.printer.println(get() + "\r\n");
        } catch (InterruptedException e) {
            this.printer.println(e);
        } catch (ExecutionException e) {
            this.printer.println(e);
        } finally {
            this.printer.flush();
        }

        try {
            this.connection.shutdownOutput();
        } catch (IOException e) {

        }
    }
}