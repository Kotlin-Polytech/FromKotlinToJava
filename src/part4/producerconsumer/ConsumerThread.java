package part4.producerconsumer;

@SuppressWarnings("WeakerAccess")
public class ConsumerThread extends Thread {

    private final StringQueue queue;

    public ConsumerThread(final StringQueue queue) {
        this.queue = queue;
    }

    static private boolean hasDiffSymbols(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j))
                    return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                String str = queue.getString();
                if (hasDiffSymbols(str)) {
                    System.out.println("String consumed: " + str);
                } else {
                    System.out.println("String ignored: " + str);
                }
            } catch (InterruptedException ex) {
                System.out.println("Thread interrupted!");
                return;
            }
        }        
    }

}
