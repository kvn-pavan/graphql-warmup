# java-spring-graphql-warmup

A very simple project template is provided with a list of features that need to be added to the project.
The "target" completion time is ~4 hours and covers some tasks such as:

    - Creating domain entities
    - Updating the database schema
    - Writing a jdbc template repository
    - Updating graphql queries and mutations.
    - Threading localization through the graphql context.

Should be familiar with Java and SQL. Some knowledge of Spring and GraphQL will help.

## Whats Required:

    - MySQL (or any db of choice, mysql is already setup in the project)
    - Java 8
    - Maven

## Getting Started:

    1. Create a database named `graphql_warmup`
    2. Update the access credentials in `src/main/resources/application.properties`
    3. Start the server with `mvn spring-boot:run`
    4. View the GraphQL editor endpoint with `localhost:8080/graphiql` in the chrome browser.

Sample query:

```
{
  inventoryItem(id: "1") {
    __typename
    id
    tagLine
    description
    year
    price {
      currencyCode
      amount
      formattedAmount
    }
    ... on Vehicle {
      make
      model
    }
  }
}
```

## Tasks

### 1. Expanding the data model

Add the following fields to the vehicle entity. They should be accessible through the graphql endpoint.

    - Fuel Type (enum)
            - Diesel
            - Electric
            - Flex Fuel
            - Flex Fuel / Electric Hybrid
            - Gasoline
            - Gasoline / Electric Hybrid
            - Gasoline / Natural Gas
            - Gasoline / Propane
            - Hydrogen
            - Natural Gas
            - Propane
    - Cylinders (Int)
    - Displacement (Double)
    - Odometer // Store in the database in the same row. Make child entity in graphQL.
        - mileage (in km) Int
        - unit (km or mi) String
    - Options (list of strings, 1-n in database)

The query below should function:
```
{
  inventoryItem(id: "1") {
    __typename
    id
    tagLine
    description
    year
    price {
      currencyCode
      amount
      formattedAmount
    }
    ... on Vehicle {
      make
      model
      displacement
      cylinders
      fuelType
      odometer {
        amount
        unit
      }
      options
    }
  }
}
```

### 2. Create a Boat entity

Create a `Boat` entity that extends `InventoryItem`. It should be queryable and implement the `InventoryItemInterface`.
Add one extra field specific to boat:

    - motorMount (enum)
        -Inboard
        -Outboard
        -None

Add another row to the databse with `id='2'` and type `BOAT`.
This should produce a result with the query below:
```
{
  inventoryItem(id: "2") {
    __typename
    id
    tagLine
    description
    year
    price {
      currencyCode
      amount
      formattedAmount
    }
    ... on Vehicle {
      make
      model
      displacement
      cylinders
      fuelType
      odometer {
        amount
        unit
      }
      options
    }
    ... on Boat {
      motorMount
    }
  }
}
```

### 3. Localize the formatted price and formatted mileage with the user's locale.

Load the locale from the GraphQL controller into the GraphQLContext and update the price formatter amount.
Change the locale on the request using the query string `?locale=en_CA` or `?locale=fr_CA`.

#### 4. Create a Relay Connection endpoint

Create a relay connection endpoint for the inventory item interface that allows paging.
Don't spend to much time on this. Only implement what is required for the query below.
hint: https://github.com/graphql-java/graphql-java/blob/master/src/test/groovy/graphql/RelaySchema.java

```
{
  inventoryItemConnection(first: 10) {
    pageInfo {
      startCursor
      hasNextPage
    }
    edges {
      cursor
      node {
        __typename
        id
        tagLine
        description
        year
        price {
          currencyCode
          amount
          formattedAmount
        }
        ... on Vehicle {
          fuelType
          cylinders
          displacement
          odometer {
            amount
            unit
          }
          options
        }
        ... on Boat {
          motorMount
        }
      }
    }
  }
}
```
