package lab6.server;

import com.thoughtworks.xstream.converters.ConversionException;
import lab6.server.commands.add.AddCommand;
import lab6.server.commands.add.AddIfMinCommand;
import lab6.server.commands.clear.ClearCommand;
import lab6.server.commands.execute.ExecuteScriptCommand;
import lab6.server.commands.exit.ExitCommand;
import lab6.server.commands.filter.FilterByDistanceCommand;
import lab6.server.commands.filter.FilterGreaterThanDistanceCommand;
import lab6.server.commands.help.HelpCommand;
import lab6.server.commands.info.InfoCommand;
import lab6.server.commands.remove.RemoveAnyByDistanceCommand;
import lab6.server.commands.remove.RemoveHeadCommand;
import lab6.server.commands.remove.RemoveLowerCommand;
import lab6.server.commands.serverCommands.ServerExitCommand;
import lab6.server.commands.serverCommands.ServerHelpCommand;
import lab6.server.commands.serverCommands.ServerSaveCommand;
import lab6.server.commands.show.ShowCommand;
import lab6.server.commands.update.UpdateCommand;
import lab6.server.parser.XMLParser;
import lab6.server.threads.ConsoleThread;
import lab6.server.threads.RequestThread;
import lab6.server.utils.*;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerWorker {
    private final Scanner scanner = new Scanner(System.in);
    private final int maxPort = 65535;
    private ServerSocketWorker serverSocketWorker;
    private final ServerCommandListener serverCommandListener = new ServerCommandListener(scanner);
    private RoutesCollection routesCollection = new RoutesCollection();
    private CommandManager commandManager;
    private final File xmlFile;

    public ServerWorker(String xml){
        this.xmlFile = new File(xml);
    }

    public void startServerWorker() {
        try {
            routesCollection.setRoutes(XMLParser.parse(xmlFile));
            //for (Route r :
            //        routesCollection.getSetOfRoutes()) {
            //    System.out.println(r);
            //}
            commandManager = new CommandManager(
                    new HelpCommand(ServerConfig.getClientAvailableCommands()),
                    new InfoCommand(routesCollection),
                    new ShowCommand(routesCollection),
                    new AddCommand(routesCollection),
                    new UpdateCommand(routesCollection),
                    new RemoveByIdCommand(routesCollection),
                    new ClearCommand(routesCollection),
                    new ExecuteScriptCommand(),
                    new ExitCommand(),
                    new RemoveHeadCommand(routesCollection),
                    new AddIfMinCommand(routesCollection),
                    new RemoveLowerCommand(routesCollection),
                    new RemoveAnyByDistanceCommand(routesCollection),
                    new FilterByDistanceCommand(routesCollection),
                    new FilterGreaterThanDistanceCommand(routesCollection),
                    new ServerHelpCommand(ServerConfig.getServerAvailableCommands()),
                    new ServerExitCommand(scanner, routesCollection),
                    new ServerSaveCommand(routesCollection));
            inputPort();
            System.out.println("Welcome to the server! To see the list of commands input HELP");
            RequestThread requestThread = new RequestThread(serverSocketWorker, commandManager, routesCollection);
            ConsoleThread consoleThread = new ConsoleThread(serverCommandListener, commandManager);
            requestThread.start();
            consoleThread.start();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ConversionException e) {
            e.printStackTrace();
            System.out.println("Error during type conversion");
            System.exit(1);
        }
    }

    private void inputPort() throws IOException {
        System.out.println("Do you want to use a default port? [y/n]");
        try {
            String s = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            if ("n".equals(s)) {
                System.out.println("Please enter the remote host port (1-65535)");
                String port = scanner.nextLine();
                try {
                    int portInt = Integer.parseInt(port);
                    if (portInt > 0 && portInt <= maxPort) {
                        serverSocketWorker = new ServerSocketWorker(portInt);
                    } else {
                        System.out.println("The number did not fall within the limits, repeat the input");
                        inputPort();
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error processing the number, repeat the input");
                    inputPort();
                }
            } else if ("y".equals(s)) {
                serverSocketWorker = new ServerSocketWorker();
            } else {
                System.out.println("You entered not valid symbol, try again");
                inputPort();
            }
        } catch (NoSuchElementException e) {
            System.out.println("An invalid character has been entered, forced shutdown!");
            System.exit(1);
        }
    }

}
