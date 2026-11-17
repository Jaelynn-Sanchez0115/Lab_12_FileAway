
import java.util.Scanner;

public class SafeInput {

    // Get a non-empty string
    public static String getNonZeroLenString(
            Scanner pipe, String prompt) {

        String input;

        do {
            System.out.print(prompt + ": ");
            input = pipe.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Try again.");
            }

            if (input.contains(",")) {
                System.out.println("Please do not enter commas.");
                input = "";
            }

        } while (input.isEmpty());

        return input;
    }


    // Get an integer within a specified range
    public static int getRangedInt(
            Scanner pipe, String prompt, int low, int high) {

        int number;
        boolean validInput;

        do {
            System.out.print(prompt + " (" + low + "-" + high + "): ");

            validInput = pipe.hasNextInt();

            if (validInput) {
                number = pipe.nextInt();
                pipe.nextLine();

                if (number >= low && number <= high) {
                    return number;
                } else {
                    System.out.println("Number must be between "
                            + low + " and " + high + ".");
                }

            } else {
                System.out.println("Please enter a valid whole number.");
                pipe.nextLine();
            }

        } while (true);
    }


    // Ask the user a yes/no question
    public static boolean getYNConfirm(
            Scanner pipe, String prompt) {

        String answer;

        do {
            System.out.print(prompt + " (Y/N): ");
            answer = pipe.nextLine().trim();

            if (answer.equalsIgnoreCase("Y")) {
                return true;
            }

            if (answer.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("Please enter Y or N.");

        } while (true);
    }
}
