
public class LinkedListStack {
	AStarNode top;

	public LinkedListStack() {
		AStarNode dummy = new AStarNode();
		dummy.next = null;
		top = dummy;
	}

	public void push(AStarNode newNode) {
		newNode.next = top.next;
		top.next = newNode;
	}
	public AStarNode peek() {
		return top.next;
	}

	public AStarNode pop() {
		if (top.next == null) {
			return null;
		} else {
			AStarNode temp = top.next;
			top.next = top.next.next;
			return temp;
		}
	}

	public boolean isEmpty() {
		if (top.next == null) {
			return true;
		} else
			return false;
	}

}
