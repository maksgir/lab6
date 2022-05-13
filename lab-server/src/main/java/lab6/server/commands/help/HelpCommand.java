package lab6.server.commands.help;

import lab6.common.abstractions.AbstractCommand;
import lab6.common.util.Request;
import lab6.common.util.Response;

import java.util.HashMap;

public class HelpCommand extends AbstractCommand {
    private final HashMap<String, AbstractCommand> availableCommands;

    public HelpCommand(HashMap<String, AbstractCommand> availableCommands) {
        super("help", 0, "shows list of available commands");
        this.availableCommands = availableCommands;
    }

    @Override
    public Response executeClientCommand(Request request) {
        StringBuilder sb = new StringBuilder();
        for (AbstractCommand command : availableCommands.values()) {
            sb.append(command.toString()).append("\n");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        return new Response(("Available commands:\n") + sb);
    }
}
