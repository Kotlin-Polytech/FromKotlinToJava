package part4.stopthread;

public class Main {

    private static class Stop {
        private boolean requested = false;

        void request() {
            requested = true;
        }

        boolean isRequested() {
            return requested;
        }
    }

    private static Stop stop = new Stop();

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stop.isRequested()) i++;
            System.out.println("Background thread finished");
        });
        backgroundThread.start();

        Thread.sleep(1000);
        stop.request();
    }
}
