package Michie.Codes.AVLTreeGraphQLServer.Trees;

/* Class containing left and right child of current
node and key value*/
public class BinaryNode {
	public String key;
    public BinaryNode left;
    public BinaryNode right;

	public BinaryNode(String item) {
		this.key = item;
		left = right = null;
	}
}
