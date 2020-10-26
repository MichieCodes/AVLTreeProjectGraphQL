package Michie.Codes.AVLTreeGraphQLServer.GraphQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import graphql.analysis.MaxQueryDepthInstrumentation;
import com.google.common.io.Resources;
import java.nio.charset.StandardCharsets;
import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class GraphQLProvider {
    @Autowired
    private BookDataFetcher bookDataFetcher;
    @Autowired
    private AVLTreeDataFetcher avlTreeDataFetcher;
    @Autowired
    private BinaryTreeDataFetcher binaryTreeDataFetcher;

    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL() {return graphQL;}

    @PostConstruct
    public void init() throws IOException { 
        GraphQLSchema graphQLSchema = schemaBuilder(
            Resources.toString(
                Resources.getResource("schema.graphql"), 
                StandardCharsets.UTF_8
            )
        );

        this.graphQL = GraphQL.newGraphQL(graphQLSchema).instrumentation(new MaxQueryDepthInstrumentation(7)).build();
    }

    private GraphQLSchema schemaBuilder(String schema) { 
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        TypeDefinitionRegistry typeDefinitions = new SchemaParser().parse(schema);
        return schemaGenerator.makeExecutableSchema(
            typeDefinitions, 
            RuntimeWiring.newRuntimeWiring()
                .type(buildMutations())
                .type(buildQueries()).build()
        );
    }

    private TypeRuntimeWiring.Builder buildQueries() {
        return TypeRuntimeWiring.newTypeWiring("Query")
            .dataFetcher("GetBooks", bookDataFetcher.getBooks())
            .dataFetcher("GenerateAVLTree", avlTreeDataFetcher.generateAVLTree())
            .dataFetcher("GenerateBinaryTree", binaryTreeDataFetcher.generateBinaryTree())
            .dataFetcher("GetAVLTreeLogs", avlTreeDataFetcher.getAVLTreeLogs())
            .dataFetcher("GetBinaryTreeLogs", binaryTreeDataFetcher.getBinaryTreeLogs());
    }

    private TypeRuntimeWiring.Builder buildMutations() {
        return TypeRuntimeWiring.newTypeWiring("Mutation")
            .dataFetcher("CreateBook", bookDataFetcher.createBook())
            .dataFetcher("UpdateBook", bookDataFetcher.updateBook())
            .dataFetcher("LoadBooksFromText", bookDataFetcher.loadBooksFromText())
            .dataFetcher("DeleteBook", bookDataFetcher.deleteBook())
            .dataFetcher("DeleteAllBooks", bookDataFetcher.deleteAllBooks());
    }
}