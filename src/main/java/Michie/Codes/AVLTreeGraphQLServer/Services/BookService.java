package Michie.Codes.AVLTreeGraphQLServer.Services;

import Michie.Codes.AVLTreeGraphQLServer.Models.Book;
import Michie.Codes.AVLTreeGraphQLServer.Repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.io.Resources;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service("bookService")
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book createBook(String ISBN, String title, String author) {
        if(ISBN.isBlank() || title.isBlank() || author.isBlank()) return null;

        Book newBook = new Book();
        newBook.ISBN = ISBN;
        newBook.title = title;
        newBook.author = author;

        return bookRepository.save(newBook);
    }
    public Book updateBook(Integer id, String ISBN, String title, String author) {
        if(id == null || ISBN.isBlank() || title.isBlank() || author.isBlank()) return null;

        Book updatedBook = bookRepository.findById(id).orElse(null);
        if(updatedBook == null) return null;

        updatedBook.ISBN = ISBN;
        updatedBook.title = title;
        updatedBook.author = author;

        return bookRepository.save(updatedBook);
    }
    
    public Boolean loadBooksFromText(String Text, Boolean append, Boolean loadDefault) {
        if(!loadDefault.booleanValue() && Text.isBlank()) return false; 
        try {
            if(loadDefault.booleanValue())
                Text = Resources.toString(Resources.getResource("Books.txt"), StandardCharsets.UTF_8);
            if(Text.isBlank()) return false; 
        } catch(Exception e) {
            return false;
        }

        List<Book> Books = new ArrayList<>();
        String[] lines = Text.split("\n");

        for(String line: lines) {
            if(line.isBlank()) continue; 

            String[] properties = line.trim().split("\\s");
            if(properties.length != 3) return false;

            Book newBook = new Book();
            newBook.ISBN = properties[0];
            newBook.title = properties[1].replace("_", " ");
            newBook.author = properties[2];

            Books.add(newBook);
        }

        if(!append.booleanValue()) bookRepository.deleteAll();

        bookRepository.saveAll(Books);
        return true;    
    }

    public Integer deleteBook(Integer id) {
        if(id == null) return -1;
        bookRepository.deleteById(id);
        return id;
    }
    public Boolean deleteAllBooks() {
        bookRepository.deleteAll();
        return true;
    }
}
