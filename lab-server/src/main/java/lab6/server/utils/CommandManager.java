package lab6.server.utils;

import lab6.common.abstractions.AbstractCommand;
import lab6.common.abstractions.AbstractServerCommand;
import lab6.common.util.Request;
import lab6.common.util.Response;

public class CommandManager {

    public CommandManager(AbstractCommand helpClientCommand,
                          AbstractCommand infoCommand,
                          AbstractCommand showCommand,
                          AbstractCommand addCommand,
                          AbstractCommand updateCommand,
                          AbstractCommand removeByIdCommand,
                          AbstractCommand clearCommand,
                          AbstractCommand executeScriptCommand,
                          AbstractCommand exitCommand,
                          AbstractCommand removeHeadCommand,
                          AbstractCommand addIfMinCommand,
                          AbstractCommand removeLowerCommand,
                          AbstractCommand removeAnyByDistanceCommand,
                          AbstractCommand filterByDistanceCommand,
                          AbstractCommand filterGreaterThanDistance,
                          AbstractServerCommand serverHelpCommand,
                          AbstractServerCommand serverExitCommand,
                          AbstractServerCommand serverSaveCommand) {

        ServerConfig.getClientAvailableCommands().put(helpClientCommand.getName(), helpClientCommand);
        ServerConfig.getClientAvailableCommands().put(infoCommand.getName(), infoCommand);
        ServerConfig.getClientAvailableCommands().put(showCommand.getName(), showCommand);
        ServerConfig.getClientAvailableCommands().put(addCommand.getName(), addCommand);
        ServerConfig.getClientAvailableCommands().put(updateCommand.getName(), updateCommand);
        ServerConfig.getClientAvailableCommands().put(removeByIdCommand.getName(), removeByIdCommand);
        ServerConfig.getClientAvailableCommands().put(clearCommand.getName(), clearCommand);
        ServerConfig.getClientAvailableCommands().put(executeScriptCommand.getName(), executeScriptCommand);
        ServerConfig.getClientAvailableCommands().put(exitCommand.getName(), exitCommand);
        ServerConfig.getClientAvailableCommands().put(removeHeadCommand.getName(), removeHeadCommand);
        ServerConfig.getClientAvailableCommands().put(addIfMinCommand.getName(), addIfMinCommand);
        ServerConfig.getClientAvailableCommands().put(removeLowerCommand.getName(), removeLowerCommand);
        ServerConfig.getClientAvailableCommands().put(removeAnyByDistanceCommand.getName(), removeAnyByDistanceCommand);
        ServerConfig.getClientAvailableCommands().put(filterByDistanceCommand.getName(), filterByDistanceCommand);
        ServerConfig.getClientAvailableCommands().put(filterGreaterThanDistance.getName(), filterGreaterThanDistance);

        ServerConfig.getServerAvailableCommands().put(serverHelpCommand.getName(), serverHelpCommand);
        ServerConfig.getServerAvailableCommands().put(serverExitCommand.getName(), serverExitCommand);
        ServerConfig.getServerAvailableCommands().put(serverSaveCommand.getName(), serverSaveCommand);

    }

    public Response executeClientCommand(Request request) {
        return ServerConfig.getClientAvailableCommands().get(request.getCommandName()).executeClientCommand(request);
    }

    public String executeServerCommand(String commandName) {
        if (ServerConfig.getServerAvailableCommands().containsKey(commandName)) {
            return ServerConfig.getServerAvailableCommands().get(commandName).executeServerCommand();
        } else {
            return ("There is no such command, type HELP to get list on commands");
        }
    }
}