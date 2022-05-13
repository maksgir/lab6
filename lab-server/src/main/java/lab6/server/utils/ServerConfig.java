package lab6.server.utils;

import lab6.common.abstractions.AbstractCommand;
import lab6.common.abstractions.AbstractServerCommand;

import java.util.HashMap;

public final class ServerConfig {

    private static final HashMap<String, AbstractCommand> CLIENT_AVAILABLE_COMMANDS = new HashMap<>();
    private static final HashMap<String, AbstractServerCommand> SERVER_AVAILABLE_COMMANDS = new HashMap<>();
    private static boolean isRunning = true;

    private ServerConfig() {
    }

    public static boolean getRunning() {
        return isRunning;
    }

    public static void toggleRun() {
        isRunning = !isRunning;
    }


    public static HashMap<String, AbstractCommand> getClientAvailableCommands() {
        return CLIENT_AVAILABLE_COMMANDS;
    }
    public static HashMap<String, AbstractServerCommand> getServerAvailableCommands() {
        return SERVER_AVAILABLE_COMMANDS;
    }


}