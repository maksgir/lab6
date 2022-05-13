package lab6.server.commands.filter;

import lab6.common.abstractions.AbstractCommand;
import lab6.common.util.Request;
import lab6.common.util.Response;
import lab6.server.utils.RoutesCollection;

public class FilterGreaterThanDistanceCommand extends AbstractCommand {
    private final RoutesCollection routesCollection;

    public FilterGreaterThanDistanceCommand(RoutesCollection routesCollection) {
        super("filter_greater_than_distance",
                1,
                "returns elements whose distance more than the given one",
                "distance");
        this.routesCollection = routesCollection;
    }
    @Override
    public Response executeClientCommand(Request request) {
        try {
            return new Response(("There are routes with more than " + request.getNumericArgument() + " distance"),
                    routesCollection.filterGreaterByDistance(request.getNumericArgument()));
        } catch (Exception e) {
            return new Response((e.getMessage()));
        }
    }
}
