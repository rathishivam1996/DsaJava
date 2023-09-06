package org.example;


public class LinkedListGfg {
    static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
//
//        @Override
//        public String toString() {
//            return "Node{" +
//                    "data=" + data +
//                    ", next=" + next != null ? next.data + " " : "null" +
//                    ", next=" + prev != null ? prev.data + " " : "null" +
//                    '}';
//        }
    }

    public static void main(String[] args) {
        LinkedListGfg list = new LinkedListGfg();
        list.addNode(1);
        list.addNode(2);
        list.addNode(3);
        list.addNode(4);
        list.addNode(5);

        Node root = new Node(1);
        Node r1 = new Node(2);
        Node r2 = new Node(3);
        Node r3 = new Node(4);

        root.next = r1;
        r1.next = r2;
        r2.next = r3;
        r3.next = r1;

        System.out.println("mid point of linked list: " + list.midPoint(head));
        System.out.println("Linked list has cycle?: " + hasCycle(root));
    }

    //Represent the head and tail of the singly linked list
    public static Node head = null;
    public static Node tail = null;

    //addNode() will add a new node to the list
    public void addNode(int data) {
        //Create a new node
        Node newNode = new Node(data);

        //Checks if the list is empty
        if (head == null) {
            //If list is empty, both head and tail will point to new node
            head = newNode;
            tail = newNode;
        } else {
            //newNode will be added after tail such that tail's next will point to newNode
            tail.next = newNode;
            //newNode will become new tail of the list
            tail = newNode;
        }
    }

    public int midPoint(Node node) {
        if (node == null) return -1;

        Node slow = node;
        Node fast = node;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow.data;
    }

    public Node reverseLinkedList(Node node) {
        if (node == null) return null;

        Node prev = null;
        Node current = node;
        Node next = null;
        while (current != null) {
            next = current.next;

            current.next = prev;

            prev = current;
            current = next;
        }
        return prev;
    }

//    public Node reverseDoublyLinkedList(Node node) {
//        if (node == null) return null;
//        Node curr = node;
//        Node temp = null;
//
//        while (curr != null) {
//            temp = curr.prev;
//            curr.prev = curr.next;
//
//            cu
//        }
//    }

    public static boolean hasCycle(Node root) {
        Node slow = root;
        Node fast = root;

        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
