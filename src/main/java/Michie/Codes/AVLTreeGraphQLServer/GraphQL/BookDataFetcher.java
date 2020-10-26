package Michie.Codes.AVLTreeGraphQLServer.GraphQL;

import Michie.Codes.AVLTreeGraphQLServer.Models.Book;
import Michie.Codes.AVLTreeGraphQLServer.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.base.Strings;
import graphql.schema.DataFetcher;

@Component
public class BookDataFetcher {
    @Autowired
    private BookService bookService;

    public DataFetcher<Iterable<Book>> getBooks() {
        return environmentVariables -> bookService.findAll();
    }
    
    public DataFetcher<Book> createBook() {
        return environmentVariables -> bookService.createBook(
            environmentVariables.getArgument("ISBN"),
            environmentVariables.getArgument("title"),
            environmentVariables.getArgument("author")
        );
    }
    public DataFetcher<Book> updateBook() {
        return environmentVariables -> bookService.updateBook(
            environmentVariables.getArgument("id"),
            environmentVariables.getArgument("ISBN"),
            environmentVariables.getArgument("title"),
            environmentVariables.getArgument("author")
        );
    }
    
    public DataFetcher<Boolean> loadBooksFromText() {
        return environmentVariables -> {
            String Text = environmentVariables.getArgument("text");

            return bookService.loadBooksFromText(
                Text, environmentVariables.getArgument("append"), Text == null || Strings.isNullOrEmpty(Text)
            );
        };
    }

    public DataFetcher<Integer> deleteBook() {
        return environmentVariables -> bookService.deleteBook(
            environmentVariables.getArgument("id")
        );
    }
    public DataFetcher<Boolean> deleteAllBooks() {
        return environmentVariables -> bookService.deleteAllBooks();
    }
}
