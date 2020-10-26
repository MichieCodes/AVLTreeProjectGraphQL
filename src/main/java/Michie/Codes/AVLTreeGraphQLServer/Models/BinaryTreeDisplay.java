package Michie.Codes.AVLTreeGraphQLServer.Models;

import Michie.Codes.AVLTreeGraphQLServer.Trees.BinaryNode;
import Michie.Codes.AVLTreeGraphQLServer.Trees.BinaryTree;

public class BinaryTreeDisplay {
    public String name;
    public BinaryTreeDisplay[] children;

    public BinaryTreeDisplay() {
        this.name = null;
        this.children = null;
    }
    public BinaryTreeDisplay(BinaryTree binaryTree) {
        if(binaryTree.root == null) return;

        this.name = binaryTree.root.key;
        this.children = new BinaryTreeDisplay[2];
        this.children[0] = generateChildren(binaryTree.root.left);
        this.children[1] = generateChildren(binaryTree.root.right);

        if(this.children[0].name == null && this.children[1].name == null)
            this.children = null;
    }

    private BinaryTreeDisplay generateChildren(BinaryNode node) {
        if(node == null) return new BinaryTreeDisplay();

        BinaryTreeDisplay childDisplay = new BinaryTreeDisplay();

        childDisplay.name = node.key;
        childDisplay.children = new BinaryTreeDisplay[2];
        childDisplay.children[0] = generateChildren(node.left);
        childDisplay.children[1] = generateChildren(node.right);

        if(childDisplay.children[0].name == null && childDisplay.children[1].name == null)
            childDisplay.children = null;

        return childDisplay;
    }
}
