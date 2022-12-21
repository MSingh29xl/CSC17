public class SinglyLinkedList {
	
	// reference that points to the list head
	public ListNode head;
	
	// nested class for singly-list node
	private static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { 
			val = x;
		}
		ListNode(int x, ListNode nextIn) { 
			this.val = x;
			this.next = nextIn;
		}
	}
	
	public SinglyLinkedList() {
		head = null;
	}
	
	// add node to the head of list
	private void add(int val) {
		ListNode e = new ListNode(val, head);
		head = e;
		
	}
	
	public String toString() {
   	 	String mylist = "";
   	 	ListNode e = head;
   	 	while(e != null) {
   	 		mylist = mylist + e.val + " ";
   	 		e = e.next;
   	 }
   	 return mylist;
	}
	
	public void partition(int x) {
		// place your code here
    }
    
    public void deleteDuplicates1() {
		// place your code here
    }
    
    public  void deleteDuplicates2() {
		// place your code here
    }
    

    
    public static void main(String args[]) {
    	SinglyLinkedList list1 = new SinglyLinkedList();
    	SinglyLinkedList list2 = new SinglyLinkedList();
    	SinglyLinkedList list3 = new SinglyLinkedList();
    	int[] array1 = {1,4,3,2,5,2};
    	int[] array2 = {1,1,2,3,3,3};
    	int[] array3 = {1,1,2,2,2,3};
    	for(int i = 5; i > -1; i--) {
    		list1.add(array1[i]);
    		list2.add(array2[i]);
    		list3.add(array3[i]);
    	}
    	System.out.println(list1);
    	list1.partition(3);
    	System.out.println(list1);
    	
    	System.out.println(list2);
    	list2.deleteDuplicates1();
    	System.out.println(list2);
    	
    	System.out.println(list3);
    	list3.deleteDuplicates2();
    	System.out.println(list3);
    }
}
