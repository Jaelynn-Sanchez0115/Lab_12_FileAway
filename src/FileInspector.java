
import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class FileInspector {

    public static void main(String[] args) {

        // Start the file chooser in this project's src directory
        File srcDirectory = Paths.get("src").toAbsolutePath().toFile();

        JFileChooser fileChooser = new JFileChooser(srcDirectory);

        fileChooser.setDialogTitle("Select a Text File");

        // Only allow the user to select files
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Display the file chooser
        int result = fileChooser.showOpenDialog(null);

        // Check whether the user selected a file
        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file was selected. Program ending.");
            return;
        }

        // Get the selected file
        File selectedFile = fileChooser.getSelectedFile();

        // Counters
        int lineCount = 0;
        int wordCount = 0;
        int characterCount = 0;

        System.out.println("\n========== FILE CONTENTS ==========\n");

        // Read the file one line at a time
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(selectedFile))) {

            String line;

            while ((line = reader.readLine()) != null) {

                // Display the current line
                System.out.println(line);

                // Count lines
                lineCount++;

                // Count characters in this line
                characterCount += line.length();

                // Count words, ignoring extra spaces
                String trimmedLine = line.trim();

                if (!trimmedLine.isEmpty()) {
                    String[] words = trimmedLine.split("\\s+");
                    wordCount += words.length;
                }
            }

            // Display the summary report
            System.out.println("\n========== FILE SUMMARY ==========");

            System.out.println("File name: " + selectedFile.getName());
            System.out.println("Number of lines: " + lineCount);
            System.out.println("Number of words: " + wordCount);
            System.out.println("Number of characters: " + characterCount);

            System.out.println("==================================");

        } catch (IOException e) {

            System.out.println("An error occurred while reading the file.");
            System.out.println("Error: " + e.getMessage());

        }
    }
}

