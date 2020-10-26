package Michie.Codes.AVLTreeGraphQLServer.Trees;
// This code has been contributed by Mayank Jaiswal

import Michie.Codes.AVLTreeGraphQLServer.Models.Book;
import java.util.ArrayList;
import java.util.List;

// Java program for insertion in AVL Tree
public class AVLTree {
	public AVLNode root;
	public List<String> logs;
	public String comparator;

	public AVLTree() {
		root = null;
		logs = new ArrayList<>();
		comparator = "ISBN";
	}
	public AVLTree(String comparator) {
		root = null;
		logs = new ArrayList<>();
		this.comparator = comparator;
	}

	// A utility function to get the height of the tree
	int height(AVLNode N) {
		if(N == null) return 0;

		return N.height;
	}

	// A utility function to get maximum of two integers
	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	// A utility function to right rotate subtree rooted with y
	// See the diagram given above.
	AVLNode rightRotate(AVLNode y) {
		AVLNode x = y.left;
		AVLNode T2 = x.right;

		// Perform rotation
		x.right = y;
		y.left = T2;

		// Update heights
		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;

		// Return new root
		return x;
	}

	// A utility function to left rotate subtree rooted with x
	// See the diagram given above.
	AVLNode leftRotate(AVLNode x) {
		AVLNode y = x.right;
		AVLNode T2 = y.left;

		// Perform rotation
		y.left = x;
		x.right = T2;

		// Update heights
		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;

		// Return new root
		return y;
	}

	// Get Balance factor of node N
	int getBalance(AVLNode N) {
		if(N == null) return 0;

		return height(N.left) - height(N.right);
	}

	public AVLNode insert(AVLNode node, String key, Book value) {

		/* 1. Perform the normal BST insertion */
		if(node == null)
			return (new AVLNode(key, value));

		if(/* key < node.key */key.compareTo(node.key) < 0)
			node.left = insert(node.left, key, value);
		else if(/* key > node.key */key.compareTo(node.key) > 0)
			node.right = insert(node.right, key, value);
		else // Duplicate keys not allowed
			return node;

		/* 2. Update height of this ancestor node */
		node.height = 1 + max(height(node.left),
							height(node.right));

		/* 3. Get the balance factor of this ancestor
			node to check whether this node became
			unbalanced */
		int balance = getBalance(node);

		// If this node becomes unbalanced, then there
		// are 4 cases Left Left Case
		if(balance > 1 && /* key < node.left.key */key.compareTo(node.left.key) < 0) {
			logs.add(String.format("Imbalance occurred at inserting %s %s; fixed in Right Rotation", comparator, key));
			return rightRotate(node);
		}
		
		// Right Right Case
		if(balance < -1 && /* key > node.right.key */key.compareTo(node.right.key) > 0) {
			logs.add(String.format("Imbalance occurred at inserting %s %s; fixed in Left Rotation", comparator, key));
			return leftRotate(node);
		}
		
		// Left Right Case
		if(balance > 1 && /* key > node.left.key */key.compareTo(node.left.key) > 0) {
			logs.add(String.format("Imbalance occurred at inserting %s %s; fixed in LeftRight Rotation", comparator, key));
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		
		// Right Left Case
		if(balance < -1 && /* key < node.right.key */key.compareTo(node.right.key) < 0) {
			logs.add(String.format("Imbalance occurred at inserting %s %s; fixed in RightLeft Rotation", comparator, key));
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		/* return the (unchanged) node pointer */
		return node;
	}

	public Boolean buildAVLFromBinaryTree(BinaryTree binaryTree) {
		List<String> keys = binaryTree.breadthFirstTraversal();
		if(keys == null || keys.isEmpty()) return false;

		logs.clear();
		
		for(String key : keys) 
			root = insert(root, key, null);

		return logs.isEmpty();
	}

	// A utility function to print preorder traversal
	// of the tree.
	public void preOrder(AVLNode node) {
		if(node != null) {
			System.out.print(node.key + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
}
