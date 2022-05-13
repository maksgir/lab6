package lab6.server.commands.filter;

import lab6.common.abstractions.AbstractCommand;
import lab6.common.exceptions.CollectionIsEmptyException;
import lab6.common.exceptions.GroupNotFoundException;
import lab6.common.util.Request;
import lab6.common.util.Response;
import lab6.server.utils.RoutesCollection;

public class FilterByDistanceCommand extends AbstractCommand {
    private final RoutesCollection routesCollection;

    public FilterByDistanceCommand(RoutesCollection routesCollection) {
        super("filter_by_distance",
                1,
                "returns elements whose distance equals to the given one",
                "distance");
        this.routesCollection = routesCollection;
    }

    @Override
    public Response executeClientCommand(Request request) {
        try {
            return new Response(("There are routes with " + request.getNumericArgument() + " distance"),
                    routesCollection.filterByDistance(request.getNumericArgument()));
        } catch (Exception e) {
            return new Response((e.getMessage()));
        }
    }

}
