package Michie.Codes.AVLTreeGraphQLServer.Models;

import Michie.Codes.AVLTreeGraphQLServer.Trees.AVLNode;
import Michie.Codes.AVLTreeGraphQLServer.Trees.AVLTree;

public class AVLTreeDisplay {
    public String name;
    public Book attributes;
    public AVLTreeDisplay[] children;

    public AVLTreeDisplay() {
        this.name = null;
        this.attributes = null;
        this.children = null;
    }
    public AVLTreeDisplay(AVLTree avlTree) {
        if(avlTree.root == null) return;
        this.name = avlTree.root.key;
        this.attributes = avlTree.root.value;
        this.children = new AVLTreeDisplay[2];
        this.children[0] = generateChildren(avlTree.root.left);
        this.children[1] = generateChildren(avlTree.root.right);

        if(this.children[0].name == null && this.children[1].name == null)
            this.children = null;
    }

    private AVLTreeDisplay generateChildren(AVLNode node) {
        if(node == null) return new AVLTreeDisplay();

        AVLTreeDisplay childDisplay = new AVLTreeDisplay();

        childDisplay.name = node.key;
        childDisplay.attributes = node.value;
        childDisplay.children = new AVLTreeDisplay[2];
        childDisplay.children[0] = generateChildren(node.left);
        childDisplay.children[1] = generateChildren(node.right);

        if(childDisplay.children[0].name == null && childDisplay.children[1].name == null)
            childDisplay.children = null;

        return childDisplay;
    }
}
