package Array.Heap;

public abstract class DHeap extends Heap {

    protected int d;

    public DHeap(int N, int d){
        super(N);
        this.d = d;
    }

    /**
     * Write the method in {\bf DHeap} class which returns the smallest element of a max $d$-heap. Since the smallest
     * element of a max heap is always a leaf, scan only the leaves, which start at index ${\it count}/d + 1$.
     */
    public int smallest(){
        int firstLeaf = this.count / this.d + 1;
        int minIndex = firstLeaf;
        for (int i = firstLeaf + 1; i < this.count; i++) {
            if (array[i].getData() < array[minIndex].getData()) {
                minIndex = i;
            }
        }
        return array[minIndex].getData();
    }

}
