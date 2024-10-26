package com.game.tambola;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("NumberDrawer Test Suite")
class NumberDrawerTest {
    @Nested
    @DisplayName("Basic Drawing Tests")
    class BasicDrawingTests {

        @Test
        @DisplayName("Should draw number within specified range")
        void shouldDrawNumberWithinSpecifiedRange() {
            NumberDrawer drawer = new NumberDrawer();
            int min = 1;
            int max = 10;

            int drawn = drawer.draw(min, max, 0);

            assertTrue(drawn >= min && drawn < max,
                    "Drawn number " + drawn + " should be between " + min + " (inclusive) and " + max + " (exclusive)");
        }

        @Test
        @DisplayName("Should not return duplicate numbers in consecutive draws")
        void shouldNotReturnDuplicateNumbers() {
            NumberDrawer drawer = new NumberDrawer();
            int min = 1;
            int max = 10;
            Set<Integer> numbers = new HashSet<>();

            // Draw 5 numbers
            for (int i = 0; i < 5; i++) {
                int drawn = drawer.draw(min, max, 0);
                assertTrue(numbers.add(drawn), "Number " + drawn + " was already drawn");
            }

            assertEquals(5, numbers.size(), "Should have drawn 5 unique numbers");
        }

        @Test
        @DisplayName("Should draw all possible numbers in range")
        void shouldDrawAllPossibleNumbersInRange() {
            NumberDrawer drawer = new NumberDrawer();
            int min = 1;
            int max = 5;
            Set<Integer> expectedNumbers = IntStream.range(min, max).boxed().collect(Collectors.toSet());
            Set<Integer> drawnNumbers = new HashSet<>();

            // Draw all possible numbers
            for (int i = min; i < max; i++) {
                drawnNumbers.add(drawer.draw(min, max, 0));
            }

            assertEquals(expectedNumbers, drawnNumbers, "Should draw all numbers in range exactly once");
        }
    }

    @Nested
    @DisplayName("Edge Cases and Error Conditions")
    class EdgeCasesAndErrorConditions {

        private static Stream<Arguments> provideInvalidRanges() {
            return Stream.of(
                    Arguments.of(10, 5, 0),  // min > max
                    Arguments.of(5, 5, 0),   // min == max
                    Arguments.of(1, 2, 5)    // retries > max
            );
        }

        @Test
        @DisplayName("Should throw exception when no more unique numbers can be drawn")
        void shouldThrowExceptionWhenNoMoreUniqueNumbers() {
            NumberDrawer drawer = new NumberDrawer();
            int min = 1;
            int max = 3;

            // Draw all possible numbers first
            for (int i = min; i < max; i++) {
                drawer.draw(min, max, 0);
            }

            // Try to draw one more number
            assertThrows(RuntimeException.class, () -> drawer.draw(min, max, max + 1),
                    "Should throw RuntimeException when no more unique numbers can be drawn");
        }

        @ParameterizedTest
        @MethodSource("provideInvalidRanges")
        @DisplayName("Should handle invalid ranges appropriately")
        void shouldHandleInvalidRanges(int min, int max, int retries) {
            NumberDrawer drawer = new NumberDrawer();

            assertThrows(RuntimeException.class, () -> drawer.draw(min, max, retries),
                    "Should throw RuntimeException for invalid range");
        }
    }

    @Nested
    @DisplayName("Retry Mechanism Tests")
    class RetryMechanismTests {

        @Test
        @DisplayName("Should successfully retry when duplicate number is drawn")
        void shouldSuccessfullyRetryWhenDuplicateNumberIsDrawn() {
            NumberDrawer drawer = new NumberDrawer();
            int min = 1;
            int max = 3;

            // Draw first number
            int first = drawer.draw(min, max, 0);

            // Draw second number - should be different due to retry mechanism
            int second = drawer.draw(min, max, 0);

            assertNotEquals(first, second, "Second draw should return different number due to retry mechanism");
        }
    }
}