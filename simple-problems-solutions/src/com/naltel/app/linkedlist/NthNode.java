package com.naltel.app.linkedlist;

import java.awt.List;
import java.util.LinkedList;

public class NthNode {
	
	public static void main(String args[]) {
		
		int[] array = new int[]{1,2,3,4,5,6};
		
		MyLinkedList ll = new MyLinkedList();
		for(int i = 0; i <array.length; i++) {
			ll.insertAtEnd(new Node(array[i]));
		}
		//ll.insert(new Node(1));
		//ll.insert(new Node(2));
		//ll.insert(new Node(3));
		//ll.insert(new Node(1));
		//ll.insert(new Node(5));
		/*
		System.out.println(ll.toString());
		Node value = nthNode(ll.head, 10);
		if(value != null)
			System.out.println(value.data);
		else
			System.out.println(-1);
		System.out.println(ll.toString());
		*/
		/*
		System.out.println(ll.toString());
		Node value = nthNode(ll.head, 5);
		if(value != null)
			System.out.println(value.data);
		else
			System.out.println(-1);
		ll.deleteNode(value);
		System.out.println(ll.toString());
		*/
		//ll.insert(6);
		//ll.insert(7);
		//ll.insert(8);
		//System.out.println(ll.toString());
		//System.out.println("Middle Node :"+ findMiddleNode(ll.head).data);
		/*
		System.out.println(ll.toString());
		Node value = findNthLastNode(ll.head, 5);
		if(value != null)
			System.out.println(value.data);
		else
			System.out.println(-1);
		 */
		//System.out.println(ll.toString());
		//ll.reverseIterative();
		//System.out.println(ll.toString());
		//System.out.println(ll.toString());
		//ll.reverseRecursion(ll.head);
		//System.out.println(ll.toString());
		//System.out.println(ll.isPalindrome());
		//System.out.println(ll.toString());
		
		//System.out.println(ll.toString());
		//ll.removeDuplicates();
		//System.out.println(ll.toString());
		
		//System.out.println(ll.toString());
		//ll.removeDuplicatesUnsorted();
		//System.out.println(ll.toString());
		
		//System.out.println(ll.toString());
		//ll.reverseTwoConsecutive();
		//System.out.println(ll.toString());
		
		//System.out.println(ll.toString());
		//ll.deleteAlternate();
		//System.out.println(ll.toString());
		
		System.out.println(ll.toString());
		ll.splitAlternate();
		System.out.println(ll.toString());
		System.out.println(ll.toTailString());
		ll.MergeHeadtail();
		System.out.println(ll.toString());
	}
	
	public static Node nthNode(Node thead, int k) {
		if(k <= 0) return null;
		if(thead == null) return null;
		if(k == 1) return thead;
		return nthNode(thead.next, k-1);
	}
	
	public static Node deleteNode(Node thead) {
		if(thead == null) return null;
		if(thead.next == null) {
			thead.data = null;
			return null;
		}
		Node temp = thead.next;
		thead.data = temp.data;
		thead.next = temp.next;
		return thead;
	}
	
	public static Node findMiddleNode(Node thead) {
		if(thead == null) return null;
		Node temp1 = thead;
		Node temp2 = thead;
		while(temp1 != null && temp2 !=null && temp2.next != null) {
			temp1 = temp1.next;
			temp2=temp2.next.next;
		}
		return temp1;
	}
	
	public static Node findNthLastNode(Node thead, int k) {
		if(thead == null) return null;
		if(k <= 0) return null;
		Node temp1 = thead;
		Node temp2 = thead;
		for(int i = 0; i < k ; i++) {
			if(temp2 == null) return null;
			else temp2 = temp2.next;
		}
		while(temp2 != null) {
			temp1 = temp1.next;
			temp2=temp2.next;
		}
		return temp1;
	}
	
	
}
