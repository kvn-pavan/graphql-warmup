package com.eblock.interview.warmup.graphql;

import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Service
public class Executor {

    private final GraphQL graphQL;

    @Autowired
    public Executor(SchemaBuilder schemaBuilder) {
        this.graphQL = new GraphQL(schemaBuilder.getSchema());
    }

    @Transactional
    public ExecutionResult execute(String query, Map<String, Object> arguments, GraphQLContext context) {
        return !CollectionUtils.isEmpty(arguments) ? graphQL.execute(query, context, arguments) : graphQL.execute(query, context);
    }
}
