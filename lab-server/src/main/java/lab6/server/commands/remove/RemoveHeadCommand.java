package lab6.server.commands.remove;

import lab6.common.abstractions.AbstractCommand;
import lab6.common.entities.Route;
import lab6.common.util.Request;
import lab6.common.util.Response;
import lab6.server.utils.RoutesCollection;


public class RemoveHeadCommand extends AbstractCommand {

    private final RoutesCollection routesCollection;

    public RemoveHeadCommand(RoutesCollection routesCollection) {
        super("remove_head", 0, "return the first element of the collection and remove it");
        this.routesCollection = routesCollection;
    }

    @Override
    public Response executeClientCommand(Request request) {
        try {
            Route res = routesCollection.removeHead();
            if (res == null) {
                return new Response("Not a single item has been deleted");
            } else {
                return new Response(("This item has been removed from the collection:"), res);
            }
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }
}