package lab6.server.commands.remove;


import lab6.common.abstractions.AbstractCommand;
import lab6.common.entities.Route;
import lab6.common.exceptions.CollectionIsEmptyException;
import lab6.common.util.Request;
import lab6.common.util.Response;
import lab6.server.utils.RoutesCollection;

import java.util.Set;

public class RemoveLowerCommand extends AbstractCommand {

    private final RoutesCollection routesCollection;

    public RemoveLowerCommand(RoutesCollection routesCollection) {
        super("remove_greater", 0, "remove all items from the collection that exceed the specified");
        this.routesCollection = routesCollection;
    }

    @Override
    public Response executeClientCommand(Request request) {
        try {
            Set<Route> res = routesCollection.removeIfLower(request.getRouteArgument());
            if (res.isEmpty()) {
                return new Response(("Not a single item has been deleted"));
            } else {
                return new Response(("These items have been removed from the collection:"), res);
            }
        } catch (CollectionIsEmptyException e) {
            return new Response((e.getMessage()));
        }
    }
}