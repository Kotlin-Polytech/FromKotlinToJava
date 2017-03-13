package part2.recode.java;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class RecoderLauncher {

    @Option(name = "-ie", metaVar = "EncodingI", required = true, usage = "Input file encoding")
    private String inputEncoding;

    @Option(name = "-oe", metaVar = "EncodingO", required = true, usage = "Output file encoding")
    private String outputEncoding;

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private String inputFileName;

    @Argument(required = true, metaVar = "OutputName", index = 1, usage = "Output file name")
    private String outputFileName;

    public static void main(String[] args) {
        new RecoderLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar recoder.jar -ie EncodingI -oe EncodingO InputName OutputName");
            parser.printUsage(System.err);
            return;
        }

        Recoder recoder = new Recoder(inputEncoding, outputEncoding);
        try {
            int result = recoder.recode(inputFileName, outputFileName);
            System.out.println("Total of " + result + " symbols recoded");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
