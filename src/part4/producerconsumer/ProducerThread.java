package part4.producerconsumer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("WeakerAccess")
public class ProducerThread extends Thread {

    private final StringQueue queue;

    public ProducerThread(final StringQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        FileInputStream fstream = null;
        InputStreamReader freader = null;
        try {
            fstream = new FileInputStream("files/in.txt");
            freader = new InputStreamReader(fstream, "CP1251");
            int ch = freader.read();
            StringBuilder sb = new StringBuilder();
            while (ch != -1) {
                if (ch != '\n') {
                    sb.append((char)ch);
                } else {
                    try {
                        System.out.println("String produced: " + sb.toString());
                        queue.putString(sb.toString());
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println("Thread interrupted!");
                        return;
                    }
                    sb = new StringBuilder();
                }
                ch = freader.read();
            }
            System.out.println("End of file");
        } catch (IOException ex) {
            System.out.println("Input/output error: " + ex.getMessage());
        } finally {
            try {
                if (fstream != null) {
                    fstream.close();
                }
            } catch (IOException ex) {
                System.out.println("I/O error while closing the file: " + ex.getMessage());
            }
            try {
                if (freader != null) {
                    freader.close();
                }
            } catch (IOException ex) {
                System.out.println("I/O error while closing the file: " + ex.getMessage());
            }
        }
    }
}
