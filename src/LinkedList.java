import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class LinkedList {
	AStarNode head;

	public LinkedList() {
		AStarNode dummy = new AStarNode();
		dummy.next = null;
		head = dummy;
	}

	public void listInsert(AStarNode newNode) {
		AStarNode temp = head;
		while (temp.next != null && temp.next.fStar < newNode.fStar) {
			temp = temp.next;
		}
		newNode.next = temp.next;
		temp.next = newNode;
	}

	public AStarNode peek() {
		return head.next;
	}
	public AStarNode pop() {
		AStarNode temp = head.next;
		head.next = head.next.next;
		return temp;
	}
	public void deleteNode(AStarNode currentNode) {
		
		AStarNode temp = head;
		AStarNode result = null;
		
		while(temp.next != null) {
			
			boolean listEqual = true;
			
			for (int i = 0; i < currentNode.configuration.length; i++) {
				if((currentNode.configuration[i] != temp.next.configuration[i])) {
					listEqual = false;
					break;
				}
				
			}
			
			if(listEqual) {
				System.out.println(temp.next);
				temp.next = temp.next.next;
				break;
			}
			temp = temp.next;
		}
	}

	public void push(AStarNode newNode) {
		newNode.next = head.next;
		head.next = newNode;
	}

	public void printList(File outFile) {
		AStarNode temp = head;
		while (temp.next != null) {
			temp.printNode(outFile);
			temp = temp.next;
		}
	}

	public boolean isEmpty() {
		if (head.next == null) {
			return true;
		} else
			return false;
	}

	public boolean contains(int[] configuration) {

		AStarNode temp = head;
		while (temp.next != null) {
			if (Arrays.equals(configuration, temp.next.configuration)) {
				return true;
			}
			temp = temp.next;
		}
		return false;
	}
	public AStarNode lookFor(int[] configuration) {
		AStarNode temp = head;
		
		while (temp.next != null) {
			
			boolean listEqual = true;
			
			for (int i = 0; i < configuration.length; i++) {
				
				if((configuration[i] != temp.next.configuration[i])) {
					listEqual = false;
					break;
				}
			}
			if(listEqual) {
				return temp.next;
			}
			temp = temp.next;
		}
		return null;
	}
	public int length() {
		int result = 0;
		AStarNode temp = head;
		while (temp.next != null) {
			result ++;
			temp = temp.next;
		}
		return result;
	}
}
