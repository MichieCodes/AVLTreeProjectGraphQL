package Michie.Codes.AVLTreeGraphQLServer.Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

// A Java program to introduce Binary Tree
public class BinaryTree {
	private Random random = new Random();
	// Root of Binary Tree
	public BinaryNode root;

	// Constructors
	public BinaryTree(String key) {
		root = new BinaryNode(key);
	}
	public BinaryTree() {
		root = null;
	}

	public void generateRandomBinaryTree(List<String> keys) {
		if(keys == null || keys.isEmpty()) return;
		
		int leftNodeCount = random.nextInt(keys.size());
		int rightNodeCount = keys.size() - leftNodeCount - 1;
		
		root = new BinaryNode(keys.get(0));
		keys.remove(0);

		root.left = generateSubTree(leftNodeCount, keys);
		root.right = generateSubTree(rightNodeCount, keys);
	}
	private BinaryNode generateSubTree(int nodeCount, List<String> keys) {
		if(nodeCount < 1 || keys == null || keys.isEmpty()) return null;

		int leftNodeCount = random.nextInt(nodeCount);
		int rightNodeCount = nodeCount - leftNodeCount - 1;

		BinaryNode node = new BinaryNode(keys.get(0));
		keys.remove(0);

		node.left = generateSubTree(leftNodeCount, keys);
		node.right = generateSubTree(rightNodeCount, keys);

		return node;
	}

	public List<String> inOrderTraversal() {
		ArrayList<String> traversalList = new ArrayList<>();
		inOrder(root, traversalList);
		return traversalList;
	}
	private void inOrder(BinaryNode node, List<String> taversalList) {
        if(node != null) {
            inOrder(node.left, taversalList);
            taversalList.add(node.key);
			inOrder(node.right, taversalList);
		}
	}

	public List<String> breadthFirstTraversal() {
		ArrayList<String> traversalList = new ArrayList<>();
		List<BinaryNode> binaryNodeQueue = new LinkedList<>();

		binaryNodeQueue.add(root);

		while(!binaryNodeQueue.isEmpty()) {
			BinaryNode currentBinaryNode = binaryNodeQueue.remove(0);
			traversalList.add(currentBinaryNode.key);

			if(currentBinaryNode.left != null) 
				binaryNodeQueue.add(currentBinaryNode.left);
			if(currentBinaryNode.right != null)
				binaryNodeQueue.add(currentBinaryNode.right);
		}

		return traversalList;
	}
}
