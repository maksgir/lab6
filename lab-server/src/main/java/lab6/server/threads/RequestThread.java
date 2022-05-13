package lab6.server.threads;

import lab6.common.util.Request;
import lab6.common.util.Response;
import lab6.server.utils.CommandManager;
import lab6.server.utils.RoutesCollection;
import lab6.server.utils.ServerConfig;
import lab6.server.utils.ServerSocketWorker;

import java.io.IOException;

public class RequestThread extends Thread {

    private final ServerSocketWorker serverSocketWorker;
    private final CommandManager commandManager;
    private final RoutesCollection routesCollection;

    public RequestThread(ServerSocketWorker serverSocketWorker, CommandManager commandManager, RoutesCollection routesCollection) {
        this.serverSocketWorker = serverSocketWorker;
        this.commandManager = commandManager;
        this.routesCollection = routesCollection;
    }

    @Override
    public void run() {
        while (ServerConfig.getRunning()) {
            try {
                Request acceptedRequest = serverSocketWorker.listenForRequest();
                if (acceptedRequest != null) {
                    routesCollection.sort();
                    System.out.println("Command: "+ acceptedRequest.getCommandName()+" has been accepted");
                    Response responseToSend = commandManager.executeClientCommand(acceptedRequest);
                    serverSocketWorker.sendResponse(responseToSend);
                }
            } catch (ClassNotFoundException e) {
                System.out.println("An error occurred while deserializing the request, try again");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            serverSocketWorker.stopServer();
        } catch (IOException e) {
            System.out.println("An error occurred during stopping the server");
        }
    }
}
