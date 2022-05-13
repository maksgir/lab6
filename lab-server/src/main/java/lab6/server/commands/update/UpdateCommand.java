package lab6.server.commands.update;

import lab6.common.abstractions.AbstractCommand;
import lab6.common.exceptions.IDNotFoundException;
import lab6.common.util.Request;
import lab6.common.util.Response;
import lab6.server.utils.RoutesCollection;

public class UpdateCommand extends AbstractCommand {


    private final RoutesCollection collectionInWork;

    public UpdateCommand(RoutesCollection collectionManager) {
        super("update", 1,
                "update the value of a collection item whose id is equal to the specified one",
                "id");
        this.collectionInWork = collectionManager;
    }

    @Override
    public Response executeClientCommand(Request request) {
        try {
            collectionInWork.updateById(request.getNumericArgument(), request.getRouteArgument());
            return new Response(("Element with ID " + request.getNumericArgument() + " was updated"));
        } catch (IDNotFoundException e) {
            return new Response((e.getMessage()));
        }
    }
}