package lab6.client;

import lab6.client.console.ClientCommandListener;
import lab6.client.utils.*;
import lab6.common.exceptions.WrongAmountOfArgsException;
import lab6.common.util.Request;
import lab6.common.util.Response;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class ClientWorker {
    private final Scanner scanner = new Scanner(System.in);
    private final ClientCommandListener commandListener = new ClientCommandListener(System.in);
    private final RequestCreator requestCreator = new RequestCreator();
    private final int maxPort = 65535;
    private ClientSocketWorker clientSocketWorker;
    private boolean statusOfCommandListening = true;

    public void startClientWorker() {
        System.out.println("Welcome to the program! To see the list of commands type HELP");
        inputAddress();
        inputPort();
        while (statusOfCommandListening) {
            CommandToSend command = commandListener.readCommand();
            if (command != null) {
                if ("exit".equals(command.getCommandName().toLowerCase(Locale.ROOT))) {
                    System.out.println("Client shutdown");
                    toggleStatus();
                } else if (AvailableCommands.SCRIPT_ARGUMENT_COMMAND.equals(command.getCommandName())) {
                    executeScript(command.getCommandArgs());
                } else {
                    if (sendRequest(command)) {
                        receiveResponse();
                    }
                }
            }
        }
    }

    public void toggleStatus() {
        statusOfCommandListening = !statusOfCommandListening;
    }

    private void inputAddress() {
        System.out.println("Do you want to use a default server address? [y/n]");
        try {
            String s = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            if ("y".equals(s)) {
                clientSocketWorker = new ClientSocketWorker();
            } else if ("n".equals(s)) {
                System.out.println("Please enter the server's internet address");
                String address = scanner.nextLine();
                clientSocketWorker = new ClientSocketWorker();
                clientSocketWorker.setAddress(address);
            } else {
                System.out.println("You entered not valid symbol, try again");
                inputAddress();
            }
        } catch (UnknownHostException e) {
            System.out.println("Unknown address, try again");
            inputAddress();
        } catch (SocketException e) {
            System.out.println("Troubles, while opening server port, try again");
            inputAddress();
        } catch (NoSuchElementException e) {
            System.out.println("An invalid character has been entered, forced shutdown!");
            System.exit(1);
        }
    }

    private void inputPort() {
        System.out.println("Do you want to use a default port? [y/n]");
        try {
            String s = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            if ("n".equals(s)) {
                System.out.println("Please enter the remote host port (1-65535)");
                String port = scanner.nextLine();
                try {
                    int portInt = Integer.parseInt(port);
                    if (portInt > 0 && portInt <= maxPort) {
                        clientSocketWorker.setPort(portInt);
                    } else {
                        System.out.println("The number did not fall within the limits, repeat the input");
                        inputPort();
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error processing the number, repeat the input");
                    inputPort();
                }
            } else if (!"y".equals(s)) {
                System.out.println("You entered not valid symbol, try again");
                inputPort();
            }
        } catch (NoSuchElementException e) {
            System.out.println("An invalid character has been entered, forced shutdown!");
            System.exit(1);
        }
    }

    private void executeScript(String[] args) {
        try {
            CommandValidators.validateAmountOfArgs(args, 1);
            ScriptReader reader = new ScriptReader();

            if (ScriptsHistory.getHistoryOfScripts().contains(args[0])) {
                System.out.println("Possible looping, change your script");
            } else {
                reader.readCommandsFromFile(args[0]);
                ScriptsHistory.addToScriptHistory(args[0]);
                ArrayList<CommandToSend> commands = reader.getCommandsFromFile();
                for (CommandToSend command : commands) {
                    System.out.println("Executing... " + command.getCommandName());
                    if ("execute_script".equals(command.getCommandName())) {
                        executeScript(command.getCommandArgs());
                    } else {
                        if (sendRequest(command)) {
                            receiveResponse();
                            System.out.println(command.getCommandName());
                        }
                    }
                }
            }
        } catch (WrongAmountOfArgsException | IOException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("An invalid character has been entered, forced shutdown!");
            System.exit(1);
        }
    }

    private boolean sendRequest(CommandToSend command) {
        Request request = requestCreator.createRequestOfCommand(command);
        if (request != null) {
            request.setCurrentTime(LocalTime.now());
            request.setClientInfo(clientSocketWorker.getAddress() + " " + clientSocketWorker.getPort());
            try {
                clientSocketWorker.sendRequest(request);
                return true;
            } catch (IOException e) {
                System.out.println("An error occurred while serializing the request, try again");
                return false;
            }
        } else {
            return false;
        }
    }

    private void receiveResponse() {
        try {
            Response response = clientSocketWorker.receiveResponse();
            System.out.println(response.toString());
        } catch (SocketTimeoutException e) {
            System.out.println("The waiting time for a response from the server has been exceeded, try again later");
        } catch (IOException e) {
            System.out.println("An error occurred while receiving a response from the server");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("The response came damaged");
        }
    }
}
