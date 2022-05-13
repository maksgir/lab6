package lab6.server.commands.serverCommands;

import lab6.common.abstractions.AbstractServerCommand;

import java.util.HashMap;

public class ServerHelpCommand extends AbstractServerCommand {

    private final HashMap<String, AbstractServerCommand> availableCommands;

    public ServerHelpCommand(HashMap<String, AbstractServerCommand> availableCommands) {
        super("help", "show list of available commands");
        this.availableCommands = availableCommands;
    }

    @Override
    public String executeServerCommand() {
        StringBuilder sb = new StringBuilder();
        for (AbstractServerCommand command : availableCommands.values()) {
            sb.append(command.toString()).append("\n");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        return ("Available commands:\n") + sb;
    }
}