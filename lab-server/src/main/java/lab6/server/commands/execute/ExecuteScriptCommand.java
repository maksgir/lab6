package lab6.server.commands.execute;

import lab6.common.abstractions.AbstractCommand;
import lab6.common.util.Request;
import lab6.common.util.Response;

import java.util.HashSet;
import java.util.Set;

public class ExecuteScriptCommand extends AbstractCommand {

    private static final Set<String> FILE_HISTORY = new HashSet<>();

    public ExecuteScriptCommand() {
        super("execute_script", 1, "read and execute the script from the specified file", "file name");
    }

    @Override
    public Response executeClientCommand(Request request) {
        return null;
    }
}