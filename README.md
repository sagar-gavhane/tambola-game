# Tambola Claim Validator

## Overview

This project implements a claim validation system for the Tambola game. Players receive tickets with numbers and can
make claims based on crossed numbers as they are announced. The system checks if these claims are valid according to
specific game rules.

## Features

- Players can register and receive tickets with numbers.
- Players can make claims such as "Early Five," "Full House," and various line claims.
- The system validates claims based on the ticket's crossed numbers.
- Outputs validation results to the console.

## Technologies Used

- Java (JDK 17 or higher)
- JUnit 5 (for testing)
- Mockito (for testing)
- Maven (for building)

## Class Overview

### 1. **Player**

- **Data Members**:
    - `String name`: The name of the player.
    - `Ticket ticket`: The player's ticket.
    - `List<Claim> claims`: List of claims made by the player.

- **Functions**:
    - `Player(String name)`: Initializes the player with a name and a new ticket.
    - `void addClaim(Claim claim)`: Adds a claim to the player's list.
    - `String getName()`: Returns the player's name.
    - `List<Claim> getClaims()`: Returns the list of claims.
    - `Ticket getTicket()`: Returns the player's ticket.

### 2. **Ticket**

- **Data Members**:
    - `Cell[][] cells`: A 3x9 grid of cells representing the ticket.
    - `Map<Integer, Cell> map`: Maps drawn numbers to their respective cells.
    - `int crossedCounter`: Total crossed numbers.
    - `int topLineCounter`: Crossed numbers in the top line.
    - `int middleLineCounter`: Crossed numbers in the middle line.
    - `int bottomLineCounter`: Crossed numbers in the bottom line.

- **Functions**:
    - `Ticket()`: Initializes the ticket and populates it with random numbers.
    - `void crossCell(int drawnNumber)`: Marks a number as crossed.
    - `boolean isWinningClaim(ClaimType claimType)`: Checks if the player's claim is valid based on crossed numbers.

### 3. **Claim**

- **Data Members**:
    - `ClaimType claimType`: The type of claim made by the player.
    - `LocalDateTime timeOfClaim`: Timestamp when the claim was made.
    - `Player player`: The player who made the claim.

- **Functions**:
    - `Claim(Player player, ClaimType claimType)`: Constructor to initialize a claim.
    - `void print()`: Prints the claim details.

### 4. **ClaimValidator**

- **Functions**:
    - `void validateClaim(Player player, ClaimType claimType)`: Validates the claim made by a player based on their
      ticket.

### 5. **NumberDrawer**

- **Functions**:
    - `int draw(int min, int max)`: Draws a random number within a specified range.

### 6. **Cell**

- **Data Members**:
    - `Integer value`: The value of the cell.
    - `boolean isCrossed`: Indicates if the cell is crossed.
    - `Integer rowId`: The row index of the cell.
    - `Integer columnId`: The column index of the cell.

- **Functions**:
    - `Cell(Integer value, Integer rowId, Integer columnId)`: Initializes the cell.
    - `void cross()`: Marks the cell as crossed.

### 7. **ClaimType (Enum)**

- **Values**: `EARLY_FIVE`, `FULL_HOUSE`, `TOP_LINE`, `MIDDLE_LINE`, `BOTTOM_LINE`

## Installation

1. **Clone the repository**:
   ```bash
   git clone git@github.com:sagar-gavhane/tambola-game.git
   cd tambola-claim-validator
   ```
2. Compile the project: Ensure you have Java installed, and compile the Java files:

```bash
javac -d out src/**/*.java
```

3. Run the project: Execute the main class to start the game logic:

```bash
java -cp out Main
```

## Testing

To run tests, ensure you have JUnit 5 set up in your project. You can run the tests using your IDE's built-in test
runner or via command line.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue for discussion.

