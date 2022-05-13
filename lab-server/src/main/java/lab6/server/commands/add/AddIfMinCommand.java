package lab6.server.commands.add;


import lab6.common.abstractions.AbstractCommand;
import lab6.common.exceptions.GroupNotMinException;
import lab6.common.util.Request;
import lab6.common.util.Response;
import lab6.server.utils.RoutesCollection;

public class AddIfMinCommand extends AbstractCommand {
    private final RoutesCollection routesCollection;

    public AddIfMinCommand(RoutesCollection routesCollection) {
        super("add_if_min", 0, "add a new item to the collection if its value less the value of the smallest item in this collection");
        this.routesCollection = routesCollection;
    }

    @Override
    public Response executeClientCommand(Request request) {
        try {
            routesCollection.addIfMin(request.getRouteArgument());
            return new Response(("New element was successfully added!"), request.getRouteArgument());
        } catch (GroupNotMinException e) {
            return new Response((e.getMessage()));
        }
    }
}