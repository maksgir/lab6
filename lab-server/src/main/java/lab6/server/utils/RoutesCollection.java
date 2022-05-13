package lab6.server.utils;

import lab6.common.entities.Route;
import lab6.common.exceptions.CollectionIsEmptyException;
import lab6.common.exceptions.GroupNotFoundException;
import lab6.common.exceptions.GroupNotMinException;
import lab6.common.exceptions.IDNotFoundException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class RoutesCollection {
    private static long idCounter = 4;
    private static String fileName;
    private LocalDate dateOfInitialization;
    private ArrayDeque<Route> routes;

    public RoutesCollection() {
        dateOfInitialization = LocalDate.now();
    }

    public RoutesCollection(String fileName) {
        dateOfInitialization = LocalDate.now();
        this.fileName = fileName;
    }

    public void sort() {
        List<Route> routeList = new ArrayList<>(routes);
        routeList = routeList.stream().sorted().collect(Collectors.toList());
        this.routes = new ArrayDeque<>(routeList);
    }

    public void setRoutes(ArrayDeque<Route> routes) {
        this.routes = routes;
    }

    public String returnInfo() {

        return "Collection type: " + routes.getClass().getSimpleName() + ", type of elements: "
                + Route.class.getSimpleName() + ", date of initialization: " + dateOfInitialization
                + ", number of elements: " + routes.size();
    }

    public Set<Route> getSetOfRoutes() {
        return new HashSet<>(routes);
    }

    public String show() {
        if (routes.isEmpty()) {
            return "Collection is empty";
        } else {
            StringBuilder sb = new StringBuilder();
            for (Route route : routes) {
                sb.append(route).append("\n");
            }
            sb = new StringBuilder(sb.substring(0, sb.length() - 2));
            return sb.toString();
        }
    }

    public void updateById(Long id, Route route) throws IDNotFoundException {
        if (routes.removeIf(r -> Objects.equals(r.getId(), id))) {
            route.setId(id);
            routes.add(route);
        } else {
            throw new IDNotFoundException("There is no group with this ID");
        }
    }

    public void addRoute(Route route) {
        route.setId(idCounter++);
        routes.add(route);
    }

    public void addIfMin(Route route) throws GroupNotMinException {
        for (Route r : routes) {
            if (r.compareTo(route) <= 0) {
                throw new GroupNotMinException("The entered route is not minimal");
            }
        }
        addRoute(route);
    }

    public void clearCollection() {
        routes.clear();
    }

    public void removeRouteById(Long id) throws IDNotFoundException {
        if (!routes.removeIf(r -> Objects.equals(r.getId(), id))) {
            throw new IDNotFoundException("There is no route with this ID");
        }
    }

    public Route removeHead() {
        return routes.pollFirst();
    }

    public Set<Route> removeIfLower(Route route) throws CollectionIsEmptyException {
        ArrayDeque<Route> copy = new ArrayDeque<>(routes);
        if (!routes.isEmpty()) {
            routes.removeIf(mb -> mb.compareTo(route) < 0);
            copy.removeAll(routes);
        } else {
            throw new CollectionIsEmptyException("Collection is empty");
        }
        return new HashSet<>(copy);
    }

    public Route removeAnyByDistance(Long distance) throws GroupNotFoundException, CollectionIsEmptyException {
        if (!routes.isEmpty()) {
            List<Route> matchRoutes = routes.stream().filter(mb -> Objects.equals(mb.getDistance(), distance)).collect(Collectors.toList());
            if (matchRoutes.isEmpty()) {
                throw new GroupNotFoundException("There is no route with such a distance");
            } else {
                routes.remove(matchRoutes.get(0));
                return matchRoutes.get(0);
            }
        } else {
            throw new CollectionIsEmptyException("Collection is empty");
        }
    }

    public Set<Route> filterByDistance(Long distance) {
        return routes.stream().filter(x -> x.getDistance() == distance).collect(Collectors.toSet());
    }

    public Set<Route> filterGreaterByDistance(Long distance) {
        return routes.stream().filter(x -> x.getDistance() > distance).collect(Collectors.toSet());
    }



    public ArrayDeque<Route> getCollection() {
        return routes;
    }
}
