package lab6.server.commands.clear;

import lab6.common.abstractions.AbstractCommand;
import lab6.common.util.Request;
import lab6.common.util.Response;
import lab6.server.utils.RoutesCollection;

public class ClearCommand extends AbstractCommand {

    private final RoutesCollection routesCollection;

    public ClearCommand(RoutesCollection collectionInWork) {
        super("clear", 0, "clear the collection");
        this.routesCollection = collectionInWork;
    }

    @Override
    public Response executeClientCommand(Request request) {
        if (routesCollection.getSetOfRoutes().isEmpty()) {
            return new Response(("Collection is already empty"));
        } else {
            routesCollection.clearCollection();
            return new Response(("The collection has been cleared"));
        }
    }
}