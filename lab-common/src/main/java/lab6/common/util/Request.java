package lab6.common.util;

import lab6.common.entities.Route;

import java.io.Serializable;
import java.time.LocalTime;

public class Request implements Serializable {

    private final String commandName;
    private String clientInfo;
    private LocalTime currentTime;
    private Long numericArgument;
    private Route routeArgument;

    public Request(String commandName) {
        this.commandName = commandName;
    }

    public Request(String commandName, Long numericArgument) {
        this.commandName = commandName;
        this.numericArgument = numericArgument;
    }

    public Request(String commandName, Route bandArgument) {
        this.commandName = commandName;
        this.routeArgument = bandArgument;
    }

    public Request(String commandName, Long numericArgument, Route routeArgument) {
        this.commandName = commandName;
        this.numericArgument = numericArgument;
        this.routeArgument = routeArgument;
    }

    public String getCommandName() {
        return commandName;
    }

    public Long getNumericArgument() {
        return numericArgument;
    }

    public Route getBandArgument() {
        return routeArgument;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "Name of command to send: " + commandName
                + (routeArgument == null ? "" : "\nInfo about route to send: " + routeArgument)
                + (numericArgument == null ? "" : "\nNumeric argument to send: " + numericArgument);
    }
}