package com.game.tambola;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("GameEngine Test Suite")
class GameEngineTest {
    private GameEngine gameEngine;
    private Player playerMock;
    private Ticket ticketMock;

    @BeforeEach
    void setUp() {
        gameEngine = new GameEngine();
        playerMock = mock(Player.class);
        ticketMock = mock(Ticket.class);

        when(playerMock.getTicket()).thenReturn(ticketMock);
        when(playerMock.getName()).thenReturn("John");
    }

    @Nested
    @DisplayName("Tests for Adding and Managing Players")
    class PlayerManagementTests {

        @Test
        @DisplayName("Add player to GameEngine")
        void testAddPlayer() {
            gameEngine.addPlayer(playerMock);
            assertEquals(1, gameEngine.getPlayers().size(), "GameEngine should have 1 player after adding");
        }
    }

    @Nested
    @DisplayName("Tests for Cross Number Functionality")
    class CrossNumberTests {

        @Test
        @DisplayName("Cross a number in all players' tickets")
        void testCrossNumber() {
            gameEngine.addPlayer(playerMock);
            gameEngine.crossNumber(5);

            verify(ticketMock, times(1)).crossCell(5);
        }
    }

    @Nested
    @DisplayName("Tests for Claim Validation")
    class ClaimValidationTests {

        @Test
        @DisplayName("Validate EARLY_FIVE claim success")
        void testValidateEarlyFiveClaimSuccess() {
            gameEngine.addPlayer(playerMock);
            when(ticketMock.getCrossedCounter()).thenReturn(5);

            gameEngine.validateClaims();
            verify(playerMock, times(1)).addClaim(any(Claim.class));
            System.out.println(playerMock.getClaims());
        }

        @Test
        @DisplayName("Validate EARLY_FIVE claim rejection")
        void testValidateEarlyFiveClaimFailure() {
            gameEngine.addPlayer(playerMock);
            when(ticketMock.getCrossedCounter()).thenReturn(3);

            gameEngine.validateClaims();
            verify(playerMock, never()).addClaim(any(Claim.class));
        }

        @Test
        @DisplayName("Validate FULL_HOUSE claim success")
        void testValidateFullHouseClaimSuccess() {
            gameEngine.addPlayer(playerMock);
            when(ticketMock.getCrossedCounter()).thenReturn(15);

            gameEngine.validateClaims();
            verify(playerMock, times(2)).addClaim(any(Claim.class));
        }

        @Test
        @DisplayName("Validate FULL_HOUSE claim rejection")
        void testValidateFullHouseClaimFailure() {
            gameEngine.addPlayer(playerMock);
            when(ticketMock.getCrossedCounter()).thenReturn(10);

            gameEngine.validateClaims();
            verify(playerMock, times(1)).addClaim(any(Claim.class));
        }

        @Test
        @DisplayName("Validate TOP_LINE claim success")
        void testValidateTopLineClaimSuccess() {
            gameEngine.addPlayer(playerMock);
            when(ticketMock.getCrossedCounter()).thenReturn(5);
            when(ticketMock.getTopLineCounter()).thenReturn(5);

            gameEngine.validateClaims();
            verify(playerMock, times(2)).addClaim(any(Claim.class));
        }

        @Test
        @DisplayName("Validate TOP_LINE claim rejection")
        void testValidateTopLineClaimFailure() {
            gameEngine.addPlayer(playerMock);
            when(ticketMock.getTopLineCounter()).thenReturn(3);

            gameEngine.validateClaims();
            verify(playerMock, never()).addClaim(any(Claim.class));
        }

        @Test
        @DisplayName("Validate MIDDLE_LINE claim success")
        void testValidateMiddleLineClaimSuccess() {
            gameEngine.addPlayer(playerMock);
            when(ticketMock.getCrossedCounter()).thenReturn(5);
            when(ticketMock.getMiddleLineCounter()).thenReturn(5);

            gameEngine.validateClaims();
            verify(playerMock, times(2)).addClaim(any(Claim.class));
        }

        @Test
        @DisplayName("Validate MIDDLE_LINE claim rejection")
        void testValidateMiddleLineClaimFailure() {
            gameEngine.addPlayer(playerMock);
            when(ticketMock.getTopLineCounter()).thenReturn(3);

            gameEngine.validateClaims();
            verify(playerMock, never()).addClaim(any(Claim.class));
        }

        @Test
        @DisplayName("Validate BOTTOM_LINE claim success")
        void testValidateBottomLineClaimSuccess() {
            gameEngine.addPlayer(playerMock);
            when(ticketMock.getCrossedCounter()).thenReturn(5);
            when(ticketMock.getBottomLineCounter()).thenReturn(5);

            gameEngine.validateClaims();
            verify(playerMock, times(2)).addClaim(any(Claim.class));
        }

        @Test
        @DisplayName("Validate MIDDLE_LINE claim rejection")
        void testValidateBttomLineClaimFailure() {
            gameEngine.addPlayer(playerMock);
            when(ticketMock.getBottomLineCounter()).thenReturn(3);

            gameEngine.validateClaims();
            verify(playerMock, never()).addClaim(any(Claim.class));
        }
    }

    @Nested
    @DisplayName("Tests for Game Results")
    class ResultTests {

        @Test
        @DisplayName("Print game results with sorted claims")
        void testResultPrintsSortedClaims() {
            Claim claimMock1 = mock(Claim.class);
            Claim claimMock2 = mock(Claim.class);

            when(claimMock1.getTimeOfClaim()).thenReturn(LocalDateTime.now().minusMinutes(5));
            when(claimMock2.getTimeOfClaim()).thenReturn(LocalDateTime.now());
            when(playerMock.getClaims()).thenReturn(List.of(claimMock1, claimMock2));

            gameEngine.addPlayer(playerMock);

            gameEngine.result();

            verify(claimMock1, times(1)).print();
            verify(claimMock2, times(1)).print();
        }
    }
}