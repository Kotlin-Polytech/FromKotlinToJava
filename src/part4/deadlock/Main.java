package part4.deadlock;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<Integer> listA = new ArrayList<>();
    private static final List<Integer> listB = new ArrayList<>();

    public static void main(String[] args) {
        Thread second = new Thread(() -> {
            int i = 0;
            while (i < 1000) {
                try {
                    synchronized (listA) {
                        System.out.println("Second thread locks listA");
                        i++;
                        Thread.sleep(100);
                        listA.add(i);
                        synchronized (listB) {
                            System.out.println("Second thread locks listB");
                            i++;
                            listB.add(i);
                        }
                    }
                    System.out.println("Second thread unlocks lists, i=" + i);
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    break;
                }
            }
        });
        second.start();
        int j = 0;
        while (j < 1000) {
            try {
                synchronized (listB) {
                    System.out.println("First thread locks listB");
                    j++;
                    Thread.sleep(100);
                    listB.add(j);
                    synchronized (listA) {
                        System.out.println("First thread locks listA");
                        j++;
                        listA.add(j);
                    }
                }
                System.out.println("First thread unlocks lists, j=" + j);
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                break;
            }
        }
        try {
            second.join();
        } catch (InterruptedException ex) {
            System.out.println("Interrupted unexpectedly!");
        }
        System.out.println("listA = " + listA);
        System.out.println("listB = " + listB);
    }
}
