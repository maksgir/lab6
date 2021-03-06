package lab6.client.utils;

import lab6.common.exceptions.WrongAmountOfArgsException;
import lab6.common.exceptions.WrongArgException;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class CommandValidators {
    public static <T> T validateInput(Predicate<Object> predicate,
                                      String message,
                                      String error,
                                      String wrong,
                                      Function<String, T> function,
                                      Boolean nullable,
                                      Scanner sc) {
        System.out.println(message);
        String input;
        T value;
        do {
            try {
                input = sc.nextLine();
                if ("".equals(input) && Boolean.TRUE.equals(nullable)) {
                    return null;
                } else if ("".equals(input) && !Boolean.TRUE.equals(nullable)) {
                    System.out.println("This field cannot be null, repeat the input");
                    continue;
                }
                value = function.apply(input);
            } catch (IllegalArgumentException e) {
                System.out.println(error);
                continue;
            } catch (NoSuchElementException e) {
                System.out.println("Invalid character entered");
                System.exit(1);
                return null;
            }
            if (predicate.test(value)) {
                return value;
            } else {
                System.out.println(wrong);
            }
        } while (true);
    }

    public static void validateAmountOfArgs(String[] args, int amountOfArgs) throws WrongAmountOfArgsException {
        if (args.length != amountOfArgs) {
            throw new WrongAmountOfArgsException("Wrong amount of args, this command needs " + amountOfArgs + " args");
        }
    }

    public static <T> T validateArg(Predicate<Object> predicate,
                                    String wrong,
                                    Function<String, T> function,
                                    String argument) throws WrongArgException, IllegalArgumentException {
        T value = function.apply(argument);
        if (predicate.test(value)) {
            return value;
        } else {
            throw new WrongArgException(wrong);
        }
    }

    public static String validateStringInput(String message,
                                             Boolean nullable,
                                             Scanner sc,
                                             int length) {
        System.out.println(message);
        String input;
        String value;
        do {
            try {
                input = sc.nextLine();
                if ("".equals(input) && Boolean.TRUE.equals(nullable)) {
                    return null;
                } else if ("".equals(input) && !Boolean.TRUE.equals(nullable)) {
                    System.out.println("This field cannot be null, repeat the input");
                    continue;
                }
                if (input.length() <= length) {
                    value = input;
                } else {
                    System.out.println("String is too long, repeat input");
                    continue;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Invalid character entered");
                System.exit(1);
                return null;
            }
            return value;
        } while (true);
    }
}
