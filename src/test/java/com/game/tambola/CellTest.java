package com.game.tambola;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cell Test Suite")
class CellTest {
    private final Integer DEFAULT_VALUE = 10;
    private final int DEFAULT_ROW = 0;
    private final int DEFAULT_COLUMN = 1;

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {
        @Test
        @DisplayName("Should create cell with valid parameters")
        void shouldCreateCellWithValidParameters() {
            Cell newCell = new Cell(DEFAULT_VALUE, DEFAULT_ROW, DEFAULT_COLUMN);

            assertAll(
                    () -> assertEquals(DEFAULT_VALUE, newCell.getValue()),
                    () -> assertEquals(DEFAULT_ROW, newCell.getRowId()),
                    () -> assertEquals(DEFAULT_COLUMN, newCell.getColumnId()),
                    () -> assertFalse(newCell.isCrossed())
            );
        }

        @DisplayName("Should accept null value")
        void shouldAcceptNullValue() {
            Cell nullValueCell = new Cell(null, DEFAULT_ROW, DEFAULT_COLUMN);
            assertNull(nullValueCell.getValue());
        }
    }

    @Nested
    @DisplayName("Getter Tests")
    class GetterTests {

        @Test
        @DisplayName("getValue should return correct value")
        void getValueShouldReturnCorrectValue() {
            Cell cell = new Cell(DEFAULT_VALUE, DEFAULT_ROW, DEFAULT_COLUMN);
            assertEquals(DEFAULT_VALUE, cell.getValue());
        }

        @Test
        @DisplayName("getRowId should return correct row")
        void getRowIdShouldReturnCorrectRow() {
            Cell cell = new Cell(DEFAULT_VALUE, DEFAULT_ROW, DEFAULT_COLUMN);
            assertEquals(DEFAULT_ROW, cell.getRowId());
        }

        @Test
        @DisplayName("getColumnId should return correct column")
        void getColumnIdShouldReturnCorrectColumn() {
            Cell cell = new Cell(DEFAULT_VALUE, DEFAULT_ROW, DEFAULT_COLUMN);
            assertEquals(DEFAULT_COLUMN, cell.getColumnId());
        }

        @Test
        @DisplayName("isCrossed should return false by default")
        void isCrossedShouldReturnFalseByDefault() {
            Cell cell = new Cell(DEFAULT_VALUE, DEFAULT_ROW, DEFAULT_COLUMN);
            assertFalse(cell.isCrossed());
        }
    }

    @Nested
    @DisplayName("Setter Tests")
    class SetterTests {

        @Test
        @DisplayName("setCrossed should update crossed state")
        void setCrossedShouldUpdateCrossedState() {
            Cell cell = new Cell(DEFAULT_VALUE, DEFAULT_ROW, DEFAULT_COLUMN);
            assertFalse(cell.isCrossed());

            cell.cross();
            assertTrue(cell.isCrossed());
        }
    }
}