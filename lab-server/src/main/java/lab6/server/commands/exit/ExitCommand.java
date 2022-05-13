package lab6.server.commands.exit;

import lab6.common.abstractions.AbstractCommand;
import lab6.common.util.Request;
import lab6.common.util.Response;

public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", 0, "shut down the client (all your changes will be lost)");
    }

    @Override
    public Response executeClientCommand(Request request) {
        return null;
    }
}