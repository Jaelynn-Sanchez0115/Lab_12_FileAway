
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {

    public static void main(String[] args) {

        Scanner pipe = new Scanner(System.in);

        // Store all CSV records in an ArrayList
        ArrayList<String> records = new ArrayList<>();

        // Start IDs at 1
        int idNumber = 1;

        boolean addAnother = true;

        System.out.println("========== DATA SAVER ==========");

        // Continue collecting records until the user says no
        while (addAnother) {

            System.out.println("\nEnter information for record " + idNumber);

            // Get first name
            String firstName = SafeInput.getNonZeroLenString(
                    pipe, "Enter first name");

            // Get last name
            String lastName = SafeInput.getNonZeroLenString(
                    pipe, "Enter last name");

            // Get email
            String email = SafeInput.getNonZeroLenString(
                    pipe, "Enter email address");

            // Get a four-digit birth year
            int birthYear = SafeInput.getRangedInt(
                    pipe, "Enter four-digit year of birth", 1000, 9999);

            // Create a six-digit ID with leading zeroes
            String formattedID = String.format("%06d", idNumber);

            // Create CSV record
            String record = firstName + ", "
                    + lastName + ", "
                    + formattedID + ", "
                    + email + ", "
                    + birthYear;

            // Add the record to the ArrayList
            records.add(record);

            System.out.println("\nRecord added:");
            System.out.println(record);

            idNumber++;

            // Ask whether the user wants to add another record
            addAnother = SafeInput.getYNConfirm(
                    pipe, "Would you like to enter another record?");
        }

        // Ask for the output file name
        System.out.println("\nYou entered " + records.size() + " record(s).");

        String fileName = SafeInput.getNonZeroLenString(
                pipe, "Enter a name for your CSV file (without .csv)");

        // Remove .csv if the user included it already
        if (fileName.toLowerCase().endsWith(".csv")) {
            fileName = fileName.substring(0, fileName.length() - 4);
        }

        // Add the .csv extension
        fileName = fileName + ".csv";

        // Save the file in the src directory
        File outputFile = Paths.get("src", fileName).toFile();

        // Write all records to the CSV file
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(outputFile))) {

            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }

            System.out.println("\nFile successfully saved!");
            System.out.println("File location: " + outputFile.getAbsolutePath());

        } catch (IOException e) {

            System.out.println("\nAn error occurred while saving the file.");
            System.out.println("Error: " + e.getMessage());

        }

        System.out.println("\n========== PROGRAM COMPLETE ==========");

    }
}

