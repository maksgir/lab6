package lab6.server.utils;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerCommandListener {

    private final Scanner sc;

    public ServerCommandListener(Scanner sc) {
        this.sc = sc;
    }

    public String readCommand() {
        try {
            System.out.println("Enter a command: ");
            return sc.nextLine().trim().toLowerCase(Locale.ROOT);
        } catch (NoSuchElementException e) {
            System.out.println("An invalid character has been entered, forced shutdown!");
            System.exit(1);
            return null;
        }
    }
}