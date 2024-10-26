package com.game.tambola;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Claim Test Suite")
class ClaimTest {

    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        mockPlayer = mock(Player.class);
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create claim with valid parameters")
        void shouldCreateClaimWithValidParameters() {
            when(mockPlayer.getName()).thenReturn("TestPlayer");

            Claim claim = new Claim(mockPlayer, ClaimType.FULL_HOUSE);

            assertAll(
                    () -> assertEquals(ClaimType.FULL_HOUSE, claim.getClaimType()),
                    () -> assertThat(claim.getTimeOfClaim(), instanceOf(LocalDateTime.class))
            );
        }

        @Test
        @DisplayName("Should handle null player")
        void shouldHandleNullPlayer() {
            assertThrows(NullPointerException.class,
                    () -> new Claim(null, ClaimType.FULL_HOUSE),
                    "Should throw NullPointerException when player is null");
        }

        @Test
        @DisplayName("Should handle null claim type")
        void shouldHandleNullClaimType() {
            assertThrows(NullPointerException.class,
                    () -> new Claim(mockPlayer, null),
                    "Should throw NullPointerException when claim type is null");
        }
    }

    @Nested
    @DisplayName("Getter Tests")
    class GetterTests {

        @ParameterizedTest
        @EnumSource(ClaimType.class)
        @DisplayName("Should return correct claim type")
        void shouldReturnCorrectClaimType(ClaimType claimType) {
            Claim claim = new Claim(mockPlayer, claimType);
            assertEquals(claimType, claim.getClaimType());
        }

        @Test
        @DisplayName("Should return correct time of claim")
        void shouldReturnCorrectTimeOfClaim() {
            Claim claim = new Claim(mockPlayer, ClaimType.FULL_HOUSE);
            assertThat(claim.getTimeOfClaim(), instanceOf(LocalDateTime.class));
        }
    }

    @Nested
    @DisplayName("Print Tests")
    class PrintTests {
        private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        private final PrintStream standardOut = System.out;

        @BeforeEach
        void setUp() {
            System.setOut(new PrintStream(outputStreamCaptor));
        }

        @Test
        @DisplayName("Should print claim details correctly")
        void shouldPrintClaimDetailsCorrectly() {
            when(mockPlayer.getName()).thenReturn("TestPlayer");

            Claim claim = new Claim(mockPlayer, ClaimType.FULL_HOUSE);
            claim.print();

            String expectedOutput = "🏆TestPlayer won FULL_HOUSE claim at " + claim.getTimeOfClaim();
            assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

            verify(mockPlayer).getName();
        }

        @Test
        @DisplayName("Should handle player with empty name")
        void shouldHandlePlayerWithEmptyName() {
            when(mockPlayer.getName()).thenReturn("");

            Claim claim = new Claim(mockPlayer, ClaimType.FULL_HOUSE);
            claim.print();

            String expectedOutput = "🏆 won FULL_HOUSE claim at " + claim.getTimeOfClaim();
            assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        }

        @org.junit.jupiter.api.AfterEach
        void tearDown() {
            System.setOut(standardOut);
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCases {

        @Test
        @DisplayName("Should handle player with null name")
        void shouldHandlePlayerWithNullName() {
            when(mockPlayer.getName()).thenReturn(null);

            Claim claim = new Claim(mockPlayer, ClaimType.FULL_HOUSE);

            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));

            assertDoesNotThrow(() -> claim.print(),
                    "Should not throw exception when player name is null");

            System.setOut(System.out);
        }

        @Test
        @DisplayName("Should handle long player names")
        void shouldHandleLongPlayerNames() {
            String longName = "a".repeat(1000);
            when(mockPlayer.getName()).thenReturn(longName);

            Claim claim = new Claim(mockPlayer, ClaimType.FULL_HOUSE);
            assertDoesNotThrow(() -> claim.print(),
                    "Should handle very long player names");
        }
    }
}