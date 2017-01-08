package com.naltel.app.linkedlist;

public class Node {
	
	public Object data;
	public Node next;
	public Node prev;
	public Node left;
	public Node right;
	public Node(Object data){
		this.data=data;
	}
	public Node(int i){
		this.data=new Integer(i);
	}
	public Node(char c){
		this.data=new Character(c);
	}
	public Node getLeft() {
		return left;
	}
	public Node getRight() {
		return right;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public String toString(){
		return data.toString();
	}
}
