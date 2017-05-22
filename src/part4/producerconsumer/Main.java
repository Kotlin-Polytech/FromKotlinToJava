package part4.producerconsumer;

public class Main {

    public static void main(String[] args) {
        final StringQueue queue = new StringQueue(2);
        final Thread producer = new ProducerThread(queue);
        final Thread consumer = new ConsumerThread(queue);
        producer.start();
        consumer.start();
        try {
            producer.join();
        } catch (InterruptedException ex) {
            System.out.println("Interrupted! " + ex.getMessage());
        }
        consumer.interrupt();
    }

}
