package Tree;

public class Tree {

    protected TreeNode root;

    public Tree(){
        root = null;
    }

    public TreeNode getRoot(){
        return root;
    }

    public void setRoot(TreeNode root){
        this.root = root;
    }

    protected void insertChild(TreeNode parent, TreeNode child){
        if (parent == null) {
            root = child;
        } else {
            if (child.data < parent.data) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
    }

    public void iterativeInsert(TreeNode node){
        TreeNode parent = null;
        TreeNode tmp = root;
        while (tmp != null) {
            parent = tmp;
            if (node.getData() < tmp.getData()){
                tmp = tmp.getLeft();
            } else {
                tmp = tmp.getRight();
            }
        }
        insertChild(parent, node);
    }

    public void prettyPrint(){
        if (root != null){
            root.prettyPrint(0);
        }
    }

    public void insertArray(int[] data) {
        for (int datum : data) {
            iterativeInsert(new TreeNode(datum));
        }
    }

    /**
     * Write a non-recursive method which returns a string that contains the two smallest numbers in the binary search tree: You are not allowed
     * to use any external data structures. (Hint: There are 3 cases: (i) The smallest number is on the left side and
     * it has right descendants, (ii) The smallest number is on the left side and it has no right descendants,
     * (iiii) The smallest number is the root.)
     */
    public String bottomTwo(){
        if (root == null) return "";
        TreeNode prev = null;
        TreeNode temp = root;
        
        while (temp != null && temp.left != null) {
            prev = temp;
            temp = temp.left;
        }
        if (temp.right != null) {
            prev = temp;
            temp = temp.right;
        }
        
        return Math.min(prev.data, temp.data) + " " + Math.max(prev.data, temp.data);
    }

    /**
     * Write a \textbf{non-recursive} method in Tree class that computes and returns the number of nodes with even
     * values in a binary search tree by using queue. You are only allowed to traverse the tree once.
     */
    public int countEvenNodes(){
        int even = 0;
        Queue queue = new Queue(1000);
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            TreeNode candidate = queue.dequeue();
            if (candidate.data % 2 == 0) even++;
            if (candidate.left != null) queue.enqueue(candidate.left);
            if (candidate.right != null) queue.enqueue(candidate.right);
        }
        return even;
    }

    /**
     * Write a non-recursive method in Tree class that returns the depth of the node containing a given data $X$ in a
     * binary search tree. You are not allowed to use any tree methods, just attributes, constructors, getters and
     * setters. Depth of the root node is 1.
     */
    public int depthOfNode(int X){
        int depth = 1;
        TreeNode temp = root;
        while (temp != null && temp.data != X) {
            if (temp.data < X) {
                if (temp.right != null) temp = temp.right;
            } else if (temp.data > X){
                if (temp.left != null) temp = temp.left;
            }
            depth++;
        }
        return depth;
    }

    /**
     * T1 and T2 are two binary trees. Write the recursive method in Tree class to determine if T1 is identical to T2.
     */
    public boolean isIdentical(TreeNode T1, TreeNode T2){
        if (T1 == null && T2 == null) return true;
        if (T1 == null || T2 == null) return false;
        if (T1.data != T2.data) return false;
        
        boolean leftSearch = isIdentical(T1.left, T2.left);
        boolean rightSearch = isIdentical(T1.right, T2.right);
        
        return leftSearch && rightSearch;
    }

    /**
     * Write a nonrecursive method using Stack that finds the difference between the number of leftist nodes and
     * rightist nodes in a binary search tree. A node is leftist~(rightist) if it has only left~(right) child.
     */
    public int leftistOrRightist(){
        Stack stack = new Stack(1000);
        stack.push(root);
        int leftist = 0, rightist = 0;
        while (!stack.isEmpty()) {
            TreeNode cd = stack.pop();
            if (cd.left == null && cd.right != null) rightist++;
            if (cd.left != null && cd.right == null) leftist++;
            
            if (cd.left != null) stack.push(cd.left);
            if (cd.right != null) stack.push(cd.right);
        }
        return leftist - rightist;
    }

    /**
     * Write a non-recursive method in the {\bf Tree} class, which returns the keys on the path as an array, where the
     * path is defined by the current parent as follows: If the parent is odd, go left; otherwise go right. The array
     * should contain only that many items not more not less.
     */
    public int[] pathList(){
        if (root == null) return null;
        int[] path = new int[0];
        TreeNode temp = root;
        while (temp != null) {
            int [] newPath = new int[path.length + 1];
            for (int i = 0; i < path.length; i++) {
                newPath[i] = path[i];
            }
            newPath[path.length] = temp.data;
            path = newPath;
            if (temp.data % 2 == 0) {
                if (temp.right != null) temp = temp.right;
                else break;
            } else {
                if (temp.left != null) temp = temp.left;
                else break;
            }
        }
        return path;
    }

