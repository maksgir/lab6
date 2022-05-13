package lab6.server.commands.info;

import lab6.common.abstractions.AbstractCommand;
import lab6.common.util.Request;
import lab6.common.util.Response;
import lab6.server.utils.RoutesCollection;

public class InfoCommand extends AbstractCommand {

    private final RoutesCollection routesCollection;

    public InfoCommand(RoutesCollection collectionInWork) {
        super("info", 0, "display information about the collection");
        this.routesCollection = collectionInWork;
    }

    @Override
    public Response executeClientCommand(Request request) {
        return new Response(("Info about collection:\n") + routesCollection.returnInfo());
    }
}