import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;

public class Server {

    private ServerSocket server;
    private Executor executor;

    public Server(int port, Executor executor) throws IOException {
        this.executor = executor;
        this.server = new ServerSocket(port);
        while(true) checkConnections();
    }

    private void checkConnections() throws IOException {
        Socket connection = this.server.accept();
        RequestHandler handler = RequestHandler.build(connection);
        this.executor.execute(handler);
    }
}
