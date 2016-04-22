package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by jay on 4/17/16.
 */
public class TimeSeriesSequences {

    public static void main(String[] args) {
        int k = 10;
        int totalEvents = 100;

        System.out.println(getNumberOfSequences(k, totalEvents));
    }

    private static long getNumberOfSequences(int k, int totalEvents) {
        if (k == 1) {
            return totalEvents;
        }

        List<List<Integer>> allList = new ArrayList<>();
        List<Integer> availableNumbers = IntStream.rangeClosed(1, k - 1).boxed()
                .collect(Collectors.toList());

        List<Integer> currentList = new ArrayList<>();
        getTotalSequences(allList, currentList, availableNumbers, k, 0);


        return (getCount(allList, totalEvents) + nChooseK(totalEvents, k));
    }

    private static long getCount(List<List<Integer>> allList, int totalEvents) {
        long count = 0;
        for (List<Integer> integers : allList) {
            long levelCount = 1;
            for (Integer number : integers) {
                levelCount = levelCount * nChooseK(totalEvents, number);
            }

            count = count + levelCount;
        }

        return count;
    }

    private static void getTotalSequences(List<List<Integer>> allList, List<Integer> currentList, List<Integer> availableNumbers, int k, int currentSum) {

        if (currentSum == k) {
            allList.add(currentList);
            currentList.clear();
            return;
        }

        for (int i = 0; i < availableNumbers.size(); i++) {

            int number = availableNumbers.get(i);
            currentList.add(number);

            currentSum = getSum(currentList);

            if (currentSum < k) {
                getTotalSequences(allList, currentList, availableNumbers, k, currentSum);
            } else if (currentSum == k) {
                List<Integer> tempList = new ArrayList<>(currentList);
                allList.add(tempList);
                currentList.remove(currentList.size() - 1);
                return;
            } else if (currentSum > k) {
                currentList.remove(currentList.size() - 1);
                return;
            }

            if (currentList.size() > 0) {
                currentList.remove(currentList.size() - 1);
            }
        }
    }

    private static int getSum(List<Integer> currentList) {
        int sum = 0;

        for (Integer integer : currentList) {
            sum += integer;
        }

        return sum;
    }

    private static long factorial(int number) {
        if (number == 0 || number == 1) {
            return 1;
        }

        long dynamicArray[] = new long[number + 1];

        dynamicArray[0] = 1;
        dynamicArray[1] = 1;
        long factorial = 0;

        if (dynamicArray[number] != 0) {
            return dynamicArray[number];
        }

        for (int i = 2; i < dynamicArray.length; i++) {
            factorial = i * dynamicArray[i - 1];
            dynamicArray[i] = factorial;
        }

        return factorial;
    }


    private static long nChooseK(int n, int k) {
        if (k == 1) {
            return n;
        } else if (k == 0) {
            return 1;
        }
        if (n >= k) {
            long numerator = 1;
            if (n - k > k) {
                for (int i = n - k + 1; i <= n; i++) {
                    numerator = numerator * i;
                }
                return numerator / factorial(k);
            } else {
                for (int i = k + 1; i <= n; i++) {
                    numerator = numerator * i;
                }
                return numerator / factorial(n - k);
            }
        } else
            return 0;
    }
}
