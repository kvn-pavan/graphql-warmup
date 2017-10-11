package com.eblock.interview.warmup.graphql.type;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

/**
 * @implNote Caching the object so only one copy exists.
 *           Important so pointer comparison in the java-graphql project does not fail if referenced twice.
 */
@Component
public class BoatType {

    private final InventoryItemInterface inventoryItemInterface;

    @Autowired
    public BoatType(InventoryItemInterface inventoryItemInterface) {
        this.inventoryItemInterface = inventoryItemInterface;
    }

    private GraphQLObjectType cachedType;
    public GraphQLObjectType get() {
        if (cachedType == null) {
            cachedType = newObject()
                    .name("Boat")
                    .withInterface(inventoryItemInterface.get())
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
                    .field(newFieldDefinition()
                            .name("motorMount")
                            .type(new GraphQLNonNull(GraphQLString))
                            .build())
                    .build();
        }
        return cachedType;
    }
}
