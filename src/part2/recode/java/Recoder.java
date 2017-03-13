package part2.recode.java;

import java.io.*;

@SuppressWarnings("WeakerAccess")
public class Recoder {

    private final String charsetInput;

    private final String charsetOutput;

    public Recoder(String charsetInput, String charsetOutput) {
        this.charsetInput = charsetInput;
        this.charsetOutput = charsetOutput;
    }

    public int recode(InputStream in, OutputStream out) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(in, charsetInput)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(out, charsetOutput)) {
                int sym = reader.read();
                int count = 0;
                while (sym != -1) {
                    writer.write(sym);
                    count++;
                    sym = reader.read();
                }
                return count;
            }
        }
    }

    public int recode(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                return recode(inputStream, outputStream);
            }
        }
    }
}
