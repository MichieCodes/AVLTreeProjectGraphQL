package Michie.Codes.AVLTreeGraphQLServer.Services;

import Michie.Codes.AVLTreeGraphQLServer.Models.AVLTreeDisplay;
import Michie.Codes.AVLTreeGraphQLServer.Models.Book;
import Michie.Codes.AVLTreeGraphQLServer.Trees.AVLTree;
import com.google.common.base.Strings;
import com.google.common.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class AVLTreeService {
    @Autowired
    private BookService bookService;
    private AVLTree avlTree;
    private String comparator;

    public AVLTreeDisplay generateAVLTree(Boolean useDatabase, String comparator) {
        this.comparator = comparator;

        if((!useDatabase.booleanValue() && createTreeFromFile()) || (useDatabase.booleanValue() && createTreeFromDatabase()))
            return new AVLTreeDisplay(avlTree);
        else return null;
    }
    public List<String> getAVLTreeLogs() {
        if(avlTree == null) return new ArrayList<String>();
        return avlTree.logs;
    }

    private Boolean createTreeFromFile() {
        try {
            avlTree = new AVLTree(comparator); 
            String fileText = Resources.toString(Resources.getResource("Books.txt"), StandardCharsets.UTF_8);
            String[] lines = fileText.split("\n");

            for(String line: lines) {
                if(Strings.isNullOrEmpty(line)) continue; 

                String[] properties = line.trim().split("\\s");
                if(properties.length != 3) continue;

                Book book = new Book();
                book.ISBN = properties[0];
                book.title = properties[1].replace("_", " ");
                book.author = properties[2];

                insertTreeNode(avlTree, book);
            }

            return true;
        } catch(Exception e) {
            return false;
        }
    }
    private Boolean createTreeFromDatabase() {
        avlTree = new AVLTree(comparator); 

        Iterable<Book> Books = bookService.findAll();

        if(Books == null) return false;

        for(Book book : Books) {
            if(book != null) 
                insertTreeNode(avlTree, book);
        }

        return true;
    }

    private void insertTreeNode(AVLTree avlTree, Book book) {
        switch(comparator) {
            case "title":
                avlTree.root = avlTree.insert(avlTree.root, book.title, book);
                break;
            case "author":
                avlTree.root = avlTree.insert(avlTree.root, book.author, book);
                break;
            default:
                avlTree.root = avlTree.insert(avlTree.root, book.ISBN, book);
                break;
        }
    }
}
