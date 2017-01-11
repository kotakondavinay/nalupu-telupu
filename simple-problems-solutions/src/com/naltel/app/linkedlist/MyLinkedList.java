package com.naltel.app.linkedlist;

import java.util.LinkedList;

public class MyLinkedList {
	public Node head;
	public Node tail;
	private int size;
	
	public void insert(Object data){
		Node n = null;
		if(!(data instanceof Node)) {
			n=new Node(data);
		} else {
			n = (Node)data;
		}
		n.next=head;
		head=n;
		if(tail==null)
			tail=n;
		size++;
		
	}
	public void insertAtEnd(Object data){
		insertAfter(tail,data);
		
	}
	public void insertAfter(Node after, Object data) {
		// TODO Auto-generated method stub
		Node n = null;
		if(!(data instanceof Node)) {
			n=new Node(data);
		} else {
			n = (Node)data;
		}
		if(size==0){
			head=tail=n;
		}
		else{
			n.next=after.next;
			after.next=n;
			if(after==tail)
				tail=n;	
		}
		size++;
	}
	public Object remove(){
		Object data=null;
		if(head==null)
			return data;
		else{
			if(size==1){
				data=head.data;
				head=tail=null;
				size--;
				return data;
			}else{
				data=head.data;
				head=head.next;
				size--;
				return data;
			}
		}
		
	}
	public Object removeAfter(Node n)
	{
		Object data=null;
		if(n==null || n.next==null) return data;
		data=n.next.data;
		n.next=n.next.next;
		size--;
		return data;
	}
	public Node search(Object data) {
		Node loop=head;
		for(int i=0;i<size;i++)
		{
			if (loop.data.equals(data)) return loop;
			loop=loop.next;
		}
		
		return loop;
	}
	public String toString(){
		if(size==0) return null;
		String s="";
		Node temp=this.head;
		while(temp != null){
			s=s+temp.data;
			s=s+"--->";
			temp=temp.next;
		}
		return s;
	}
	
	public void reverseList(){
		if(size<=1) return;
		Node prev=null;
		Node current=this.head;
		Node next;
		while(current!= null)
		{
			next=current.next;
			current.next=prev;
			if(prev==null) tail=current;
			prev=current;
			current=next;
		}
		this.head=prev;
	}
	public void printReverseRecursive(Node head) {
		if(head== null ) return;
		printReverseRecursive(head.next);
		System.out.print("   "+head.data);
	}
	
	public Node nthNode(Node thead, int k) {
		if(k <= 0) return null;
		if(thead == null) return null;
		if(k == 1) return thead;
		return nthNode(thead.next, k-1);
	}
	
	public Node findMiddleNode(Node thead) {
		if(thead == null) return null;
		Node temp1 = thead;
		Node temp2 = thead;
		while(temp1 != null && temp2 !=null && temp2.next != null && temp2.next.next != null) {
			temp1 = temp1.next;
			temp2=temp2.next.next;
		}
		return temp1;
	}
	
	public Node deleteNode(Node thead) {
		if(thead == null) return null;
		if(thead.next == null) {
			thead.data = null;
			this.size = this.size - 1;
			return null;
		}
		Node temp = thead.next;
		thead.data = temp.data;
		thead.next = temp.next;
		this.size = this.size - 1;
		return thead;
	}
	
	public void reverseIterative() {
		if(this.head == null) return;
		Node reversed = null;
		Node remaining = head;
		Node temp;
		while(remaining != null) {
			temp = remaining.next;
			remaining.next = reversed;
			reversed = remaining;
			remaining = temp;
		}
		this.head = reversed;
	}
	
	public Node reverseRecursion(Node head) {
		if(head == null) return null;
		if(head.next == null) {
			return head; 
		};
		Node reversed = reverseRecursion(head.next);
		head.next.next = head;
		head.next = null;
		this.head =  reversed;
		return this.head;
	}
	
	public void removeDuplicates() {
		if(this.head == null) return;
		Node itr = this.head;
		while(itr.next != null) {
			if(Integer.parseInt(itr.next.data.toString()) == Integer.parseInt(itr.data.toString())) {
				itr.next = itr.next.next;
			}
			else {
				itr = itr.next;
			}
		}
	}
	
	public void removeDuplicatesUnsorted() {
		if(this.head == null) return;
		Node itr = this.head;
		while(itr != null) {
			Node itr2 = itr;
			while(itr2.next != null) {
				if(Integer.parseInt(itr.data.toString()) == Integer.parseInt(itr2.next.data.toString())) {
					itr2.next=itr2.next.next;
				}
				else {
					itr2=itr2.next;
				}
			}
			itr=itr.next;
		}
	}
	
	public boolean isPalindrome() {
		if(this.head == null) return true;
		Node temp1 = this.head;
		Node temp2 = this.head;
		int counter = 0;
		while(temp1 != null) {
			counter++;
			temp1 = temp1.next;
		}
		int mid=counter/2;
		if(counter%2==1){
			mid=counter+1/2;
		}
		for(int i=1;i < mid;i++) {
			temp2 = temp2.next;
		}
		Node head2=temp2.next;
		temp1=head2;
		temp2.next=null;
		reverseIterative();
		Node head1=this.head;
		if(counter%2 == 1) {
			head1=head1.next;
		}
		while(head1 != null) {
			if(Integer.parseInt(head1.data.toString()) != Integer.parseInt(head2.data.toString())) {
				head1=this.head;
				head2=temp1;
				reverseIterative();
				head1.next=head2;
				return false;
			}
			head1 = head1.next;
			head2 = head2.next;
		}
		head1=this.head;
		head2=temp1;
		reverseIterative();
		head1.next=head2;
		return true;
	}

}
