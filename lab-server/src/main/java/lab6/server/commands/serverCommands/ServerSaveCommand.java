package lab6.server.commands.serverCommands;

import lab6.common.abstractions.AbstractServerCommand;
import lab6.server.parser.XMLParser;
import lab6.server.utils.RoutesCollection;

import java.io.File;
import java.io.IOException;

public class ServerSaveCommand extends AbstractServerCommand {
    private static Integer id = 0;
    private final String path = "C:\\work\\lab6\\lab-server\\src\\main\\java\\lab6\\server\\parser\\savedFiles\\saved";
    private final RoutesCollection routesCollection;


    public ServerSaveCommand(RoutesCollection routesCollection) {
        super("save", "save the collection to a file");
        this.routesCollection = routesCollection;

    }

    @Override
    public String executeServerCommand() {
        try {
            id++;
            XMLParser.write(new File(path + id.toString()), routesCollection);
        } catch (IOException e) {
            return (e.getMessage());
        }
        return ("Collection was successfully saved");
    }
}