package com.eblock.interview.warmup.web;

import com.eblock.interview.warmup.graphql.Executor;
import com.eblock.interview.warmup.graphql.GraphQLContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class GraphQLController {

    private final Executor executor;

    @Autowired
    public GraphQLController(Executor executor) {
        this.executor = executor;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/graphiql/graphql", method = RequestMethod.POST)
    public ResponseEntity<Object> graphQL(Locale locale, @RequestBody final GraphQLRequestBody query) throws IOException {

        HashMap<String, Object> variables = null;
        if (query.variables != null) {
            if ((query.variables instanceof String) && !StringUtils.isEmpty(query.variables)) {
                final Map anyVariables = new ObjectMapper().readValue((String) query.variables, Map.class);
                variables = new HashMap<>();
                for (Object key : anyVariables.keySet()) {
                    if (key instanceof String) {
                        variables.put((String) key, anyVariables.get(key));
                    }
                }
            } else if (query.variables instanceof Map){
                variables = (HashMap<String, Object>) query.variables;
            }
        }

        final ExecutionResult executionResult = executor.execute(query.query, variables, new GraphQLContext(locale));
        if (!executionResult.getErrors().isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("errors", executionResult.getErrors()), null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(Collections.singletonMap("data", executionResult.getData()), null, HttpStatus.OK);
    }

    @SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
    private static class GraphQLRequestBody {
        public String query;
        public Object variables;
    }
}
