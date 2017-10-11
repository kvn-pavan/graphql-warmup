package com.eblock.interview.warmup.graphql.type;

import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLNonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;

@Component
public class InventoryItemInterface {

    private final InventoryItemTypeResolver inventoryItemTypeResolver;

    @Autowired
    public InventoryItemInterface(InventoryItemTypeResolver inventoryItemTypeResolver) {
        this.inventoryItemTypeResolver = inventoryItemTypeResolver;
    }

    private GraphQLInterfaceType cachedInterface;
    public GraphQLInterfaceType get() {
        if (cachedInterface == null) {
            cachedInterface = newInterface()
                    .name("InventoryItem")
                    .field(newFieldDefinition()
                            .name("id")
                            .type(new GraphQLNonNull(GraphQLString))
                            .build())
                    .field(newFieldDefinition()
                            .name("tagLine")
                            .type(GraphQLString)
                            .build())
                    .field(newFieldDefinition()
                            .name("description")
                            .type(GraphQLString)
                            .build())
                    .field(newFieldDefinition()
                            .name("year")
                            .type(new GraphQLNonNull(GraphQLInt))
                            .build())
                    .field(newFieldDefinition()
                            .name("price")
                            .type(MonetaryAmountType.MonetaryAmountType)
                            .build())
                    .typeResolver(inventoryItemTypeResolver)
                    .build();
        }
        return cachedInterface;
    }
}
