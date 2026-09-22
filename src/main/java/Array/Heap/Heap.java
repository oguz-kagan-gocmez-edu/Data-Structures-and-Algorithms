package Array.Heap;

public abstract class Heap {

    protected HeapNode[] array;
    protected int count;

    protected int N;

    abstract protected void percolateDown(int no);
    abstract protected void percolateUp(int no);

    abstract public void update(int k, int newValue);
    public Heap(int N){
        array = new HeapNode[N];
        count = 0;
        this.N = N;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public boolean isFull(){
        return count == N;
    }

    protected void swapNode(int index1, int index2){
        HeapNode tmpNode;
        tmpNode = array[index1];
        array[index1] = array[index2];
        array[index2] = tmpNode;
    }

    public HeapNode delete(){
        HeapNode tmp;
        tmp = array[0];
        array[0] = array[count - 1];
        percolateDown(0);
        count--;
        return tmp;
    }

    public int search(int name){
        for (int i = 0; i < count; i++){
            if (array[i].getName() == name){
                return i;
            }
        }
        return -1;
    }

    public void insert(HeapNode node){
        count++;
        array[count - 1] = node;
        percolateUp(count - 1);
    }

    public void insertArray(int[] data){
        for (int datum : data) {
            insert(new HeapNode(datum, datum));
        }
    }

    public String toString(){
        String s = "";
        for (int i = 0; i < count; i++){
            s += array[i].getData() + " ";
        }
        return s.trim();
    }

    /**
     * Write the method in {\bf Heap} class which returns the number of pairs of elements that have the same
     * grandparent. The parent of the element at index $i$ is at index $(i-1)/2$, and the parent of the root is the root
     * itself. Every unordered pair should be counted once.
     */
    public int howManyPairGrandParentEqual(){
        int count = 0;
        for (int i = 0; i < this.count; i++) {
            int parentI = (i == 0) ? 0 : (i - 1) / 2;
            int grandParentI = (parentI == 0) ? 0 : (parentI - 1) / 2;
            for (int j = i + 1; j < this.count; j++) {
                int parentJ = (j == 0) ? 0 : (j - 1) / 2;
                int grandParentJ = (parentJ == 0) ? 0 : (parentJ - 1) / 2;
                if (grandParentI == grandParentJ) {
                    count++;
                }
            }
        }
        return count;
    }

}
