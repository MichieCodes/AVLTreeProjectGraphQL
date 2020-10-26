package Michie.Codes.AVLTreeGraphQLServer.Trees;

import Michie.Codes.AVLTreeGraphQLServer.Models.Book;

public class AVLNode {
    public String key;
    public Book value;
    public int height;
    public AVLNode left;
    public AVLNode right;

	AVLNode(String key) {
		this.key = key;
		height = 1;
	}
	AVLNode(String key, Book value) {
		this.key = key;
		this.value = value;
		height = 1;
	}
}