import java.util.concurrent.Callable;

class ResponseRenderer implements Callable<String> {
    public String call() {
        return "Hello world";
    }
}