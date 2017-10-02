package com.naltel.app.tree;

// Recursive Java program to connect nodes at same level
// using constant extra space
// Thanks to geeksforgeeks

public class  ConnectSameLevel {

    public static void main(String args[])
    {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(10);
        tree.root.left = new Node(8);
        tree.root.right = new Node(2);
        tree.root.left.left = new Node(3);
        tree.root.right.right = new Node(90);

        // Populates nextRight pointer in all nodes
        tree.connectRecur(tree.root);

        // Let us check the values of nextRight pointers
        int a = tree.root.nextRight != null ?
                tree.root.nextRight.data : -1;
        int b = tree.root.left.nextRight != null ?
                tree.root.left.nextRight.data : -1;
        int c = tree.root.right.nextRight != null ?
                tree.root.right.nextRight.data : -1;
        int d = tree.root.left.left.nextRight != null ?
                tree.root.left.left.nextRight.data : -1;
        int e = tree.root.right.right.nextRight != null ?
                tree.root.right.right.nextRight.data : -1;

        // Now lets print the values
        System.out.println("Following are populated nextRight pointers in "
                + " the tree(-1 is printed if there is no nextRight)");
        System.out.println("nextRight of " + tree.root.data + " is " + a);
        System.out.println("nextRight of " + tree.root.left.data + " is " + b);
        System.out.println("nextRight of " + tree.root.right.data + " is " + c);
        System.out.println("nextRight of " + tree.root.left.left.data +
                " is " + d);
        System.out.println("nextRight of " + tree.root.right.right.data +
                " is " + e);
    }
}
// A binary tree node
class Node
{
    int data;
    Node left, right, nextRight;

    Node(int item)
    {
        data = item;
        left = right = nextRight = null;
    }
}

class BinaryTree
{
    Node root;

    /* Set next right of all descendents of p. This function makes sure that
    nextRight of nodes ar level i is set before level i+1 nodes. */
    void connectRecur(Node root)
    {
        // Base case
        if (root == null)
            return;
        Node p = root;
        p.nextRight = null;
        while(p != null) {
            Node q = p;
            while(q != null) {
                if(q.left != null) {
                    if(q.right != null)
                        q.left.nextRight = q.right;
                    else
                        q.left.nextRight = getNextRight(q);
                }
                if(q.right != null)
                    q.right.nextRight = getNextRight(q);
                q = q.nextRight;
            }
            if(p.left != null)
                p = p.left;
            else if(p.right != null)
                p = p.right;
            else
                p = p.right;
        }
    }

    Node getNextRight(Node p)
    {
        Node temp = p.nextRight;
        while (temp != null)
        {
            if (temp.left != null)
                return temp.left;
            if (temp.right != null)
                return temp.right;
            temp = temp.nextRight;
        }
        return null;
    }
}

// This code has been contributed by Mayank Jaiswal
