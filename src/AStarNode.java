import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AStarNode {
	int[] configuration;
	int gStar;
	int hStar;
	int fStar;
	AStarNode parent;
	AStarNode next;

	public AStarNode() {

	}

	public AStarNode(int _gStar, int _hStar, int _fStar) {
		gStar = _gStar;
		hStar = _hStar;
		fStar = _fStar;
		parent = null;
		next = null;
	}
	public String toString() {
		String result = "";
		for (int i = 0; i < configuration.length; i++) {
			result = result +  configuration[i] + " ";
		}
		return result;
		
	}
	public void printNode(File outFile) {
		try {
			FileWriter fw1 = new FileWriter(outFile, true);
			fw1.write("< " + fStar + ":: ");
			if(configuration != null) {
				for (int i = 0; i < configuration.length; i++) {
					fw1.write(configuration[i] + " ");
				}
			}
			else {
				fw1.write("null");
			}
			fw1.write(" :: ");
			if(parent != null && parent.configuration != null) {
				for (int j = 0; j < parent.configuration.length; j++) {
					fw1.write(parent.configuration[j] + " ");
				}
			}
			else {
				fw1.write("null");
			}
			fw1.write("> \n");
			fw1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int[] getConfiguration() {
		return configuration;
	}

	public int getgStar() {
		return gStar;
	}

	public int gethStar() {
		return hStar;
	}

	public int getfStar() {
		return fStar;
	}

	public AStarNode getParent() {
		return parent;
	}

	public void setConfiguration(int[] configuration) {
		this.configuration = configuration;
	}

	public void setgStar(int gStar) {
		this.gStar = gStar;
	}

	public void sethStar(int hStar) {
		this.hStar = hStar;
	}

	public void setfStar(int fStar) {
		this.fStar = fStar;
	}

	public void setParent(AStarNode parent) {
		this.parent = parent;
	}

	public AStarNode getNext() {
		return next;
	}

	public void setNext(AStarNode next) {
		this.next = next;
	}
}
