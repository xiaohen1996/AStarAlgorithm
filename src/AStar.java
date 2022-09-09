import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class AStar {
	AStarNode startNode;
	AStarNode goalNode;
	LinkedList open;
	LinkedList close;
	LinkedListStack childList;
	int[][] costMatrix = {
			/*
			 * start node 
			 * 1 5 8 
			 * 3 0 7 
			 * 2 6 4
			 */
			/*
			 * goal node 
			 * 1 4 7 
			 * 2 5 8 
			 * 3 6 0
			 */
			{ 0, 1, 2, 1, 2, 3, 2, 3, 4 }, 
			{ 1, 0, 1, 2, 1, 2, 3, 2, 3 }, 
			{ 2, 1, 0, 3, 2, 1, 4, 3, 2 },
			{ 1, 2, 3, 0, 1, 2, 1, 2, 3 }, 
			{ 2, 1, 2, 1, 0, 1, 2, 1, 2 }, 
			{ 3, 2, 1, 2, 1, 0, 3, 2, 1 },
			{ 2, 3, 4, 1, 2, 3, 0, 1, 2 }, 
			{ 3, 2, 3, 2, 1, 2, 1, 0, 1 }, 
			{ 4, 3, 2, 3, 2, 1, 2, 1, 0 } };
	public AStar() {

	}

	public int computeGStar(AStarNode node) {
		if(node.parent == null) {
			return 0;
		}
		return node.parent.gStar + 1;
	}

	public int computeHStar(AStarNode currentNode) {
		int h2 = 0;
		int indexOfCorrectNumber = 0;


		for (int i = 0; i < currentNode.configuration.length; i++) {
			
			if (currentNode.configuration[i] != goalNode.configuration[i]) {
				
				for (int j = 0; j < goalNode.configuration.length; j++) {
					
					if(goalNode.configuration[j] == currentNode.configuration[i]) {
						indexOfCorrectNumber = j;
						h2 += costMatrix[i][indexOfCorrectNumber];
						break;
					}
				}
			}
			else continue;
		}
		return h2;
	}

	public boolean match(int[] configuration1, int[] configuration2) {
		if (Arrays.equals(configuration1, configuration2)) {
			return true;
		}

		return false;
	}

	public boolean isGoalNode(AStarNode node) {
		if (match(node.configuration, goalNode.configuration)) {
			return true;
		} else
			return false;
	}

	public boolean checkAncestor(AStarNode currentNode) {

		AStarNode temp = currentNode;
		while (temp.parent != null) {
			temp = temp.parent;
			if (match(currentNode.configuration, temp.configuration)) {
				return true;
			}
		}
		return false;
	}

	public LinkedListStack constructChildList(AStarNode currentNode) {
		int[][] costMatrix = {
				/*
				 * start node 
				 * 1 5 8 
				 * 3 0 7 
				 * 2 6 4
				 */
				/*
				 * goal node 
				 * 1 4 7 
				 * 2 5 8 
				 * 3 6 0
				 */
				{ 0, 1, 2, 1, 2, 3, 2, 3, 4 }, 
				{ 1, 0, 1, 2, 1, 2, 3, 2, 3 }, 
				{ 2, 1, 0, 3, 2, 1, 4, 3, 2 },
				{ 1, 2, 3, 0, 1, 2, 1, 2, 3 }, 
				{ 2, 1, 2, 1, 0, 1, 2, 1, 2 }, 
				{ 3, 2, 1, 2, 1, 0, 3, 2, 1 },
				{ 2, 3, 4, 1, 2, 3, 0, 1, 2 }, 
				{ 3, 2, 3, 2, 1, 2, 1, 0, 1 }, 
				{ 4, 3, 2, 3, 2, 1, 2, 1, 0 } };
		int indexOfZero = 0;
		LinkedListStack result = new LinkedListStack();
		for (int i = 0; i < currentNode.configuration.length; i++) {
			if (currentNode.configuration[i] == 0) {
				indexOfZero = i;
				break;
			}
		}
			for (int j = 0; j < costMatrix[0].length; j++) {
				
				AStarNode newChildNode = new AStarNode();
				newChildNode.setParent(currentNode);
				int[] childConfiguration = new int[9];
				for (int i = 0; i < childConfiguration.length; i++) {
					childConfiguration[i] = currentNode.configuration[i];
				}
				if(costMatrix[indexOfZero][j] == 1) {
					int temp = currentNode.configuration[j];
					childConfiguration[j] = 0;
					childConfiguration[indexOfZero] = temp;
				}
				else continue;
				newChildNode.setConfiguration(childConfiguration);
				if(!checkAncestor(newChildNode)) {
					result.push(newChildNode);
				}
				
			}	
		return result;
	}

	public void printSolution(AStarNode node, File outFile) {
		try {
			FileWriter fw = new FileWriter(outFile, true);
			fw.write("******************final solution*********************\n");
			for (int i = 2; i < node.configuration.length; i += 3) {
				for (int j = i - 2; j <= i; j++) {
					fw.write(node.configuration[j] + " ");
				}
				fw.write(" \n");
			}
			fw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public AStarNode getStartNode() {
		return startNode;
	}

	public AStarNode getGoalNode() {
		return goalNode;
	}

	public LinkedList getOpen() {
		return open;
	}

	public LinkedList getClose() {
		return close;
	}

	public LinkedListStack getChildList() {
		return childList;
	}

	public int[][] getCostMatrix() {
		return costMatrix;
	}

	public void setStartNode(AStarNode startNode) {
		this.startNode = startNode;
	}

	public void setGoalNode(AStarNode goalNode) {
		this.goalNode = goalNode;
	}

	public void setOpen(LinkedList open) {
		this.open = open;
	}

	public void setClose(LinkedList close) {
		this.close = close;
	}

	public void setChildList(LinkedListStack childList) {
		this.childList = childList;
	}

	public void setCostMatrix(int[][] costMatrix) {
		this.costMatrix = costMatrix;
	}

}
