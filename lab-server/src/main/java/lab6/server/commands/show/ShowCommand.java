package lab6.server.commands.show;

import lab6.common.abstractions.AbstractCommand;
import lab6.common.util.Request;
import lab6.common.util.Response;
import lab6.server.utils.RoutesCollection;

public class ShowCommand extends AbstractCommand {

    private final RoutesCollection routesCollection;

    public ShowCommand(RoutesCollection collectionInWork) {
        super("show", 0, "display all the elements of the collection and information about them");
        this.routesCollection = collectionInWork;
    }

    @Override
    public Response executeClientCommand(Request request) {
        if (routesCollection.getSetOfRoutes().isEmpty()) {
            return new Response("Collection is empty");
        } else {
            return new Response(("Elements of collection:"), routesCollection.getSetOfRoutes());
        }
    }
}