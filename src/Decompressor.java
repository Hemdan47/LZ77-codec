import java.io.*;
import java.util.ArrayList;

public class Decompressor {

    /**
     * Reads a list of Tag objects from a specified file.
     *
     * This method opens a file at the given file path and attempts to
     * read an ArrayList of Tag objects from it. If an IOException
     * occurs during the reading process, an exception message will be
     * printed to the console. If the class of a serialized object cannot
     * be found, a RuntimeException will be thrown.
     *
     * @param filePath The path to the file from which the Tag objects
     *                 will be read. The file must contain a serialized
     *                 ArrayList of Tag objects.
     * @return An ArrayList of Tag objects read from the file.
     *         Returns an empty list if the file is empty or if an error
     *         occurs during reading.
     */
    private static ArrayList<Tag> fileReader(String filePath) {
        ArrayList<Tag> result = new ArrayList<>();
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filePath));) {
            result = (ArrayList<Tag>)reader.readObject();
        } catch (IOException e) {
            System.out.println("Exception thrown  :" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;  // Return the list of Tag objects
    }


    /**
     * Writes the decompressed string output to a file.
     *
     * @param output The decompressed string that will be written to the file.
     * @param filePath The path where the decompressed output should be saved.
     */
    private static void fileWriter(String output, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(output);  // Write the decompressed data to the output file
        } catch (IOException e) {
            System.out.println("Exception thrown  :" + e);
        }
    }

    /**
     * Decompresses a file using the LZ77 algorithm.
     * Reads the compressed file, reconstructs the original data, and writes the decompressed result to a new file.
     *
     * @param inputFilePath The path to the compressed input file.
     * @param outputFilePath The path where the decompressed output should be saved.
     */
    public static void decompress(String inputFilePath, String outputFilePath) {
        // Read the compressed input file and get the list of tags
        ArrayList<Tag> input = fileReader(inputFilePath);
        StringBuilder result = new StringBuilder();

        // Reconstruct the original data from the tags
        for (int i = 0; i < input.size(); i++) {
            int start = result.length() - input.get(i).getPos();   // Calculate the start index of the match
            int end = start + input.get(i).getLength();            // Calculate the end index of the match
            String cur = result.substring(start, end);             // Extract the matched string from the result
            result.append(cur);                                    // Append the matched string
            result.append(input.get(i).getNextChar());             // Append the next character after the match
        }

        // Remove the last character if it's a null character (0)
        if (result.charAt(result.length() - 1) == 0) {
            result.deleteCharAt(result.length() - 1);
        }

        // Write the decompressed result to the output file
        fileWriter(result.toString(), outputFilePath);
    }
}
