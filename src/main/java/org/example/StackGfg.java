package org.example;

import java.security.PublicKey;
import java.util.LinkedList;
import java.util.Stack;

public class StackGfg {
    public static void main(String[] args) {
//        System.out.println(infixToPrefix("a+b*c-d/e"));
        Node node = infixToExpressionTree("a+b*c-d/e");
        levelOrder(node);
    }

    public static String infixToPostFix(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder rv = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);
            if (isOperator(ch)) {
                if (ch == '(') {
                    stack.push(ch);
                } else if (ch == ')') {
                    while (stack.peek() != '(') {
                        rv.append(stack.pop());
                    }
                } else {
                    while (!stack.isEmpty() && getPriority(ch) <= getPriority(stack.peek())) {
                        rv.append(stack.pop());
                    }
                    stack.push(ch);
                }
            } else {
                rv.append(ch);
            }
        }
        while (!stack.isEmpty()) {
            rv.append(stack.pop());
        }
        return rv.toString();
    }

    public static boolean isOperator(char ch) {
        return ch == '(' || ch == ')' || ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public static int getPriority(char ch) {
        switch (ch) {
            case '+', '-' -> {
                return 1;
            }

            case '*', '/' -> {
                return 2;
            }

            default -> {
                return -1;
            }
        }
    }

    public static String infixToPrefix(String infix) {
        Stack<Character> operatorStack = new Stack<>();
        Stack<String> operandStack = new Stack<>();
        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);
            if (isOperator(ch)) {
                if (ch == '(') {
                    operatorStack.push(ch);
                } else if (ch == ')') {
                    while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                        String ch1 = operandStack.pop();
                        String ch2 = operandStack.pop();
                        char op = operatorStack.pop();
                        String temp = op + ch2 + ch1;

                        operandStack.push(temp);
                    }
                } else {
                    while (!operatorStack.isEmpty() && getPriority(ch) <= getPriority(operatorStack.peek())) {
                        char op = operatorStack.pop();
                        String ch1 = operandStack.pop();
                        String ch2 = operandStack.pop();
                        String temp = op + ch2 + ch1;

                        operandStack.push(temp);
                    }
                    operatorStack.push(ch);
                }
            } else {
                operandStack.push(Character.toString(ch));
            }
        }
        while (!operatorStack.isEmpty()) {
            char op = operatorStack.pop();
            String ch1 = operandStack.pop();
            String ch2 = operandStack.pop();
            String temp = op + ch1 + ch2;

            operandStack.push(temp);
        }
        return operandStack.pop();
    }

    private static class Node {
        private final String data;
        private final Node left;
        private final Node right;

        public Node(String data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(String data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            String rv = "Node{ ";
            rv += "data=" + data;
            if (left != null) {
                rv += ", left=" + left.data;
            } else {
                rv += " null";
            }
            if (right != null) {
                rv += ", right=" + right.data;
            } else {
                rv += " null";
            }
            rv += " }";

            return rv;
        }
    }

    public static Node infixToExpressionTree(String infix) {
        Stack<Node> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            if (isOperator(ch)) {
                if (ch == '(') {
                    operators.push(ch);
                } else if (ch == ')') {
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        Node ch1 = operands.pop();
                        Node ch2 = operands.pop();
                        Node op = new Node(operators.pop().toString(), ch2, ch1);

                        operands.push(op);
                    }
                } else {
                    while (!operators.isEmpty() && getPriority(ch) <= getPriority(operators.peek())) {
                        Node ch1 = operands.pop();
                        Node ch2 = operands.pop();
                        Node op = new Node(operators.pop().toString(), ch2, ch1);

                        operands.push(op);
                    }
                    operators.push(ch);
                }
            } else {
                operands.push(new Node(Character.toString(ch)));
            }
        }
        while (!operators.isEmpty()) {
            Node ch1 = operands.pop();
            Node ch2 = operands.pop();
            Node op = new Node(operators.pop().toString(), ch2, ch1);

            operands.push(op);
        }
        return operands.peek();
    }

    public static void levelOrder(Node root) {
        if (root == null) return;

        LinkedList<Node> list = new LinkedList<>();
        list.addFirst(root);

        while (!list.isEmpty()) {
            Node curr = list.removeFirst();

            if (curr.left != null) list.addLast(curr.left);
            if (curr.right != null) list.addLast(curr.right);
            System.out.print(curr.data + " =>");
        }
    }
}
