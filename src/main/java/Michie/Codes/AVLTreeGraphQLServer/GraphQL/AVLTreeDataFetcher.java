package Michie.Codes.AVLTreeGraphQLServer.GraphQL;

import Michie.Codes.AVLTreeGraphQLServer.Models.AVLTreeDisplay;
import Michie.Codes.AVLTreeGraphQLServer.Services.AVLTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import graphql.schema.DataFetcher;
import java.util.List;

@Component
public class AVLTreeDataFetcher {
    @Autowired
    private AVLTreeService avlTreeService;

    public DataFetcher<AVLTreeDisplay> generateAVLTree() {
        return environmentVariables -> {
            String comparator = environmentVariables.getArgument("comparator");
            if(comparator == null || comparator.isBlank()) comparator = "ISBN";
            
            return avlTreeService.generateAVLTree(true, comparator.trim());
        };
    }
    public DataFetcher<List<String>> getAVLTreeLogs() {
        return environmentVariables -> avlTreeService.getAVLTreeLogs();
    }
}
