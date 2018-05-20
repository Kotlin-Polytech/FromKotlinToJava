package part4.primes;

import java.util.Calendar;
import java.util.stream.IntStream;

public class PrimeStream {
    public static long parallelCount(int limit) {
        return IntStream.rangeClosed(2, limit).parallel().filter(PrimeStream::isPrime).count();
    }

    public static long count(int limit) {
        return IntStream.rangeClosed(2, limit).filter(PrimeStream::isPrime).count();
    }

    public static boolean isPrime(final int number) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .noneMatch(divisor -> number % divisor == 0);
    }

    public static void main(String[] args) {
        long startTime = Calendar.getInstance().getTimeInMillis();
        long result = count(1000000);
        long endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Count (sequential) = " + result);
        System.out.println("Time (sequential) = " + (endTime - startTime));

        startTime = Calendar.getInstance().getTimeInMillis();
        result = parallelCount(1000000);
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Count (parallel) = " + result);
        System.out.println("Time (parallel) = " + (endTime - startTime));
    }
}

