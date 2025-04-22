package lab2.ilayda;

import java.util.Random;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        testBST();
        benchmarkRandomTrees();
        benchmarkSortedTrees();
    }

    public static void testBST() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        System.out.println("Adding elements: 10, 5, 15, 3, 7, 12, 18");
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(12);
        tree.add(18);

        System.out.println("Tree size: " + tree.size()); // Bör vara 7
        System.out.println("Search 7: " + tree.searchFor(7)); // true
        System.out.println("Search 13: " + tree.searchFor(13)); // false
        System.out.println("Tree contents (in-order): " + tree.toString()); // 3 5 7 10 12 15 18

        System.out.println("Removing 5 and 15...");
        tree.remove(5);
        tree.remove(15);

        System.out.println("Tree size after removals: " + tree.size()); //Bör vara 5
        System.out.println("Tree contents after removals (in-order): " + tree.toString()); // 3 7 10 12 18

        System.out.println("Clearing the tree...");
        tree.clear();

        System.out.println("Tree size after clear: " + tree.size()); // Bör vara 0
        System.out.println("Tree contents after clear: '" + tree.toString() + "'"); // Bör vara tom
    }

    public static void benchmarkRandomTrees() {
        System.out.println("\nBenchmarking with random trees:");
        Random rand = new Random();
        int repetitions = 1000;

        for (int size = 20000; size <= 320000; size *= 2) {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();
            for (int i = 0; i < size; i++) {
                tree.add(rand.nextInt(size * 10));
            }

            int[] queries = new int[repetitions];
            for (int i = 0; i < repetitions; i++) {
                queries[i] = rand.nextInt(size * 10);
            }

            long start = System.nanoTime();
            for (int q : queries) {
                tree.searchFor(q);
            }
            long end = System.nanoTime();

            System.out.println("Size: " + size + ", Time (ms): " + (end - start) / 1_000_000.0);
        }
    }

    public static void benchmarkSortedTrees() {
        System.out.println("\nBenchmarking with sorted trees:");
        int repetitions = 1000;

        for (int size = 20000; size <= 320000; size *= 2) {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();
            for (int i = 0; i < size; i++) {
                tree.add(i);
            }

            Random rand = new Random();
            int[] queries = new int[repetitions];
            for (int i = 0; i < repetitions; i++) {
                queries[i] = rand.nextInt(size * 2);
            }

            long start = System.nanoTime();
            for (int q : queries) {
                tree.searchFor(q);
            }
            long end = System.nanoTime();

            System.out.println("Size: " + size + ", Time (ms): " + (end - start) / 1_000_000.0);
        }
    }
}
