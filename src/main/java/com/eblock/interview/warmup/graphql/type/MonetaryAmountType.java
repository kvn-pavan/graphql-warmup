package com.eblock.interview.warmup.graphql.type;

import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import javax.money.MonetaryAmount;

import com.eblock.interview.warmup.graphql.GraphQLContext;

import java.text.NumberFormat;

import static graphql.Scalars.GraphQLFloat;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

class MonetaryAmountType {

    final static GraphQLObjectType MonetaryAmountType = newObject()
            .name("MonetaryAmount")
            .field(newFieldDefinition()
                    .name("currencyCode")
                    .type(new GraphQLNonNull(GraphQLString))
                    .dataFetcher(environment -> ((MonetaryAmount) environment.getSource()).getCurrency().getCurrencyCode())
                    .build())
            .field(newFieldDefinition()
                    .name("amount")
                    .type(new GraphQLNonNull(GraphQLFloat))
                    .dataFetcher(environment -> ((MonetaryAmount) environment.getSource()).getNumber().doubleValue())
                    .build())
            .field(newFieldDefinition()
                    .name("formattedAmount")
                    .type(new GraphQLNonNull(GraphQLString))
                    .dataFetcher(environment -> {
                        final MonetaryAmount monetaryAmount = (MonetaryAmount) environment.getSource();
                        return NumberFormat.getCurrencyInstance(((GraphQLContext)environment.getContext()).getLocale()).format(monetaryAmount.getNumber());
                    }).build())
            .build();
}
