package Recursion.Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        List<List<Integer>> sets = subsets(nums);

        for (List<Integer> set : sets) {
            System.out.println("set --- ");

            for (Integer integer : set) {
                System.out.println(integer);
            }
        }

    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<Integer> subset = new ArrayList<>();
        List<List<Integer>> subsets = new ArrayList<>();

        subsets(nums, 0, subset, subsets);

        return subsets;
    }

    private static void subsets(int[] nums, int offset, List<Integer> subset, List<List<Integer>> subsets) {
        if (offset == nums.length) {
            subsets.add(new ArrayList<>(subset));
        } else {
            subset.add(nums[offset]);
            subsets(nums, offset + 1, subset, subsets);
            subset.remove(subset.size() - 1);
            subsets(nums, offset + 1, subset, subsets);
        }
    }
}

