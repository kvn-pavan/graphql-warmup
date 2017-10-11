package com.eblock.interview.warmup.graphql.type;

import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.Scalars.GraphQLFloat;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * @implNote Caching the object so only one copy exists.
 *           Important so pointer comparison in the java-graphql project does not fail if referenced twice.
 */
@Component
public class VehicleType {

    private final InventoryItemInterface inventoryItemInterface;

    @Autowired
    public VehicleType(InventoryItemInterface inventoryItemInterface) {
        this.inventoryItemInterface = inventoryItemInterface;
    }

    private GraphQLObjectType cachedType;
    public GraphQLObjectType get() {
        if (cachedType == null) {
            cachedType = newObject()
                    .name("Vehicle")
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
                            .name("make")
                            .type(new GraphQLNonNull(GraphQLString))
                            .build())
                    .field(newFieldDefinition()
                            .name("model")
                            .type(new GraphQLNonNull(GraphQLString))
                            .build())
                    .field(newFieldDefinition()
                            .name("cylinders")
                            .type(new GraphQLNonNull(GraphQLInt))
                            .build())
                    .field(newFieldDefinition()
                            .name("displacement")
                            .type(new GraphQLNonNull(GraphQLFloat))
                            .build())
                    .field(newFieldDefinition()
                            .name("fuelType")
                            .type(new GraphQLNonNull(GraphQLString))
                            .build())
                    .field(newFieldDefinition()
                            .name("odometer")
                            .type(OdometerType.OdometerType)
                            .build())
                    .build();
        }
        return cachedType;
    }
}
