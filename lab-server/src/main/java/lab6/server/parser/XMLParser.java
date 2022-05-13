package lab6.server.parser;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import lab6.common.entities.Route;
import lab6.server.utils.RoutesCollection;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class XMLParser {
    public static ArrayDeque<Route> parse(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();
        while (bufferedReader.ready()) {
            builder.append(bufferedReader.readLine().trim());
        }
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("Route", Route.class);
        xStream.alias("list", RoutesCollection.class);
        xStream.addImplicitCollection(RoutesCollection.class, "routes");
        RoutesCollection routes = (RoutesCollection) xStream.fromXML(builder.toString());
        return routes.getCollection();
    }
    public static void write(File file, RoutesCollection routes) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("Route", Route.class);
        xStream.alias("list", RoutesCollection.class);
        xStream.addImplicitCollection(RoutesCollection.class, "routes");
        String xmlText = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n" + xStream.toXML(new ArrayList<>(routes.getCollection()));

        try (FileOutputStream out = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(out)) {

            byte[] buffer = xmlText.getBytes();
            bos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }
}
