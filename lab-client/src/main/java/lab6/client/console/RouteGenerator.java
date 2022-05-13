package lab6.client.console;

import lab6.client.utils.CommandValidators;
import lab6.common.entities.Coordinates;
import lab6.common.entities.Location;
import lab6.common.entities.Route;

import java.util.Scanner;

public class RouteGenerator {
    private final int maxNameLength = 100;
    private final int maxAddressLength = Integer.MAX_VALUE;

    private final Route generatedRoute;

    private final Coordinates coordinates = new Coordinates();

    private final Location from = new Location();

    private final Location to = new Location();

    private final Scanner sc = new Scanner(System.in);

    public RouteGenerator() {
        generatedRoute = new Route();
        generatedRoute.setCoordinates(coordinates);
        generatedRoute.setTo(to);
        generatedRoute.setFrom(from);
    }

    public Route getGeneratedRoute() {
        return generatedRoute;
    }

    private void getName() {
        String name = CommandValidators.validateStringInput("Enter the name of the route (max length + "
                        + maxNameLength
                        + " symbols)",
                false,
                sc,
                maxNameLength);
        generatedRoute.setName(name);
    }

    private void getXCoordinate() {
        int x = CommandValidators.validateInput(arg -> ((int) arg) <= Coordinates.MAX_X,
                "Enter the X coordinate of the route (its value should be no more than " + Coordinates.MAX_X + ")",
                "Error processing the number, repeat the input",
                "The X coordinate should be no more than " + Coordinates.MAX_X + ", repeat the input",
                Integer::parseInt,
                false,
                sc);
        generatedRoute.getCoordinates().setX(x);
    }

    private void getYCoordinate() {
        long y = CommandValidators.validateInput(arg -> ((long) arg) <= Coordinates.MAX_Y,
                "Enter the Y coordinate of the route (its value should be no more than " + Coordinates.MAX_Y + ")",
                "Error processing the number, repeat the input",
                "The Y coordinate should be no more than " + Coordinates.MAX_Y + ", repeat the input",
                Long::parseLong,
                false,
                sc);
        generatedRoute.getCoordinates().setY(y);
    }

    private void getDistance() {
        long distance = CommandValidators.validateInput(arg -> ((long) arg) > 0,
                "Enter route's distance",
                "Error processing the number, repeat the input",
                "The route's distance must be greater than 0, repeat the input",
                Long::parseLong,
                false,
                sc);
        generatedRoute.setDistance(distance);
    }

    private String getLocationName() {
        return CommandValidators.validateStringInput("Enter the name of the route's location (max length + "
                        + maxNameLength
                        + " symbols)",
                false,
                sc,
                maxNameLength);
    }

    private Double getLocationX() {
        return CommandValidators.validateInput(arg -> ((double) arg) > 0,
                "Enter the X coordinate of the route's location (its value should be more than 0)",
                "Error processing the number, repeat the input",
                "The X coordinate should more than 0",
                Double::parseDouble,
                false,
                sc);
    }

    private Long getLocationY() {
        return CommandValidators.validateInput(arg -> ((long) arg) > 0,
                "Enter the Y coordinate of the route's location (its value should be more than 0)",
                "Error processing the number, repeat the input",
                "The Y coordinate should more than 0",
                Long::parseLong,
                false,
                sc);
    }


    private void getLocationTo() {
        generatedRoute.getTo().setName(getLocationName());
        generatedRoute.getTo().setX(getLocationX());
        generatedRoute.getTo().setY(getLocationY());
    }

    private void getLocationFrom() {
        generatedRoute.getFrom().setName(getLocationName());
        generatedRoute.getFrom().setX(getLocationX());
        generatedRoute.getFrom().setY(getLocationY());
    }


    public void setVariables() {
        getName();
        getXCoordinate();
        getYCoordinate();
        getDistance();
        getLocationFrom();
        getLocationTo();

    }


}
