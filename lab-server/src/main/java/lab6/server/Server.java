package lab6.server;

public final class Server {

    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        ServerWorker serverWorker;
        if (args.length == 1) {
            serverWorker = new ServerWorker(args[0]);

        } else {
            serverWorker = new ServerWorker("C:\\work\\lab6\\lab-server\\src\\main\\java\\lab6\\server\\parser\\Routes.xml");
        }
        serverWorker.startServerWorker();

    }
}
