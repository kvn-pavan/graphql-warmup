package com.eblock.interview.warmup.graphql.type;

import com.eblock.interview.warmup.domain.Boat;
import com.eblock.interview.warmup.domain.Vehicle;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @implNote  This class breaks a circular dependency.
 *            InventoryItemTypeResolver->ConcreteItemType->InventoryItemInterface->InventoryItemTypeResolver
 *
 *            The type resolver cannot be a lambda and must have its dependencies lazily wired.
 *            The lazily wired dependencies must exist because the GraphQLSchema bean requires them to build the schema
 *            in the constructor. Therefore this is a safe operation and complete wiring is guaranteed.
 */
@Component
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class InventoryItemTypeResolver implements TypeResolver {

    @Lazy
    @Autowired
    private VehicleType vehicleType;
    
    @Lazy
    @Autowired
    private BoatType boatType;

    @Override
    public GraphQLObjectType getType(Object object) {
        if (object instanceof Vehicle) {
            return vehicleType.get();
        } else if(object instanceof Boat) {
        	return boatType.get();
        }
        throw new RuntimeException("Unknown type " + object.getClass().getCanonicalName());
    }
}
