# LZW Compression and Decompression Codec

This project implements an LZW-based compression and decompression system in Java. It is designed to both compress text data into a compact format and decompress it back to its original form. The project includes Java classes for both compression and decompression, with a main entry point for easy execution. 

## Table of Contents
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [Example Files](#example-files)
- [License](#license)

## Project Structure

```plaintext
LZW-codec/
├── data/
│   ├── compressed/
│   │   └── compressed.txt          # Example of compressed data
│   └── decompressed/
│       ├── input.txt               # Example of original input
│       └── output.txt              # Example of decompressed output
├── src/
    ├── Compressor.java             # Compression logic
    ├── Decompressor.java           # Decompression logic
    └── Main.java                   # Main entry point
```

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/LZW-codec.git
   ```

2. Compile the Java files:
   ```bash
   javac -d out/production/LZW-codec src/*.java
   ```

3. Run the application using the main class:
   ```bash
   java -cp out/production/LZW-codec Main
   ```

## Usage

1. **Compression**:
   - The `Compressor` class reads an input file, compresses the content using the LZW algorithm, and writes the compressed data to a specified output file.
   
2. **Decompression**:
   - The `Decompressor` class reads from a specified compressed file and decompresses the data back to its original format, writing the result to an output file of your choice.

## Example Files

- `data/decompressed/input.txt`: Sample input data for compression.
- `data/compressed/compressed.txt`: Result of compressing the input data.
- `data/decompressed/output.txt`: Expected output after decompression, matching `input.txt`.


## License

This project is open-source and available under the [MIT License](LICENSE).

---
