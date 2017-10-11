package com.eblock.interview.warmup.graphql.query;

import com.eblock.interview.warmup.graphql.type.InventoryItemInterface;
import com.eblock.interview.warmup.repository.InventoryItemRepository;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;

@Component
public class InventoryItemQuery {

    private final InventoryItemInterface inventoryItemInterface;
    private final InventoryItemRepository inventoryItemRepository;

    @Autowired
    public InventoryItemQuery(InventoryItemInterface inventoryItemInterface,
                              InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemInterface = inventoryItemInterface;
        this.inventoryItemRepository = inventoryItemRepository;
    }

    private GraphQLFieldDefinition cachedDefinition;
    public GraphQLFieldDefinition get() {
        if (cachedDefinition == null) {
            cachedDefinition = newFieldDefinition()
                    .name("inventoryItem")
                    .type(inventoryItemInterface.get())
                    .argument(newArgument()
                            .name("id")
                            .type(new GraphQLNonNull(GraphQLString))
                            .build())
                    .dataFetcher(environment -> inventoryItemRepository.get(environment.getArgument("id")))
                    .build();
        }
        return cachedDefinition;
    }
}
