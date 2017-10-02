package com.naltel.app.tree;

import java.util.LinkedList;
import java.util.Queue;

public class DiagonalPrint {
    // Tree node
    private static class Node{
        private int data;
        Node left;
        Node right;

        //constructor
        Node(int data)
        {
            this.data=data;
            left = null;
            right =null;
        }
    }

    static void addAllrights(Queue q, Node root) {
        while(root != null) {
            q.add(root);
            root = root.right;
        }
    }

    static void diagPrint(Node root) {
        Queue<Node> q = new LinkedList<>();
        addAllrights(q,root);
        q.add(null);
        while(!q.isEmpty()) {
            Node currentValue = q.poll();
            if(currentValue == null) {
                if(!q.isEmpty()) {
                    q.add(null);
                }
                System.out.println();
            } else {
                System.out.print(currentValue.data + " ");
                addAllrights(q, currentValue.left);
            }
        }
    }

    // Driver program
    public static void main(String[] args) {

        Node root = new Node(8);
        root.left = new Node(3);
        root.right = new Node(10);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.right.right = new Node(14);
        root.right.right.left = new Node(13);
        root.left.right.left = new Node(4);
        root.left.right.right = new Node(7);

        diagPrint(root);
    }
}
