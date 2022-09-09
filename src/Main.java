import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("program start\n");
		String initialFile = args[0];
		String goalFile = args[1];
		String outputFile1 = args[2];
		String outputFile2 = args[3];

		File inFile1 = new File(initialFile);
		File inFile2 = new File(goalFile);
		File outFile1 = new File(outputFile1);
		File outFile2 = new File(outputFile2);

		AStar myAStar = new AStar(); // my Astar
		AStarNode startNode = new AStarNode(); // initial startNode
		AStarNode goalNode = new AStarNode(); // goal node

		int[] initialConfiguration = new int[9]; // initial configuaration
		int[] goalConfiguration = new int[9]; // goal configuaration

		LinkedList open = new LinkedList(); // open linkedlist
		LinkedList close = new LinkedList(); // close linkedlist

		Scanner sc = new Scanner(inFile1);
		int index = 0;
		while (sc.hasNext()) {
			initialConfiguration[index] = sc.nextInt();
			index++;
		}

		sc = new Scanner(inFile2);
		index = 0;
		while (sc.hasNext()) {
			goalConfiguration[index] = sc.nextInt();
			index++;
		}
		startNode.setConfiguration(initialConfiguration);
		goalNode.setConfiguration(goalConfiguration);
		myAStar.setStartNode(startNode);
		myAStar.setGoalNode(goalNode);
		myAStar.setOpen(open);
		myAStar.setClose(close);
		startNode.setgStar(0);
		startNode.sethStar(myAStar.computeHStar(startNode));
		startNode.setfStar(startNode.getgStar() + startNode.gethStar());
		open.listInsert(startNode);
		AStarNode currentNode = open.peek();
		int loopCounter = 0;
		while (true) {

			if (loopCounter >= 30) {
				myAStar.printSolution(currentNode, outFile2);
				break;
			}
			if (open.isEmpty()) {
				myAStar.printSolution(currentNode, outFile2);
				break;
			}
			currentNode = open.pop();
			if (myAStar.isGoalNode(currentNode)) {
				myAStar.printSolution(currentNode, outFile2);
				break;
			}

			LinkedListStack childList = myAStar.constructChildList(currentNode); // how to construct child list

			while (!childList.isEmpty()) {
//				System.out.println("child list while loop****************");
				AStarNode child = childList.pop();
				child.setParent(currentNode);

				child.setgStar(myAStar.computeGStar(child));
				child.sethStar(myAStar.computeHStar(child));
				child.setfStar(child.getgStar() + child.gethStar());

				if (!open.contains(child.getConfiguration()) && !close.contains(child.getConfiguration())) {

//					System.out.println("reach here in both not contains");
					open.listInsert(child);
					child.setParent(currentNode);

				} else if (open.contains(child.getConfiguration())
						&& child.getfStar() < open.lookFor(child.getConfiguration()).getfStar()) {
					AStarNode temp = open.lookFor(child.getConfiguration());
					if (temp != null) {
						open.deleteNode(temp);
						open.listInsert(child);
						child.setParent(currentNode);
					}

//					System.out.println("reach here in open contains");

				} else if (close.contains(child.getConfiguration())
						&& child.getfStar() < close.lookFor(child.getConfiguration()).getfStar()) {
//					System.out.println("reach here in close contains");
					AStarNode temp = close.lookFor(child.getConfiguration());
					if (temp != null) {
						close.deleteNode(temp);
						open.listInsert(child);
						child.setParent(currentNode);
					}

				}

			}
			// delete the old node remove the node of larger f value
			// keep the child of lower F value
			close.push(currentNode);
			myAStar.setOpen(open);
			myAStar.setClose(close);

			FileWriter fw = new FileWriter(outFile1, true);
			fw.write("This is Openlist: \n");
			myAStar.getOpen().printList(outFile1);
			fw.write("This is Closelist: \n");
			myAStar.getClose().printList(outFile1);
			fw.close();
			loopCounter++;
		}
		if (open.isEmpty() && !myAStar.match(currentNode.configuration, myAStar.goalNode.configuration)) {
			FileWriter fw = new FileWriter(outFile1, true);
			fw.write("no solution can be found in the search");
			fw.close();
		}
		System.out.println("program end\n");
	}
}
