package com.example.tabular_method;


public class Single_Linkedlist {



	private Node head = null;
	
	
	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public void add(int index, Object element){
		
		Node n = new Node();
		Node i = head;
		n.value = element;
		int link_size = size();
		if(index < 0 || index > link_size) {throw  new NullPointerException();}
		if(index == 0 && head == null){
			head = n;
		} else if (index == 0){
			n.next = head;
			head = n;
		} else if (index > 0 && index <= link_size) {
		
			for(int j = 0; j < index - 1; j++) i = i.next;
			n.next = i.next;
			i.next = n;
		}
	}
	

	public void add(Object element){
		
		Node n = new Node();
		Node i = new Node();
		i = head;
		n.value = element;
		
		if (head == null){
			head = n;
			
		} else {
			
			while( i.next != null){
				i = i.next;
			}
			i.next = n;
		}
		 n.next = null;
	}

	public Object get(int index) {
		
		int link_size = size();
		if(index < 0 || index >= link_size) return -1;
		Node i = head;
		for(int j = 0; j < index ; j++)
			i = i.next;
		
		return i.value;
	}

	public void set(int index, Object element) {
		
		Node n = new Node();
		n.value = element;
		Node i = head;
		int link_size = size();
		if(index < 0 || index > link_size) {throw  new NullPointerException();}
		if(index >= 0 && (index < link_size || index == 0)){
			if(head == null && index == 0){
				head = n;
			} else if (index == 0){
				n.next = i.next;
				head = n;
			} else {
				for(int j = 0; j < index - 1; j++){
					i = i.next;
				} 
					n.next = i.next.next;
					i.next = n;
			}
		}
	}

	public void clear() {
		head = null;
		
	}

	public boolean isEmpty() {
		
		if(head == null) return true;
		return false;
	}

	public void remove(int index) {
			
			Node i = head;
			int link_size = size();
			if(index < 0 || index >= link_size) {throw  new NullPointerException();}
			if(i.next == null && index == 0) head = null;
			else if (index == 0) head = head.next;
			else {
				
				for(int j = 0; j < index - 1; j++) i = i.next;
				Node e = i.next;
				i.next = e.next;
				e.next = null;
			}
	}

	public int size() {
		
		if (head == null) return 0;
		Node i = head;
		int link_size = 1;
		while(i.next != null){
			i = i.next;
			link_size++;
		}
		return link_size;
	}

	public boolean contains(Object o) {
		
		Node i = head;
		while(i.next != null){
			if (i.value.equals(o) ) return true;
			i = i.next;
		}
		if(i.value.equals(o)) return true;
		return false;
	}
	
	public Single_Linkedlist  copy(Single_Linkedlist cop){
		
		Node i = head;
		
		while(i != null){
			cop.add(i.value);
			i = i.next;
		}
		cop.head = head;
		return cop;
		
	}
		
	public void print(){
		Node i = new Node();
		i = head;
		while(i != null){
			System.out.print(i.value + " , ");
			i = i.next;
		}
		System.out.println();
	}
	
	public void copy_f1_to_f2(Single_Linkedlist l1, Single_Linkedlist l2){
		
		Node i = l1.head;
		while(i != null){
			l2.add(i.value);
			i = i.next;
		}
	}
	
}
