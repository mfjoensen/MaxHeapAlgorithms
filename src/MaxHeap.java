import java.util.*;

/**
 * Can be used for Max-Heap-Insert and Heap-Extract-Max algorithms
 */
public class MaxHeap {
    private int[] Heap;
    private int size;
    private int maxSize;

    public MaxHeap(int maxsize) {
        this.maxSize = maxsize;
        this.size = 0;
        Heap = new int[this.maxSize + 1];
        Heap[0] = Integer.MAX_VALUE;
    }

    private int getParent(int pos) {
        return pos / 2;
    }

    private int getLeftChild(int pos) {
        return (2 * pos);
    }

    private int getRightChild(int pos) {
        return (2 * pos) + 1;
    }
    
    private boolean isLeaf(int pos) {
        return pos >= (size / 2) && pos <= size;
    }

    private void swap(int pos1, int pos2) {
        int temp;
        temp = Heap[pos1];
        Heap[pos1] = Heap[pos2];
        Heap[pos2] = temp;
    }

    private void maxHeapify(int pos) {
        if (isLeaf(pos))
            return;

        if (Heap[pos] < Heap[getLeftChild(pos)] ||
                Heap[pos] < Heap[getRightChild(pos)]) {

            if (Heap[getLeftChild(pos)] > Heap[getRightChild(pos)]) {
                swap(pos, getLeftChild(pos));
                maxHeapify(getLeftChild(pos));
            }
            else {
                swap(pos, getRightChild(pos));
                maxHeapify(getRightChild(pos));
            }
        }
    }

    public void addFix(int element) {
        Heap[++size] = element;

        int current = size;
        while (Heap[current] > Heap[getParent(current)]) {
            swap(current, getParent(current));
            current = getParent(current);
        }
    }

    public void add(int element) {
        Heap[++size] = element;
    }

    public void display() {
        for (int i = 1; i <= size; i++) {
            System.out.print("|" + Heap[i]);
        }
        System.out.print("|");
    }

    public int poll() {
        int max = Heap[1];
        Heap[1] = Heap[size--];
        maxHeapify(1);

        return max;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Array size:");
        int n = scan.nextInt();
        MaxHeap maxHeap = new MaxHeap(n);

        System.out.println("Choose mode:");
        System.out.println("1. Max-Heap-Insert");
        System.out.println("2. Heap-Extract-Max");
        int mode = scan.nextInt();

        System.out.println("Enter array (Only write integers, separated by spaces):");
        for (int i = 0; i < n; i++) {
            int v = scan.nextInt();
            switch (mode) {
                case 1 -> maxHeap.addFix(v);
                case 2 -> maxHeap.add(v);
            }
        }

        if (mode == 2) {
            System.out.println("Extracted " + maxHeap.poll());
        }

        System.out.println("Array Output:");

        maxHeap.display();
    }
}
