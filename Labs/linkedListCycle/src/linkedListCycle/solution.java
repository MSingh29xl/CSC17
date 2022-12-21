package linkedListCycle;
import java.util.*;

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}
}
public class solution {
	
	public static boolean hasCycle(ListNode head) {
		if(head == null) {
			return false;
		}
		HashSet<ListNode> cycle = new HashSet<>();
		while(head != null) {
			cycle.add(head);
			head = head.next;
			if(cycle.contains(head)) {
				return true;
			}
		}
		return false;
	}
	public static void main(String args[]) {
		ListNode head = null;
		ListNode tmp = null;
		System.out.println(hasCycle(head));
		head = new ListNode(3);
		head.next = head;
		System.out.println(hasCycle(head));
		tmp = head.next = new ListNode(1);
		head.next.next = new ListNode(0);
		head.next.next.next = new ListNode(7);
		System.out.println(hasCycle(head));
		head.next.next.next.next = tmp;
		System.out.println(hasCycle(head));
	}
}