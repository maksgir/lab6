package lab6.server.commands.remove;


import lab6.common.abstractions.AbstractCommand;
import lab6.common.exceptions.CollectionIsEmptyException;
import lab6.common.exceptions.GroupNotFoundException;
import lab6.common.util.Request;
import lab6.common.util.Response;
import lab6.server.utils.RoutesCollection;

public class RemoveAnyByDistanceCommand extends AbstractCommand {

    private final RoutesCollection routesCollection;

    public RemoveAnyByDistanceCommand(RoutesCollection routesCollection) {
        super("remove_any_by_distance",
                1,
                "delete a route with a specified distance from the collection",
                "distance");
        this.routesCollection = routesCollection;
    }

    @Override
    public Response executeClientCommand(Request request) {
        try {
            return new Response(("Route with " + request.getNumericArgument() + " participants was removed"),
                    routesCollection.removeAnyByDistance(request.getNumericArgument()));
        } catch (GroupNotFoundException | CollectionIsEmptyException e) {
            return new Response((e.getMessage()));
        }
    }
}