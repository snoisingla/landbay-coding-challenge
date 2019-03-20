# landbay-coding-challenge

## How to build and run

- Open the project in any IDE (Eclipse or IntelliJ IDEA)
- To run the project, directly run the main method from `LandbayChallenge.java`

## Modify the loans and investment data
- `resource` folder contains two csv files `investmentRequests-data.csv` and `loans-data.csv`. Just update these files with new data and run the project again to see the results.
- results are shown in console output in JSON format

## Unit tests
- `LoanMatcherTests.java` contains some basic unit tests for the buisness logic implementation. Tests can be run directly with the IDE.

## Performance and Running Time
- This implementation takes `O(mn)` time, where m is the number of investments and n is the number of loans.
