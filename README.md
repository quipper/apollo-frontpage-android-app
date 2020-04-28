# Overview

This is a simple example Apollo Android app inspired by https://github.com/apollographql/frontpage-ios-app.
It is a very basic app, but it does demonstrate how you can hook up GraphQL query results to your UI using MVVM architecture.

The code in `PostsRepository.kt` fetches data based on a GraphQL query defined in `PostListActivity.graphql`. That query refers to a fragment defined in `PostListItem.graphql`, which nicely illustrates how you can describe your data needs next to the UI component that uses them.

## Workflow

We followed the following workflow while working GraphQL endpoint and the Apollo SDK.

- Write and test queries using [GraphiQL](https://github.com/graphql/graphiql).
- Use the Apollo plugin to generate model types from the schema and queries.
- Integrate ApolloClient into the app, passing in the generated queries and write the logic for handling already-parsed responses.

## Branch Info

Branch | Description
:--: | :--:
master | RxJava2 Support  |

## Installation

This project requires Android studio 3.6.3 or later to build this app. You can install it from [here](https://developer.android.com/studio).

### Server

This app talks to the [frontpage example server](https://github.com/apollographql/frontpage-server). Follow the instructions there and start the server before running the Android app.

### Starting the app

If you want to run on a device, change `10.0.2.2` IP address to your machine's local IP address in `Modules.kt`.