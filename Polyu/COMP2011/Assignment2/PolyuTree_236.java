package comp2011.a2; // Don't change this line!
/**
 *
 * @author Yixin Cao (November 1, 2025)
 *
 * A binary search tree for Polyu students.
 * Since we only store students, the class doesn't use generics.
 *
 * You are forbidden to use {@code import} or {@code java.} in this file.
 *
 * Do not change the signature of any given method, but feel free to introduce new ones, which must be {@code private}.
 */
public class PolyuTree_236 { // Please replace 000 with your secret number!
    /**
     * No modification to the class {@code Student} is allowed.
     * If you change anything in this class, your work will not be graded.
     */
    static class Student {
        String id;
        String name;

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String toString() {
            return id + ", " + name;
        }
    }

    /**
     * No modification to the class {@code Node} is allowed.
     * If you change anything in this class, your work will not be graded.
     */
    private class Node {
        Student e;
        public Node lc, rc; // left child; right child

        @SuppressWarnings("unused")
        public Node(Student data) {
            this.e = data;
        }

        public String toString() {
            return e.toString();
        }
    }

    Node root;

    /**
     * Insert a new student into the tree.
     *
     * VERY IMPORTANT.
     * I've sought help from the following GenAI:
     *     1. poe
     *     2.
     *     3.
     *     ...
     *
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1.
     *     2.
     *     3.
     *     ...
     *
     * I've sought help from the following Internet resources and books:
     *     1. geeksforgeeks
     *     2. youtube
     *     3.
     *     ...
     *
     *
     * Running time: O(n). A function of d and n.
     */
    public void insert(Student s) {
        root = insertRecursion(root, s);
    }
    private Node insertRecursion(Node root, Student s) {
        if (root == null) {
            return new Node(s);
        }
        int cmp = s.name.compareTo(root.e.name);
        if (cmp < 0) {
            root.lc = insertRecursion(root.lc, s);
        } else if (cmp > 0)  {
            root.rc = insertRecursion(root.rc, s);
        }
        return root;
    }

    /**
     * Calculate the largest difference between the depths of the two subtrees of a
     * node.
     *
     * VERY IMPORTANT.
     *
     * I've sought help from the following GenAI:
     *     1. poe
     *     2.
     *     3.
     *     ...
     *
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1.
     *     2.
     *     3.
     *     ...
     *
     * I've sought help from the following Internet resources and books:
     *     1. Geeksforgeeks
     *     2.
     *     3.
     *     ...
     *
     *
     * Running time: O(n). A function of d and n.
     */
    public int maxDepthDiff() {
        return maxDepthDiffRecursion(root);
    }

    private int maxDepthDiffRecursion(Node node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = maxDepth(node.lc);
        int rightDepth = maxDepth(node.rc);
        int depthDiff = Math.abs(leftDepth - rightDepth);

        return Math.max(depthDiff, Math.max(maxDepthDiffRecursion(node.lc), maxDepthDiffRecursion(node.rc)));
    }

    private int maxDepth(Node node) {
        if (node == null){
            return 0;
        }
        return 1 + Math.max(maxDepth(node.lc), maxDepth(node.rc));
    }

    /**
     * Calculate the largest difference between the sizes of the two subtrees of a
     * node.
     *
     * VERY IMPORTANT.
     *
     * I've sought help from the following GenAI:
     *     1. poe
     *     2.
     *     3.
     *     ...
     *
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1.
     *     2.
     *     3.
     *     ...
     *
     * I've sought help from the following Internet resources and books:
     *     1. Geeksforgeeks
     *     2.
     *     3.
     *     ...
     *
     *
     * Running time: O(n). A function of d and n.
     */
    public int maxSizeDiff() {
        return maxSizeDiffRecursion(root);
    }

    private int maxSizeDiffRecursion(Node node) {
        if (node == null){
            return 0;
        }

        int leftSize = size(node.lc);
        int rightSize = size(node.rc);
        int sizeDiff = Math.abs(leftSize - rightSize);

        return Math.max(sizeDiff, Math.max(maxSizeDiffRecursion(node.lc), maxSizeDiffRecursion(node.rc)));
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.lc) + size(node.rc);
    }

    /**
     * Calculate the number of nodes that have only one child.
     *
     * VERY IMPORTANT.
     *
     * I've sought help from the following GenAI:
     *     1. poe
     *     2.
     *     3.
     *     ...
     *
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1.
     *     2.
     *     3.
     *     ...
     *
     * I've sought help from the following Internet resources and books:
     *     1. Geeksforgeeks
     *     2.
     *     3.
     *     ...
     *
     *
     * Running time: O( ). A function of d and n.
     */
    public int nodesWithOneChild() {
        return nodesWithOneChildRecursion(root);
    }

    private int nodesWithOneChildRecursion(Node node) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        if ((node.lc == null && node.rc != null) || (node.lc != null && node.rc == null)) {
            count = 1;
        }
        return count + nodesWithOneChildRecursion(node.lc) + nodesWithOneChildRecursion(node.rc);
    }

    /**
     * Find a student with the specified name.
     * You may return any of them if there are multiple students of this name.
     *
     * VERY IMPORTANT.
     *
     * I've sought help from the following GenAI:
     *     1. poe
     *     2.
     *     3.
     *     ...
     *
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1.
     *     2.
     *     3.
     *     ...
     *
     * I've sought help from the following Internet resources and books:
     *     1. Geeksforgeeks
     *     2.
     *     3.
     *     ...
     *
     *
     * Running time: O(n). A function of d and n.
     */
    public Student searchFullname(String name) {
        return searchFullnameRecursion(root, name);
    }

    private Student searchFullnameRecursion(Node node, String name) {
        if (node == null) {
            return null;
        }

        if (node.e.name.equals(name)) {
            return node.e;
        }

        int cmp = name.compareTo(node.e.name);
        if (cmp < 0) {
            return searchFullnameRecursion(node.lc, name);
        } else {
            return searchFullnameRecursion(node.rc, name);
        }
    }

    /**
     * Find all students with the specified surname.
     * Consider the first word as the surname.
     * Warning: Make sure "Liu Dennis" does not show when you search "Li."
     *
     * VERY IMPORTANT.
     *
     * I've sought help from the following GenAI:
     *     1. poe
     *     2.
     *     3.
     *     ...
     *
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1.
     *     2.
     *     3.
     *     ...
     *
     * I've sought help from the following Internet resources and books:
     *     1. Geeksforgeeks
     *     2.
     *     3.
     *     ...
     *
     *
     * Running time: O(n). A function of d and n.
     */
    public Student[] searchSurname(String surname) {
        return searchSurnameRecursion(root, surname);
    }

    private Student[] searchSurnameRecursion(Node node, String surname) {
        if (node == null) {
            return new Student[0];
        }
        int count = 0;
        if (node.e.name.startsWith(surname)) {
            count++;
        }
        count += size(node.lc) + size(node.rc);
        Student[] results = new Student[count];
        fillSurnameResults(node, surname, results, new int[]{0});
        return results;
    }

    private void fillSurnameResults(Node node, String surname, Student[] results, int[] index) {
        if (node == null) {
            return;
        }
        if (node.e.name.startsWith(surname)) {
            results[index[0]] = node.e;
            index[0]++;
        }
        fillSurnameResults(node.lc, surname, results, index);
        fillSurnameResults(node.rc, surname, results, index);
    }
}
