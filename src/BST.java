import java.util.*;

public class BST<K extends Comparable<K>, V>  implements Iterable<BST<K, V>.Entry> {
    private Node root;
    private int size = 0;
    private class Node {
        private K key;
        private V val;
        private Node left, right;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public class Entry {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
    public int size() {
        return size;
    }

    public void put(K key, V val) {
        Node newNode = new Node(key, val);
        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node current = root;
        Node parent = null;
        boolean found = false;

        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                current.val = val;
                found = true;
                break;
            }
        }

        if (!found) {
            int cmp = key.compareTo(parent.key);
            if (cmp < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            size++;
        }
    }

    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.val;
            }
        }
        return null;
    }

    public void delete(K key) {
        Node parent = null;
        Node current = root;

        while (current != null && !current.key.equals(key)) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) return;

        // Case 1: Node has no children or one child
        if (current.left == null || current.right == null) {
            Node replacement = (current.left != null) ? current.left : current.right;

            if (parent == null) {
                root = replacement;
            } else if (parent.left == current) {
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }
            size--;
        }
        // Case 2: Node has two children
        else {
            // Find the inorder successor (smallest in right subtree)
            Node successorParent = current;
            Node successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            if (successorParent != current) {
                successorParent.left = successor.right;
                successor.right = current.right;
            }

            successor.left = current.left;

            if (parent == null) {
                root = successor;
            } else if (parent.left == current) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            size--;
        }
    }

    @Override
    public Iterator<Entry> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Entry> {
        private final Stack<Node> stack = new Stack<>();

        public BSTIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Entry next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node node = stack.pop();
            pushLeft(node.right);

            return new Entry(node.key, node.val);
        }
    }

    public static void main(String[] args) {
        BST<String, Integer> tree = new BST<>();
        tree.put("D", 4);
        tree.put("B", 2);
        tree.put("F", 6);
        tree.put("A", 1);
        tree.put("C", 3);
        tree.put("E", 5);
        tree.put("G", 7);

        System.out.println("In-order traversal:");
        for (BST<String, Integer>.Entry entry : tree) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        System.out.println("\nSize: " + tree.size());
    }


}
