package Michie.Codes.AVLTreeGraphQLServer;

import Michie.Codes.AVLTreeGraphQLServer.Services.BookService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class AvlTreeGraphQlServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(AvlTreeGraphQlServerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadDefaultBooks(BookService bookService) {
		return args -> bookService.loadBooksFromText("", false, true);
	}

}
