package part4.count;

public class Count {

    private static volatile int i = 0;

    private static volatile long l = 0;

    private static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            for (int j=0; j<100000; j++) {
                i++;
                l++;
            }
        });
        backgroundThread.start();
        for (int j=0; j<100000; j++) {
            i++;
            l++;
        }
        Thread.sleep(1000);
        System.out.println("i = " + i);
        System.out.println("l = " + l);
    }
}