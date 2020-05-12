# Bowling Ten-Pin Algorithm
This project was used for technical review. It implements an algorithm that calculates points for ten-pin bowling and validates the sum against a REST API.

# Running the application
Clone project to a folder and in a terminal from the root of the project folder execute:

> ./gradle run

# Running tests

> ./gradlew clean test

#### Note

For some reason the application receives status code 400 along with the message "Invalid token". It requires a little digging to find the cause.