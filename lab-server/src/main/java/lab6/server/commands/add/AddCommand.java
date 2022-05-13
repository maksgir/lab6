package lab6.server.commands.add;

import lab6.common.abstractions.AbstractCommand;
import lab6.common.util.Request;
import lab6.common.util.Response;
import lab6.server.utils.RoutesCollection;

public class AddCommand extends AbstractCommand {

    private final RoutesCollection routesCollection;

    public AddCommand(RoutesCollection collectionInWork) {
        super("add", 0, "add new item to the collection");
        this.routesCollection = collectionInWork;
    }

    @Override
    public Response executeClientCommand(Request request) {
        routesCollection.addRoute(request.getRouteArgument());
        return new Response(("New element was successfully added!"), request.getRouteArgument());
    }
}