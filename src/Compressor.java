import java.io.*;
import java.util.ArrayList;

public class Compressor {

    /**
     * Finds the longest match of the current look-ahead string in the search buffer.
     * The match is found by checking substrings from the current index in the text.
     *
     * @param searchBuffer The buffer containing the previously processed characters.
     * @param text The full input text being compressed.
     * @param idx The current index in the text where compression is taking place.
     * @param lookAheadSize The maximum size of the look-ahead buffer.
     * @return The longest matching substring found in the search buffer.
     */
    private static String longestMatch(StringBuilder searchBuffer, String text, int idx, int lookAheadSize) {
        StringBuilder cur = new StringBuilder();  // StringBuilder to store the current substring
        for (int i = idx; i < Math.min(text.length(), idx + lookAheadSize); i++) {
            cur.append(text.charAt(i));  // Add the next character from the text to the current substring
            if (searchBuffer.indexOf(cur.toString()) == -1) {
                cur.deleteCharAt(cur.length() - 1);  // Remove the last character if no match is found
                break;
            }
        }
        return cur.toString();  // Return the longest matching substring
    }

    /**
     * Reads the input file and converts it into a string for compression.
     *
     * @param filePath The path to the input file.
     * @return The content of the file as a string.
     */
    private static String fileReader(String filePath) {
        StringBuilder text = new StringBuilder();
        try (FileReader reader = new FileReader(filePath)) {
            int c;
            // Read the file character by character and append to the text StringBuilder
            while ((c = reader.read()) != -1) {
                char ch = (char) c;
                text.append(ch);
            }
        } catch (IOException e) {
            System.out.println("Exception thrown  :" + e);
        }
        return text.toString();  // Return the entire file content as a string
    }

    /**
     * Writes a list of Tag objects to a specified file.
     *
     * This method opens a file at the given file path and serializes the
     * provided ArrayList of Tag objects, writing them to the file. If an
     * IOException occurs during the writing process, an exception message
     * will be printed to the console.
     *
     * @param output   The ArrayList of Tag objects to be written to the file.
     *                 The objects must be serializable.
     * @param filePath The path where the output file will be created.
     *                 If the file already exists, it will be overwritten.
     */
    private static void fileWriter(ArrayList<Tag> output, String filePath) {
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filePath));) {
            writer.writeObject(output);
        } catch (IOException e) {
            System.out.println("Exception thrown  :" + e);
        }
    }


    /**
     * Compresses the input file using the LZ77 algorithm.
     * The compressed output is written to the output file as a list of tags.
     *
     * @param inputFilePath The path to the input file.
     * @param searchBufferSize The size of the search buffer.
     * @param lookAheadSize The size of the look-ahead buffer.
     * @param outputFilePath The path where the compressed output should be saved.
     */
    public static void compress(String inputFilePath, int searchBufferSize, int lookAheadSize, String outputFilePath) {
        String text = fileReader(inputFilePath);  // Read the input file
        StringBuilder searchBuffer = new StringBuilder();  // Initialize an empty search buffer
        ArrayList<Tag> result = new ArrayList<>();  // To store the resulting compressed tags

        // Iterate through each character in the input text
        for (int i = 0; i < text.length(); i++) {
            char nextChar = 0;  // The next character after the longest match
            String match = longestMatch(searchBuffer, text, i, lookAheadSize);  // Find the longest match in the search buffer
            int pos = 0, length = 0;  // Position and length of the match

            // If a match is found, set the position and length
            if (!match.isEmpty()) {
                pos = i - text.indexOf(match, Math.max(0, i - searchBufferSize));  // Calculate the position of the match
                length = match.length();  // Length of the matched string
            }

            // Set the next character if there is one after the match
            if (i + length < text.length()) {
                nextChar = text.charAt(i + length);
            }

            // Update the search buffer with the new characters
            for (int j = i; j <= Math.min(i + length, text.length() - 1); j++) {
                if (searchBuffer.length() == searchBufferSize) {
                    searchBuffer.deleteCharAt(0);  // Maintain the search buffer size by removing the oldest character
                }
                searchBuffer.append(text.charAt(j));  // Add the new character to the search buffer
            }

            i += length;  // Move the index to the end of the match
            result.add(new Tag(pos, length, nextChar));  // Add the tag (pos, length, nextChar) to the result
        }

        fileWriter(result, outputFilePath);  // Write the compressed tags to the output file
    }
}
