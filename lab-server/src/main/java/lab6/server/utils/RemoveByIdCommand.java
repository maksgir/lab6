package lab6.server.utils;

import lab6.common.abstractions.AbstractCommand;
import lab6.common.exceptions.IDNotFoundException;
import lab6.common.util.Request;
import lab6.common.util.Response;

public class RemoveByIdCommand extends AbstractCommand {

    private final RoutesCollection collectionInWork;

    public RemoveByIdCommand(RoutesCollection collectionInWork) {
        super("remove_by_id", 1, "deletes route from collection by its id", "id");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public Response executeClientCommand(Request request) {
        try {
            collectionInWork.removeRouteById(request.getNumericArgument());
            return new Response(("Route with ID " + request.getNumericArgument() + " was deleted from collection"));
        } catch (IDNotFoundException e) {
            return new Response((e.getMessage()));
        }
    }
}