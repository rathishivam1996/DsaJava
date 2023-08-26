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

    public List<T> levelOrder(Node<T> root) {
        if (root == null) return null;
        List<T> rv = new ArrayList<>();

        LinkedList<Node<T>> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            Node<T> curr = list.removeFirst();
            if (curr.left != null) list.addLast(curr.left);
            if (curr.right != null) list.addLast(curr.right);
            rv.add(curr.data);
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

    public int height(Node<T> root) {
        if (root == null) return 0;
        int lh = height(root.left) + 1;
        int rh = height(root.right) + 1;
        return Math.max(lh, rh);
    }
}
