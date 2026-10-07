# Day 3 - Control Flow + Maven

## Topics Covered

* if-else
* switch
* for loop
* do-while loop
* enhanced for loop
* break
* continue
* labelled break
* Maven project structure
* POM configuration
* Maven lifecycle
* Maven profiles

## Project

ATM Simulator implemented using Java control-flow statements.

### Features

1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Mini Statement
5. Exit

### Input Validation

* Invalid PIN input is handled safely.
* Maximum 3 PIN attempts are allowed.
* Invalid menu input does not crash the program.
* Invalid deposit and withdrawal amounts are rejected.
* Withdrawal greater than balance is rejected.

## Maven Commands

Build the project:

```text
mvn clean package
```

Run the application:

```text
java -cp target\classes com.hackathon.day3.ATMSimulator
```

## Maven Profiles

Development profile:

```text
mvn help:evaluate -Dexpression=environment -Pdev -q -DforceStdout
```

Result:

```text
development
```

Production profile:

```text
mvn help:evaluate -Dexpression=environment -Pprod -q -DforceStdout
```

Result:

```text
production
```

## Maven Lifecycle

The project uses the standard Maven lifecycle:

`validate -> compile -> test -> package -> install`

## Result

`mvn clean package` completed successfully and the ATM menu was tested with valid and invalid input.
