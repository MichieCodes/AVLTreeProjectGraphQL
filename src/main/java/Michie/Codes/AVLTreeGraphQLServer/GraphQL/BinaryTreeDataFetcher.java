package Michie.Codes.AVLTreeGraphQLServer.GraphQL;

import Michie.Codes.AVLTreeGraphQLServer.Models.BinaryTreeDisplay;
import Michie.Codes.AVLTreeGraphQLServer.Services.BinaryTreeService;
import org.springframework.stereotype.Component;
import graphql.schema.DataFetcher;
import java.util.List;

@Component
public class BinaryTreeDataFetcher {
    private BinaryTreeService binaryTreeService = new BinaryTreeService();

    public DataFetcher<BinaryTreeDisplay> generateBinaryTree() {
        return environmentVariables -> binaryTreeService.generateBinaryTree(environmentVariables.getArgument("nodes"));
    }
    public DataFetcher<List<String>> getBinaryTreeLogs() {
        return environmentVariables -> binaryTreeService.getBinaryTreeLogs();
    }
}
