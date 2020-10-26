package Michie.Codes.AVLTreeGraphQLServer.Repositories;

import Michie.Codes.AVLTreeGraphQLServer.Models.Book;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository("bookRepository")
public interface BookRepository extends CrudRepository<Book, Integer> {}
