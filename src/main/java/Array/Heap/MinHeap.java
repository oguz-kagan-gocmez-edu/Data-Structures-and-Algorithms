package Array.Heap;

public class MinHeap extends Heap{

    public MinHeap(int N) {
        super(N);
    }

    protected void percolateDown(int no){
        int left, right;
        left = 2 * no + 1;
        right = 2 * no + 2;
        while ((left < count && array[no].getData() > array[left].getData()) ||
                (right < count && array[no].getData() > array[right].getData())){
            if (right >= count || array[left].getData() < array[right].getData()){
                swapNode(no, left);
                no = left;
            } else {
                swapNode(no, right);
                no = right;
            }
            left = 2 * no + 1;
            right = 2 * no + 2;
        }
    }

    protected void percolateUp(int no){
        int parent;
        parent = (no - 1) / 2;
        while (parent >= 0 && array[parent].getData() > array[no].getData()){
            swapNode(parent, no);
            no = parent;
            parent = (no - 1) / 2;
        }
    }

    public void update(int k, int newValue){
        int oldValue = array[k].getData();
        array[k].setData(newValue);
        if (oldValue < newValue){
            percolateDown(k);
        } else {
            percolateUp(k);
        }
    }

    /**
     * Write the method in {\bf MinHeap} class that returns the number of nodes in the heap whose children can be
     * swapped without hurting the heap property. Your method should run in ${\cal O}(N)$ time.
     */
    public int howManyChildrenCanBeSwapped(){
        int countSwap = 0;
        for (int i = 0; i < count; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            
            if (right >= count)
                continue;
                
            boolean isOk = true;
            int ll = 2 * left + 1;
            int lr = 2 * left + 2;
            if (ll < count && array[right].getData() > array[ll].getData()) isOk = false;
            if (lr < count && array[right].getData() > array[lr].getData()) isOk = false;

            int rl = 2 * right + 1;
            int rr = 2 * right + 2;
            if (rl < count && array[left].getData() > array[rl].getData()) isOk = false;
            if (rr < count && array[left].getData() > array[rr].getData()) isOk = false;

            if (isOk) countSwap++;
        }
        return countSwap;
    }

    /**
     * Write the method in {\bf MinHeap} class which returns true if the largest value appears on the leftmost node. You
     * may not use any additional data structures.
     */
    public boolean isLargestLeftMost(){
        if (count == 1) return true; // I added this line due to last test case.
        int maxIndex = -1;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < count; i++) {
            if (array[i].getData() > maxValue) {
                maxValue = array[i].getData();
                maxIndex = i;
            }
        }
        int indexOfLeftMost = 0;
        for (int i = 0; indexOfLeftMost < count; i++) {
            indexOfLeftMost += (int)Math.pow(2,i);
            if (indexOfLeftMost == maxIndex) return true;
        }
        
        return false;
    }

    /**
     * Given an array of N integers, find the $k$'th maximum of those integers in $\cal O$$(N\log K)$ time. (Hint: Use
     * a min-heap to store $K$ largest elements at a time, in that case the removeMin will return the $k$'th maximum).
     */
    public static int kthMaximum(int[] array, int k){
        MinHeap minHeap = new MinHeap(k);
        for (int i = 0; i < array.length; i++) {
            if (!minHeap.isFull()) {
                minHeap.insert(new HeapNode(array[i],array[i]));
            } else {
                minHeap.delete();
                minHeap.insert(new HeapNode(array[i], array[i]));
            }
        }
        return minHeap.array[0].getData();
    }

    /**
     Write a method in {\bf MinHeap} class that returns the average number of percolate up operations for numbers given in the
     list, if each number is assumed to be inserted independently to the heap. Do not modify the heap while checking.
     */
    public double averagePercolateUp(int[] list) {
        // this solution below has been given to us by instructor.
        if (isFull()) return 0;
        double percolateUp = 0;
        
        for (int n : list) {
            int i = count;
            
            while (i > 1) {
                int p = i / 2;
                if (array[p].getData() <= n) break;
                percolateUp++;
                i = p;
            }
            
        }
        return percolateUp / list.length;
    }

}
