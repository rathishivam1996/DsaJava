package org.example;

import java.util.*;

public class Tree<T> {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();

        Node<Integer> root = tree.buildTree("5 3 7 2 4 6 9 1 N N N N N 8 N N N");
        System.out.println("level order: " + tree.levelOrder(root));
        System.out.println("inorder iterative: " + tree.inorderIterative(root));
        System.out.println("height: " + tree.height(root));
        System.out.println("preOrderIterative: " + tree.preorderIterative(root));
        System.out.println("postOrderIterative: " + tree.postOrderIterative(root));
    }

    private static class Node<T> {
        private final T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + this.data +
                    ", left=" + this.left.data +
                    ", right=" + this.right.data +
                    '}';
        }
    }

    public Node<Integer> buildTree(String str) {

        if (str.length() == 0 || str.charAt(0) == 'N') {
            return null;
        }

        String[] ip = str.split(" ");
        // Create the root of the tree
        Node<Integer> root = new Node<Integer>(Integer.parseInt(ip[0]));
        // Push the root to the queue

        Queue<Node<Integer>> queue = new LinkedList<>();

        queue.add(root);
        // Starting from the second element

        int i = 1;
        while (queue.size() > 0 && i < ip.length) {

            // Get and remove the front of the queue
            Node<Integer> currNode = queue.peek();
            queue.remove();

            // Get the current node's value from the string
            String currVal = ip[i];

            // If the left child is not null
            if (!currVal.equals("N")) {

                // Create the left child for the current node
                currNode.left = new Node<Integer>(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }

            // For the right child
            i++;
            if (i >= ip.length)
                break;

            currVal = ip[i];

            // If the right child is not null
            if (!currVal.equals("N")) {

                // Create the right child for the current node
                currNode.right = new Node<Integer>(Integer.parseInt(currVal));

                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }

        return root;
    }

    public void inorder(Node<T> node) {
        if (node == null) return;

        inorder(node.left);
        System.out.print(node.data + " ->");
        inorder(node.right);
    }

    public void inorder(Node<T> node, List<Node<T>> inorder) {
        if (node == null) return;

        inorder(node.left);
        inorder.add(node);
        inorder(node.right);
    }

    public List<T> inorderIterative(Node<T> root) {
        if (root == null) return null;

        List<T> returnList = new ArrayList<>();
        Stack<Node<T>> stack = new Stack<>();

        Node<T> curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                returnList.add(curr.data);
                curr = curr.right;
            }
        }
        return returnList;
    }

    public List<T> preorderIterative(Node<T> root) {
        if (root == null) return null;
        List<T> rv = new ArrayList<>();
        Stack<Node<T>> stack = new Stack<>();
        stack.add(root);

        // r->left->right
        while (!stack.isEmpty()) {
            Node<T> curr = stack.pop();
            rv.add(curr.data);
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
        return rv;
    }


    public List<T> postOrderIterative(Node<T> root) {
        if (root == null) return null;

        List<T> rv = new ArrayList<>();
        Stack<Node<T>> stack = new Stack<>();
        Stack<Node<T>> temp = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> curr = stack.pop();
            temp.push(curr);
            if (curr.left != null) stack.push(curr.left);
            if (curr.right != null) stack.push(curr.right);
        }
        while (!temp.isEmpty()) {
            rv.add(temp.pop().data);
        }
        return rv;
    }

    public List<List<T>> levelOrder(Node<T> root) {
        if (root == null) return null;
        List<List<T>> rv = new ArrayList<>();

        LinkedList<Node<T>> currLevel = new LinkedList<>();
        LinkedList<Node<T>> nextLevel = new LinkedList<>();
        List<T> currResult = new ArrayList<>();
        currLevel.add(root);
        while (!currLevel.isEmpty()) {
            Node<T> curr = currLevel.removeFirst();
            if (curr.left != null) nextLevel.addLast(curr.left);
            if (curr.right != null) nextLevel.addLast(curr.right);
            currResult.add(curr.data);
            if (currLevel.isEmpty()) {
                rv.add(currResult);
                currResult = new ArrayList<>();
                LinkedList<Node<T>> temp = nextLevel;
                nextLevel = currLevel;
                currLevel = temp;
            }
        }
        return rv;
    }

    public int height(Node<T> root) {
        if (root == null) return 0;
        int lh = height(root.left) + 1;
        int rh = height(root.right) + 1;
        return Math.max(lh, rh);
    }

    public boolean isSame(Node<T> r1, Node<T> r2) {
        if (r1 == null && r2 == null) return true;
        if (r1 == null || r2 == null) return false;
        return r1.data == r2.data
                && isSame(r1.left, r2.left)
                && isSame(r1.right, r2.right);
    }

    public Node<T> invertTree(Node<T> root) {
        if (root == null) return null;
        invertTree(root.left);
        invertTree(root.right);

        Node<T> temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    public boolean isSymmetric(Node<T> root) {
        if (root == null) return true;
        return isSame(root.left, root.right);
    }

    public boolean isMirror(Node<T> r1, Node<T> r2) {
        if (r1 == null && r2 == null) return true;
        if (r1 == null || r2 == null) return false;

        return r1.data == r2.data
                && isMirror(r1.left, r2.right)
                && isMirror(r1.right, r2.left);
    }
}
