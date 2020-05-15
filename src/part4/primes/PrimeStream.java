package part4.primes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

public class PrimeStream {
    public static long parallelCount(int limit) {
        return IntStream.rangeClosed(2, limit).parallel().filter(PrimeStream::isPrime).count();
    }

    public static long count(int limit) {
        return IntStream.rangeClosed(2, limit).filter(PrimeStream::isPrime).count();
    }

    private static long optimizedCount(int limit) {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        outer: for (int i = 3; i <= limit; i += 2) {
            for (int prime: primes) {
                if (prime * prime > i) break;
                if (i % prime == 0) continue outer;
            }
            primes.add(i);
        }
        return primes.size();
    }

    public static boolean isPrime(final int number) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .noneMatch(divisor -> number % divisor == 0);
    }

    private static final int LIMIT = 20000000;

    public static void main(String[] args) {
        long startTime = Calendar.getInstance().getTimeInMillis();
        long result = parallelCount(LIMIT);
        long endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Count (parallel) = " + result);
        System.out.println("Time (parallel) = " + (endTime - startTime));

        startTime = Calendar.getInstance().getTimeInMillis();
        result = count(LIMIT);
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Count (sequential) = " + result);
        System.out.println("Time (sequential) = " + (endTime - startTime));

        startTime = Calendar.getInstance().getTimeInMillis();
        result = optimizedCount(LIMIT);
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Count (optimized) = " + result);
        System.out.println("Time (optimized) = " + (endTime - startTime));
    }
}

