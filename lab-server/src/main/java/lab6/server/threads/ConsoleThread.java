package lab6.server.threads;

import lab6.server.utils.CommandManager;
import lab6.server.utils.ServerCommandListener;
import lab6.server.utils.ServerConfig;

public class ConsoleThread extends Thread {

    private final ServerCommandListener serverCommandListener;
    private final CommandManager commandManager;

    public ConsoleThread(ServerCommandListener serverCommandListener, CommandManager commandManager) {
        this.serverCommandListener = serverCommandListener;
        this.commandManager = commandManager;
    }

    @Override
    public void run() {
        while (ServerConfig.getRunning()) {
            String command = serverCommandListener.readCommand();
            System.out.println(commandManager.executeServerCommand(command));
        }
    }

}