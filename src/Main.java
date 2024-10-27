import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the LZ77 Compression/Decompression Program.");
        System.out.println("Please select an option:");
        System.out.println("1. Compress a file");
        System.out.println("2. Decompress a file");
        System.out.println("3. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                // Get inputs for compression
                System.out.println("Enter the input file path to compress:");
                String inputFilePath = scanner.nextLine();
                System.out.println("Enter the output file path for compressed file:");
                String outputFilePath = scanner.nextLine();
                System.out.println("Enter the search buffer size:");
                int searchBufferSize = scanner.nextInt();
                System.out.println("Enter the look-ahead buffer size:");
                int lookAheadBufferSize = scanner.nextInt();

                // Compression logic
                System.out.println("Compressing the file...");
                Compressor.compress(inputFilePath, searchBufferSize, lookAheadBufferSize, outputFilePath);
                System.out.println("Compression complete. Output saved to '" + outputFilePath + "'.");
                break;

            case 2:
                // Get inputs for decompression
                System.out.println("Enter the input file path to decompress:");
                String compressedFilePath = scanner.nextLine();
                System.out.println("Enter the output file path for decompressed file:");
                String decompressedFilePath = scanner.nextLine();

                // Decompression logic
                System.out.println("Decompressing the file...");
                Decompressor.decompress(compressedFilePath, decompressedFilePath);
                System.out.println("Decompression complete. Output saved to '" + decompressedFilePath + "'.");
                break;

            case 3:
                // Exit the program
                System.out.println("Exiting...");
                break;

            default:
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
                break;
        }

        scanner.close();
    }
}