    /**
     * Write the non-recursive method in Tree class that computes the products of all keys in a binary search tree by
     * using stack.
     */
    public int product(){
        Stack stack = new Stack(1000);
        stack.push(root);
        int product = 1;
        while (!stack.isEmpty()) {
            TreeNode cd = stack.pop();
            product *= cd.data;
            if (cd.left != null) stack.push(cd.left);
            if (cd.right != null) stack.push(cd.right);
        }
        return product;
    }

    /**
     * Write a non-recursive method in Tree class, which first finds the minimum (A) and maximum (B) elements in the
     * tree. The method will then randomly search a number between [A, B] $N$ times and returns the average number of
     * nodes visited in this search. You are not allowed to use any tree methods.
     */
    public double simulateSearch(int N){
        return 0;
    }

    /**
     * Write a non-recursive method in Tree class, which sums the keys on the path, where the path is defined by the
     * parameter path as follows: (i) Path consists of 0's and 1's such as 10011. (ii) A 1 represents to go right, a 0
     * represents to go left. If the path is 1011, you start from root, you go first right, then left, then right, then
     * right. If the path is 001, you start from root, you go first left, then left, then right. You will use charAt
     * function in strings.
     */
    public int sumOfPath(String path){
        if (root == null) return 0;
        TreeNode temp = root;
        int sum = temp.data;
        for (int i = 0; i < path.length() && temp != null; i++) {
            if (path.charAt(i) == '0') {
                if (temp.left != null) temp = temp.left;
            } else if (path.charAt(i) == '1') {
                if (temp.right != null) temp = temp.right;
            } else break;
            if (temp == null) break;
            sum += temp.data;
        }
        return sum;
    }

    /**
     * Write a non-recursive method that computes and returns the number of nodes with odd values in a binary search
     * tree by using queue. You are only allowed to traverse the tree once. You may assume tree has at least 1 node.
     */
    public int countOddNodes(){
        int odd = 0;
        Queue queue = new Queue(1000);
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            TreeNode candidate = queue.dequeue();
            if (candidate.data % 2 == 1) odd++;
            if (candidate.left != null) queue.enqueue(candidate.left);
            if (candidate.right != null) queue.enqueue(candidate.right);
        }
        return odd;
    }

    /**
     * Write a nonrecursive method in {\bf Tree} class using Stack which returns the sum of the cubic powers of all keys
     * in a binary search tree. Push the root first, then repeatedly pop a node, add the cube of its key to the sum and
     * push its existing children, until the stack becomes empty.
     */
    public int cubicSumOfTree(){
        int sum = 0;
        Stack stack = new Stack(100);
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            int key = current.getData();
            sum += key * key * key;
            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }
            if (current.getRight() != null) {
                stack.push(current.getRight());
            }
        }
        return sum;
    }

    /**
     * T1 and T2 are two binary trees. Write the recursive method in {\bf Tree} class to determine whether T2 is a
     * subtree of T1, that is, whether some node of T1 roots a tree which is identical to T2. An empty T2 is a subtree of
     * every tree.
     */
    public boolean isSubtree(TreeNode T1, TreeNode T2){
        if (T2 == null) return true;
        if (T1 == null) return false;
        if (isIdentical(T1, T2)) return true;
        return isSubtree(T1.left, T2) || isSubtree(T1.right, T2);
    }

    /**
     * Write a nonrecursive method in {\bf Tree} class which returns whether the binary tree is two colorable, that is,
     * whether every node can be given one of two colors so that no node has the same color as one of its children.
     * Traverse the tree in breadth first order using an external array based queue, and carry the color of each node in
     * a parallel array where both children get the opposite color of their parent.
     */
    // WARNING! Modifying arrays or collection values is not allowed!
    public boolean isTwoColorableTree(){
        TreeNode[] queue = new TreeNode[100];
        int[] colors = new int[100];
        int front = 0;
        int rear = 0;
        queue[rear] = root;
        colors[rear] = 0;
        rear++;
        while (front < rear) {
            TreeNode current = queue[front];
            int currentColor = colors[front];
            front++;
            int childColor = 1 - currentColor;
            if (current.getLeft() != null) {
                queue[rear] = current.getLeft();
                colors[rear] = childColor;
                rear++;
            }
            if (current.getRight() != null) {
                queue[rear] = current.getRight();
                colors[rear] = childColor;
                rear++;
            }
        }
        return true;
    }

}
