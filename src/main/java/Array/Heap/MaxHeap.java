package Array.Heap;

public class MaxHeap extends Heap{

    public MaxHeap(int N) {
        super(N);
    }

    protected void percolateDown(int no){
        int left, right;
        left = 2 * no + 1;
        right = 2 * no + 2;
        while ((left < count && array[no].getData() < array[left].getData()) ||
                (right < count && array[no].getData() < array[right].getData())){
            if (right >= count || array[left].getData() > array[right].getData()){
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
        while (parent >= 0 && array[parent].getData() < array[no].getData()){
            swapNode(parent, no);
            no = parent;
            parent = (no - 1) / 2;
        }
    }

    public void update(int k, int newValue){
        int oldValue = array[k].getData();
        array[k].setData(newValue);
        if (oldValue > newValue){
            percolateDown(k);
        } else {
            percolateUp(k);
        }
    }

    /**
     * Given the index of a heap node in a binary max heap, write the method in {\bf MaxHeap} class that returns the
     * indexes of its ascendants (parent, grandparent, grandgrandparent, $\ldots$) of this node. The array should contain
     * only that many items not more not less.
     */
    public int[] ascendants(int index){
        if (index == 0) {
            return new int[0];
        } else {
            int[] upper = ascendants((index - 1) / 2);
            int[] result = new int[upper.length + 1];
            for (int i = 0; i < upper.length; i++) {
                result[i] = upper[i];
            }
            result[result.length - 1] = (index - 1) / 2;
            return result;
        }
        // non - recursive solution as follows.
        /*
        int size = (int) (Math.log(index + 1) / Math.log(2));

        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[result.length - 1 - i] = (index - 1) / 2;
            index = (index - 1) / 2;
        }
        return result;*/
    }

    /**
     * Given the index of a heap node in a max-heap, write a method that determines if the subtree rooted from the node
     * with index satisfies the heap-order property. Do not use any class or external methods.
     */
    public boolean heapOrder(int index){
        for (int i = index ; i < count && 2 * index + i < count; i++) {
            int child = 2 * index + i;
            if (array[i].getData() < array[child].getData()) return false;
        }
        return true;
    }

    /**
     * Write the method in {\bf MaxHeap} class that returns the shortest distance between two nodes in the heap with
     * indexes index1 and index2. Generate the ascendant lists of the nodes with index1 and index2 (You can assume the
     * tree depth is smaller than 100). Compare those lists to solve the problem.
     */
    public int shortestDistanceBetWeenNodes(int index1, int index2){
        int distance = 0;
        if (index1 == index2) {
            return distance;
        } else {
            while (index1 != index2) {
                if (index1 < index2) {
                    index2 = (index2 - 1) / 2;
                } else if (index1 > index2) {
                    index1 = (index1 - 1) / 2;
                }
                distance++;
            }
        }
        return distance;
    }

    /**
     * Write the method in MaxHeap class that returns the third maximum number in the heap. Your method should run in
     * {\cal O}(1) time. You are not allowed to use any class method except getData.
     */
    public int third(){
        if (array.length < 3) return Integer.MIN_VALUE;
        int max = array[0].getData();
        int best2 = array[1].getData() > array[2].getData() ? array[1].getData() : array[2].getData();
        int best3 = array[1].getData() > array[2].getData() ? array[2].getData() : array[1].getData();
        
        boolean isLeftBigger = (array[1].getData() > array[2].getData());
        
        if (isLeftBigger) {
            int leftLeft = (3 < count) ? 3 : -1;
            int leftRight =  (4 < count) ? 4 : -1;
            if (leftRight == -1 && leftLeft != -1 && array[leftLeft].getData() > best3) {
                best3 = array[leftLeft].getData();
            } else if (leftRight != -1) {
                int tmp = array[leftLeft].getData() > array[leftRight].getData() ? array[leftLeft].getData() : array[leftRight].getData();
                if (tmp > best3)
                    best3 = tmp;
            }
        } else  {
            int rightLeft = 5 < count ? 5 : -1;
            int rightRight = 6 < count ? 6 : -1;
            if (rightRight == -1 && rightLeft != 1 && array[rightLeft].getData() > best3) {
                best3 = array[rightLeft].getData();
            } else if (rightRight != -1) {
                int tmp = array[rightLeft].getData() > array[rightRight].getData() ? array[rightLeft].getData() : array[rightRight].getData();
                best3 = tmp;
            }
        }
        return best3;
    }

}
