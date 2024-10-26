package com.game.tambola;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ticket Test Suite")
class TicketTest {

    @Nested
    @DisplayName("Ticket Creation Tests")
    class TicketCreationTests {

        @Test
        @DisplayName("Should create ticket with correct dimensions")
        void shouldCreateTicketWithCorrectDimensions() {
            Ticket ticket = new Ticket();
            Cell[][] cells = ticket.getCells();

            assertEquals(3, cells.length, "Should have 3 rows");
            assertEquals(9, cells[0].length, "Should have 9 columns");
        }

        @Test
        @DisplayName("Should have exactly 15 numbers in ticket")
        void shouldHaveExactlyFifteenNumbers() {
            Ticket ticket = new Ticket();
            long numbersCount = countNonNullCells(ticket);
            assertEquals(15, numbersCount, "Ticket should contain exactly 15 numbers");
        }

        @Test
        @DisplayName("Should have exactly 5 numbers per row")
        void shouldHaveExactlyFiveNumbersPerRow() {
            Ticket ticket = new Ticket();

            for (int i = 0; i < 3; i++) {
                final int row = i;
                long numbersInRow = IntStream.range(0, 9)
                        .mapToObj(col -> ticket.getCells()[row][col])
                        .filter(cell -> cell.getValue() != null)
                        .count();
                assertEquals(5, numbersInRow, "Row " + i + " should contain exactly 5 numbers");
            }
        }

        @Test
        @DisplayName("Should have numbers in correct range for each column")
        void shouldHaveNumbersInCorrectRangeForEachColumn() {
            Ticket ticket = new Ticket();

            for (int j = 0; j < 9; j++) {
                for (int i = 0; i < 3; i++) {
                    Cell cell = ticket.getCells()[i][j];

                    if (cell.getValue() != null) {
                        int minRange = j * 10 + 1;
                        int maxRange = j * 10 + 9;

                        assertTrue(
                                cell.getValue() >= minRange && cell.getValue() <= maxRange,
                                String.format("Number %d in column %d should be between %d and %d",
                                        cell.getValue(), j, minRange, maxRange)
                        );
                    }
                }
            }
        }

        @Test
        @DisplayName("Should have unique numbers across ticket")
        void shouldHaveUniqueNumbersAcrossTicket() {
            Ticket ticket = new Ticket();
            Set<Integer> numbers = new HashSet<>();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    Cell[][] cells = ticket.getCells();

                    if (cells[i][j].getValue() != null) {
                        assertTrue(numbers.add(cells[i][j].getValue()),
                                "Number " + cells[i][j].getValue() + " appears multiple times");
                    }
                }
            }

            assertEquals(15, numbers.size(), "Should have 15 unique numbers");
        }

        // Helper methods
        private long countNonNullCells(Ticket ticket) {
            long count = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    if (ticket.getCells()[i][j].getValue() != null) {
                        count++;
                    }
                }
            }
            return count;
        }
    }

    @Nested
    @DisplayName("Cell Crossing Tests")
    class CellCrossingTests {
        @Test
        @DisplayName("Should cross cell when number exists")
        void shouldCrossCellWhenNumberExists() {
            Ticket ticket = new Ticket();

            // Find first non-null cell
            Cell firstCell = findFirstNonNullCell(ticket);
            assertNotNull(firstCell, "Should have at least one number in ticket");

            Integer numberToCross = firstCell.getValue();
            ticket.crossCell(numberToCross);

            assertTrue(firstCell.isCrossed(), "Cell should be crossed");
            assertEquals(1, ticket.getCrossedCounter(), "Crossed counter should be incremented");
        }

        @Test
        @DisplayName("Should not cross cell when number doesn't exist")
        void shouldNotCrossCellWhenNumberDoesntExist() {
            Ticket ticket = new Ticket();
            int nonExistentNumber = 999;
            ticket.crossCell(nonExistentNumber);

            assertEquals(0, ticket.getCrossedCounter(), "Crossed counter should not increment");
        }

        @Test
        @DisplayName("Should increment line counters correctly")
        void shouldIncrementLineCountersCorrectly() {
            Ticket ticket = new Ticket();
            // Cross a number in each row
            Cell topCell = findFirstNonNullCellInRow(ticket, 0);
            Cell middleCell = findFirstNonNullCellInRow(ticket, 1);
            Cell bottomCell = findFirstNonNullCellInRow(ticket, 2);

            ticket.crossCell(topCell.getValue());
            assertEquals(1, ticket.getTopLineCounter(), "Top line counter should increment");

            ticket.crossCell(middleCell.getValue());
            assertEquals(1, ticket.getMiddleLineCounter(), "Middle line counter should increment");

            ticket.crossCell(bottomCell.getValue());
            assertEquals(1, ticket.getBottomLineCounter(), "Bottom line counter should increment");
        }

        // Helper methods
        private Cell findFirstNonNullCell(Ticket ticket) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    Cell[][] cells = ticket.getCells();

                    if (cells[i][j].getValue() != null) {
                        return cells[i][j];
                    }
                }
            }
            return null;
        }

        private Cell findFirstNonNullCellInRow(Ticket ticket, int row) {
            for (int j = 0; j < 9; j++) {
                Cell[][] cells = ticket.getCells();

                if (cells[row][j].getValue() != null) {
                    return cells[row][j];
                }
            }
            return null;
        }
    }

    @Nested
    @DisplayName("Ticket Printing Tests")
    class TicketPrintingTests {
        @Test
        @DisplayName("Should print ticket without throwing exception")
        void shouldPrintTicketWithoutThrowingException() {
            Ticket ticket = new Ticket();
            assertDoesNotThrow(() -> ticket.print("Test Player"));
        }
    }
}