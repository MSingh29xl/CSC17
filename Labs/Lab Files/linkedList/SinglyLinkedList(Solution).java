
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
	
    private void reverseList() {
    	if(head == null) return;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        head = prev;
    }
    
    private void reverseBetween(int m, int n) {
        if(head == null) return;
        ListNode dummy = new ListNode(-1); 
        dummy.next = head;
        ListNode pre = dummy; 
        for(int i = 0; i<m-1; i++) pre = pre.next;
        
        ListNode start = pre.next; 
        ListNode then = start.next; 

        
        for(int i=0; i<n-m; i++)
        {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        head = dummy.next;

    }
    
    /* another solution
     * 
     private void reverseBetween(int m, int n) {
		ListNode dummy = new ListNode(0, head);
		ListNode curr = dummy;
		for(int i = 0; i < m - 1; i++) {
			curr = curr.next;
		}
		ListNode before = curr;
		for(int i = 0; i < n - m + 2; i++) {
			curr = curr.next;
		}
		ListNode after = curr;
		
		curr = before.next;
		ListNode prev = after;
		ListNode next;
		while(curr != after) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		before.next = prev;
		head = dummy.next;
    }
    
    public void deleteDuplicates1() {
        ListNode list = head;
         
         while(list != null) {
        	 if (list.next == null) {
        		 break;
        	 }
        	 if (list.val == list.next.val) {
        		 list.next = list.next.next;
        	 } else {
        		 list = list.next;
        	 }
         }
    }
    * */
    
    public  void deleteDuplicates2() {
        if(head == null) return;
        ListNode dummy =new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur != null){
            while(cur.next!=null && cur.val == cur.next.val){
                cur = cur.next;
            }
            if(pre.next == cur){
                pre = pre.next;
            }
            else{
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        head = dummy.next;
    }
    
    public void partition(int x) {
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
        ListNode curr1 = dummy1, curr2 = dummy2, curr = head;      //current tails of the two queues;
        while (curr!=null){
            if (curr.val<x) {
                curr1.next = curr;
                curr1 = curr;
            }else {
                curr2.next = curr;
                curr2 = curr;
            }
            curr = curr.next;
        }
        curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
        curr1.next = dummy2.next;
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


    	SinglyLinkedList list4 = new SinglyLinkedList();
    	for(int i = 10; i > 0; i--) {
    		list4.add(i);
    	}
    	System.out.println(list4);
    	list4.reverseList();
    	System.out.println(list4);
    	list4.reverseList();
    	System.out.println(list4);
    	list4.reverseBetween(3,7);
    	System.out.println(list4);
    }
}
