package lab6.server.commands.serverCommands;

import lab6.common.abstractions.AbstractServerCommand;
import lab6.server.parser.XMLParser;
import lab6.server.utils.RoutesCollection;
import lab6.server.utils.ServerConfig;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerExitCommand extends AbstractServerCommand {
    private static Integer id = 0;
    private final String path = "C:\\work\\lab6\\lab-server\\src\\main\\java\\lab6\\server\\parser\\savedFiles\\saved";
    private final Scanner scanner;

    private final RoutesCollection routesCollection;

    public ServerExitCommand(Scanner scanner,  RoutesCollection routesCollection) {
        super("exit", "shut down the server (you'll be asked to store all the changes)");
        this.scanner = scanner;
        this.routesCollection = routesCollection;
    }

    @Override
    public String executeServerCommand() {

        chooseSaving();
        ServerConfig.toggleRun();
        return ("Server shutdown");
    }

    private void chooseSaving() {
        System.out.println("Do you want to save changes? [y/n]");
        try {
            String s = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            if ("n".equals(s)) {
                System.out.println("You lost all of your data )=");
            } else if ("y".equals(s)) {
                id++;
                XMLParser.write(new File(path + id.toString()), routesCollection);
                System.out.println("Collection was successfully saved");
            } else {
                System.out.println("You entered not valid symbol, try again");
                chooseSaving();
            }
        } catch (NoSuchElementException e) {
            System.out.println("An invalid character has been entered, forced shutdown!");
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}