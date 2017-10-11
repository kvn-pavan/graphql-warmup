package com.eblock.interview.warmup.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eblock.interview.warmup.graphql.query.InventoryItemQuery;
import com.eblock.interview.warmup.graphql.type.Types;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

@Component
class SchemaBuilder {

    private final GraphQLSchema schema;

    @Autowired
    public SchemaBuilder(InventoryItemQuery inventoryItemQuery, Types types) {
        final GraphQLObjectType.Builder queryType = GraphQLObjectType.newObject().name("QueryType");
        queryType.field(inventoryItemQuery.get());
        final GraphQLSchema.Builder schemaBuilder = GraphQLSchema.newSchema();
        schemaBuilder.query(queryType.build());

        // Must manually add types that are not explicitly included in the graph.
        schema = schemaBuilder.build(types.getTypes());
    }
    
    GraphQLSchema getSchema() {
        return schema;
    }
}
