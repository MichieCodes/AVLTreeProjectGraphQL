package Michie.Codes.AVLTreeGraphQLServer.Services;

import Michie.Codes.AVLTreeGraphQLServer.Models.BinaryTreeDisplay;
import Michie.Codes.AVLTreeGraphQLServer.Trees.AVLTree;
import Michie.Codes.AVLTreeGraphQLServer.Trees.BinaryTree;
import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryTreeService {
    private Random random = new Random();
    
    private BinaryTree binaryTree = new BinaryTree();
    private ArrayList<String> logs = new ArrayList<>();

    public BinaryTreeDisplay generateBinaryTree(int nodes) {
        logs.clear();
        binaryTree.generateRandomBinaryTree(generateISBNList(nodes));

        if(checkBST().booleanValue()) {
            logs.add("Binary Tree is a Binary Search Tree");
            AVLTree avlTree = new AVLTree();
            Boolean isAVLTree = avlTree.buildAVLFromBinaryTree(binaryTree);
        
            logs.addAll(avlTree.logs);

            if(isAVLTree.booleanValue())
                logs.add("Binary Tree is an AVL Tree");
            else
                logs.add("Binary Tree is not an AVL Tree");
        } else {
            logs.add("Binary Tree is not a Binary Search Tree");
            logs.add("Binary Tree is not an AVL Tree");
        }

        return new BinaryTreeDisplay(binaryTree);
    }
    public List<String> getBinaryTreeLogs() {
        return logs;
    }

    private ArrayList<String> generateISBNList(int nodes) {
        ArrayList<String> ISBNList = new ArrayList<>();

        for(int i = 0; i < nodes; i++)
            ISBNList.add("978" + Strings.padStart(
                String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(999999999)), 10, '0')
            );

        return ISBNList;
    }

    Boolean checkBST() {
        logs.clear();
        List<String> inOrderTraversalList = binaryTree.inOrderTraversal();
        Boolean isValid = true;
        
        for(int i = inOrderTraversalList.size() - 1; i >= 1; i--) {
            String current = inOrderTraversalList.get(i);
            String previous = inOrderTraversalList.get(i - 1);

            if(current.compareTo(previous) < 0) {
                logs.add(String.format("%s is less than %s, but is out of order", current, previous));
                isValid = false;
            }
        }
        
        return isValid.booleanValue();
    }
}