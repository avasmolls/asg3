# Submarine Commands | Java

**HCDD 311: Object-Oriented Programming & Design — Assignment 3**  
**Pennsylvania State University | Fall 2026**

## Overview

This project implements a command-based submarine navigation system in Java using sealed interfaces, records, and pattern matching. The program simulates submarine movement through a series of commands while maintaining immutable submarine states.

The goal was to explore Java's modern object-oriented programming features, including algebraic data types, exhaustive switch expressions, and record patterns, while demonstrating how commands can transform an object's state without modifying the original.

## Technologies Used

- **Java 25+** — Object-oriented programming
- **IntelliJ IDEA** — Development environment
- **Sealed Interfaces** — Restricting permitted command types
- **Records** — Representing commands and submarine data
- **Pattern Matching** — Type and record patterns in switch expressions
- **Immutability** — Preserving original submarine states

## Features

The program supports four submarine navigation commands:

| Command | Description |
|---|---|
| `Forward(amount)` | Increases the submarine's horizontal position. |
| `Up(amount)` | Decreases the submarine's depth. |
| `Down(amount)` | Increases the submarine's depth. |
| `Surface()` | Resets the submarine's depth to zero without changing its horizontal position. |

### Core Functionality

- **Command Descriptions:** Converts commands into readable strings such as `forward 5`, `up 3`, and `surface`.
- **Individual Command Execution:** Applies a command and returns a new submarine with updated coordinates.
- **Sequential Command Execution:** Processes an array of commands in order using an enhanced `for` loop.
- **Immutable State:** Ensures the original submarine remains unchanged after executing commands.
- **Exhaustive Pattern Matching:** Uses switch expressions to handle all permitted command types without a `default` case.
- **Position Calculations:** Calculates the product of the submarine's final horizontal position and depth.

## Example Usage

### Creating a Submarine

```java
Submarine submarine = new Submarine(4, 7);

System.out.println(submarine.horizontal());
// Output: 4

System.out.println(submarine.depth());
// Output: 7
```

### Executing a Command

```java
Submarine start = new Submarine(0, 0);

Submarine moved = start.execute(new Command.Forward(5));

System.out.println(moved.horizontal());
// Output: 5

System.out.println(start.horizontal());
// Output: 0
```

The original submarine remains unchanged after executing the command.

### Executing Multiple Commands

```java
Command[] commands = {
    new Command.Forward(5),
    new Command.Down(5),
    new Command.Forward(8),
    new Command.Up(3),
    new Command.Down(8),
    new Command.Forward(2)
};

Submarine start = new Submarine(0, 0);
Submarine result = start.executeAll(commands);

System.out.println(result.horizontal());
// Output: 15

System.out.println(result.depth());
// Output: 10
```

### Surfacing the Submarine

```java
Submarine submarine = new Submarine(15, 10);

Submarine surfaced = submarine.execute(new Command.Surface());

System.out.println(surfaced.horizontal());
// Output: 15

System.out.println(surfaced.depth());
// Output: 0
```

The `Surface` command resets the depth while preserving the horizontal position.

## Project Structure

```text
asg3/
├── Main.java
├── Submarine.java
└── Command.java
```

- **Main.java** — Contains the program entry point, command descriptions, sample execution, and validation checks.
- **Submarine.java** — Defines the submarine's position and implements command execution.
- **Command.java** — Defines the sealed command interface and its four nested record implementations.

## Implementation Approach

### Sealed Interfaces & Records

The `Command` interface defines a restricted family of navigation commands using four nested records.

This design ensures that only the permitted command types can implement the interface, providing a structured and type-safe approach to representing submarine movements.

### Pattern Matching

The program uses two forms of Java pattern matching:

- **Type Patterns:** Identify command types when generating readable descriptions.
- **Record Patterns:** Extract command values directly when executing movement operations.

Both switch expressions are exhaustive, allowing the Java compiler to identify missing cases when additional command types are introduced.

### Immutable State Management

Rather than changing the coordinates of an existing submarine, each command returns a new `Submarine` instance.

This approach ensures that previous states remain accessible and prevents unintended modifications to existing objects.

### Sequential Command Processing

The `executeAll()` method processes commands in order using an enhanced `for` loop.

Each command transforms the result of the previous operation, producing a final submarine position while preserving the original starting state.

## Testing & Validation

The program includes checks to verify correct command execution and state preservation.

| Test Case | Expected Position |
|---|---|
| Empty command array starting at (4, 7) | (4, 7) |
| Forward(3), Surface(), Down(2) starting at (4, 7) | (7, 2) |
| Sample sequence starting at (0, 0) | (15, 10) |
| Surface() starting at (15, 10) | (15, 0) |
| Original submarine after command execution | Unchanged |

The project also verifies that the final coordinates can be multiplied to produce the correct position product.

## Key Learning Outcomes

Through this project, I strengthened my understanding of:

- **Object-Oriented Programming:** Designing classes and records to represent application data and behavior.
- **Algebraic Data Types:** Using sealed interfaces and records to model a defined family of commands.
- **Pattern Matching:** Implementing exhaustive switch expressions using type and record patterns.
- **Immutable Object Design:** Returning updated objects rather than modifying existing instances.
- **Sequential Processing:** Applying multiple commands in order to calculate a final state.
- **Compiler-Assisted Validation:** Understanding how exhaustive switches help identify unhandled command types.
- **Java Records vs. Classes:** Exploring alternative approaches to representing structured data.

## Project Context

Developed as part of **HCDD 311 at Pennsylvania State University during Fall 2026**, this assignment focused on modern Java programming concepts, immutable object design, and command-based state transformations.

The project provided a foundation for working with sealed interfaces, records, and pattern matching in more advanced Java applications.
