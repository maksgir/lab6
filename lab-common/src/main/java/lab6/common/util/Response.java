package lab6.common.util;

import lab6.common.entities.Route;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Response implements Serializable {

    private String messageToResponse;
    private Route routeToResponse;
    private Set<Route> collectionToResponse;

    public Response(String messageToResponse) {
        this.messageToResponse = messageToResponse;
    }

    public Response(String messageToResponse, Route routeToResponse) {
        this.messageToResponse = messageToResponse;
        this.routeToResponse = routeToResponse;
    }

    public Response(String messageToResponse, Set<Route> collectionToResponse) {
        this.messageToResponse = messageToResponse;
        this.collectionToResponse = collectionToResponse;
    }

    public Response(Route routeToResponse) {
        this.routeToResponse = routeToResponse;
    }

    public Response(Set<Route> collectionToResponse) {
        this.collectionToResponse = collectionToResponse;
    }

    public String getMessageToResponse() {
        return messageToResponse;
    }

    public Route getRouteToResponse() {
        return routeToResponse;
    }

    public Set<Route> getCollectionToResponse() {
        return collectionToResponse;
    }

    public String getInfoAboutResponse() {
        return "Response contains: " + (messageToResponse == null ? "" : "message")
                + (routeToResponse == null ? "" : ", musicband")
                + (collectionToResponse == null ? "" : ", collection");
    }

    @Override
    public String toString() {
        StringBuilder collection = new StringBuilder();
        if (collectionToResponse != null) {
            List<Route> sortedRoutes = new ArrayList<>(collectionToResponse);
            sortedRoutes = sortedRoutes.stream().sorted(Comparator.comparing(Route::getName).reversed()).collect(Collectors.toList());
            for (Route route : sortedRoutes) {
                collection.append(route.toString()).append("\n");
            }
            collection = new StringBuilder(collection.substring(0, collection.length() - 1));
        }
        return (messageToResponse == null ? "" : messageToResponse)
                + (routeToResponse == null ? "" : "\n" + routeToResponse)
                + ((collectionToResponse == null) ? "" : "\n"
                + collection);
    }
}
