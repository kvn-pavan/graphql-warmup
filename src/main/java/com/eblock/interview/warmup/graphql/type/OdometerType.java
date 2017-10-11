package com.eblock.interview.warmup.graphql.type;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import com.eblock.interview.warmup.domain.Odometer;

import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

class OdometerType {
	final static GraphQLObjectType OdometerType = newObject()
            .name("Odometer")
            .field(newFieldDefinition()
                    .name("mileage")
                    .type(new GraphQLNonNull(GraphQLInt))
                    .dataFetcher(environment -> ((Odometer) environment.getSource()).getMileage())
                    .build())
            .field(newFieldDefinition()
                    .name("unit")
                    .type(new GraphQLNonNull(GraphQLString))
                    .dataFetcher(environment -> ((Odometer) environment.getSource()).getUnit())
                    .build())
            .build();
}
