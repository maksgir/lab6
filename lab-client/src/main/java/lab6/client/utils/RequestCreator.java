package lab6.client.utils;

import lab6.client.console.RouteGenerator;
import lab6.common.exceptions.WrongAmountOfArgsException;
import lab6.common.exceptions.WrongArgException;
import lab6.common.util.Request;

public class RequestCreator {

    public Request createRequestOfCommand(CommandToSend command) {
        String name = command.getCommandName();
        Request request;
        if (AvailableCommands.COMMANDS_WITHOUT_ARGS.contains(name)) {
            request = createRequestWithoutArgs(command);
        } else if (AvailableCommands.COMMANDS_WITH_ID_ARG.contains(name)) {
            request = createRequestWithID(command);
        } else if (AvailableCommands.COMMANDS_WITH_DISTANCE_ARG.contains(name)) {
            request = createRequestWithDistance(command);
        } else if (AvailableCommands.COMMANDS_WITH_ROUTE_ARG.contains(name)) {
            request = createRequestWithRoute(command);
        } else if (AvailableCommands.COMMANDS_WITH_ROUTE_ID_ARGS.contains(name)) {
            request = createRequestWithRouteID(command);
        } else {
            System.out.println("There is no such command, type HELP to get list on commands");
            request = null;
        }
        return request;
    }

    private Request createRequestWithoutArgs(CommandToSend command) {
        try {
            CommandValidators.validateAmountOfArgs(command.getCommandArgs(), 0);
            return new Request(command.getCommandName());
        } catch (WrongAmountOfArgsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Request createRequestWithID(CommandToSend command) {
        try {
            CommandValidators.validateAmountOfArgs(command.getCommandArgs(), 1);
            long id = CommandValidators.validateArg(arg -> ((long) arg) > 0,
                    "ID must be greater than 0",
                    Long::parseLong,
                    command.getCommandArgs()[0]);
            return new Request(command.getCommandName(), id);
        } catch (WrongAmountOfArgsException | WrongArgException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong data type of argument");
            return null;
        }
    }

    private Request createRequestWithDistance(CommandToSend command) {
        try {
            CommandValidators.validateAmountOfArgs(command.getCommandArgs(), 1);
            long distance = CommandValidators.validateArg(arg -> ((long) arg) > 1,
                    "Route's distance must be greater than 1",
                    Long::parseLong,
                    command.getCommandArgs()[0]);
            return new Request(command.getCommandName(), distance);
        } catch (WrongAmountOfArgsException | WrongArgException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong data type of argument");
            return null;
        }
    }

    private Request createRequestWithRoute(CommandToSend command) {
        try {
            CommandValidators.validateAmountOfArgs(command.getCommandArgs(), 0);
            RouteGenerator generator = new RouteGenerator();
            generator.setVariables();
            return new Request(command.getCommandName(), generator.getGeneratedRoute());
        } catch (WrongAmountOfArgsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Request createRequestWithRouteID(CommandToSend command) {
        try {
            CommandValidators.validateAmountOfArgs(command.getCommandArgs(), 1);
            long id = CommandValidators.validateArg(arg -> ((long) arg) > 0,
                    "ID must be greater then 0",
                    Long::parseLong,
                    command.getCommandArgs()[0]);
            RouteGenerator generator = new RouteGenerator();
            generator.setVariables();
            return new Request(command.getCommandName(), id, generator.getGeneratedRoute());
        } catch (WrongAmountOfArgsException | WrongArgException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong data type of argument");
            return null;
        }
    }
}