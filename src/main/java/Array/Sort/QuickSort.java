package Array.Sort;

public class QuickSort implements Sort{

    private int partition(int[] A, int first, int last){
        int x = A[last];
        int i = first - 1;
        for (int j = first; j < last; j++){
            if (A[j] <= x){
                i++;
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        int tmp = A[i + 1];
        A[i + 1] = A[last];
        A[last] = tmp;
        return i + 1;
    }

    private void quickSort(int[] A, int first, int last){
        if (first < last){
            int pivot = partition(A, first, last);
            quickSort(A, first, pivot - 1);
            quickSort(A, pivot + 1, last);
        }
    }

    @Override
    public void sort(int[] A) {
        quickSort(A, 0, A.length - 1);
    }

    /**
     * Suppose arrays representing sets A and B are both sorted. Write a linear time method that finds the minimum
     * difference between any element from A and any element from B.
     */
    public int minDifference(int[] A, int[] B){
        return 0;
    }

    /**
     * Implement a modified version of partition algorithm to write a method in QuickSort class which moves the odd
     * numbers before even ones in A in one pass. The algorithm is as follows:
     * At the beginning of the algorithm, let say we have two indexes i and j, showing the first and last elements
     * respectively.
     * While i is less than j,
     * Increment i until you find an odd number.
     * Decrement j until you find an even number.
     * If i $<$ j, swap the contents of i and j.
     * Continue with step 2.
     */
    public void oddsBeforeEvens(int[] A){

    }

    /**
     * Suppose you are given an array of $N$ integers. Write a single pass algorithm that puts the numbers fully
     * divisible by 3 first, then the numbers which division remains 1, and lastly the numbers which division remains
     * 2 similar to the partition algorithm in QuickSort.
     */
    public void quickTriple(int[] A){

    }

    /**
     * Suppose you are given A, an unsorted array of N integers, and a number X. Write $O(N)$ algorithm to partition the
     * numbers in the array such that, the numbers that are smaller than X will be in the first part, the numbers that
     * are equal to X will be in the second part, and the numbers that are larger than X will be in the third part. You
     * may not use any additional data structure or array.
     */
    public void threePartitionArray(int[] A, int X){
    }

    /**
     * Given array A of N numbers write function by using the existing quick sort implementation
     * quickSort, which now orders the items as minimum item, maximum item, second minimum item,
     * second maximum item, etc. You may assume list length is even. Input list should not be modified and
     * You may use up to two extra arrays. For instance:
     * [1, 8, 7, 3, 4, 5, 9, 2] -> [1, 9, 2, 8, 3, 7, 4, 5]
     */
    public int[] minMaxRepeat(int[] A) {return new int[0];}

    /**
     * Suppose you are given an array of $N$ integers. Write a single pass algorithm that puts the numbers whose division
     * by 4 remains 0 first, then the numbers which division remains 1, then the ones which remains 2, and lastly the
     * ones which remains 3, similar to the partition algorithm in QuickSort. The array may contain negative numbers, so
     * the remainder should be computed with {\bf Math.floorMod}. You may not use any additional data structure or array.
     */
    public void quickQuadruple(int[] A){
        int zero = 0;
        int one = 0;
        int i = 0;
        int three = A.length - 1;
        while (i <= three) {
            int r = Math.floorMod(A[i], 4);
            if (r == 0) {
                int temp = A[i];
                A[i] = A[one];
                A[one] = temp;
                temp = A[one];
                A[one] = A[zero];
                A[zero] = temp;
                zero++;
                one++;
                i++;
            }
            else if (r == 1) {
                int temp = A[i];
                A[i] = A[one];
                A[one] = temp;
                one++;
                i++;
            }
            else if (r == 2) {
                i++;
            }
            else {
                int temp = A[i];
                A[i] = A[three];
                A[three] = temp;
                three--;
            }
        }
    }

}
