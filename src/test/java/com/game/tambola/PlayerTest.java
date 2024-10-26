package com.game.tambola;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("John");
    }

    @Nested
    @DisplayName("Constructor tests")
    class ConstructorTests {

        @Test
        @DisplayName("Player name is correctly set and retrieved")
        void testGetName() {
            assertEquals("John", player.getName(), "Player name should be 'John'");
        }

        @Test
        @DisplayName("Player's initial ticket is not null")
        void testGetTicket() {
            assertNotNull(player.getTicket(), "Player's ticket should not be null on initialization");
        }

        @Test
        @DisplayName("Player's claims list is empty upon initialization")
        void testInitialClaimsListEmpty() {
            assertTrue(player.getClaims().isEmpty(), "The claims list should be empty initially");
        }
    }

    @Nested
    @DisplayName("Tests for Adding Claims")
    class ClaimAdditionTests {
        @Test
        @DisplayName("Test adding a single claim to Player's claims")
        void testAddSingleClaim() {
            Claim claim = new Claim(player, ClaimType.FULL_HOUSE);
            List<Claim> claims = player.addClaim(claim);

            assertEquals(1, claims.size(), "There should be one claim in the claims list");
            assertTrue(claims.contains(claim), "The claim list should contain the added claim");
        }

        @Test
        @DisplayName("Test adding multiple claims to Player's claims")
        void testAddMultipleClaims() {
            Claim claim1 = new Claim(player, ClaimType.FULL_HOUSE);
            Claim claim2 = new Claim(player, ClaimType.EARLY_FIVE);

            player.addClaim(claim1);
            List<Claim> claims = player.addClaim(claim2);

            assertEquals(2, claims.size(), "There should be two claims in the claims list");
            assertTrue(claims.contains(claim1), "The claim list should contain claim1");
            assertTrue(claims.contains(claim2), "The claim list should contain claim2");
        }

        @Test
        @DisplayName("Adding a null claim throws NullPointerException")
        void testAddNullClaim() {
            assertThrows(NullPointerException.class, () -> player.addClaim(null), "Adding a null claim should throw NullPointerException");
        }
    }

    @Nested
    @DisplayName("Tests for Retrieving Claims")
    class RetrieveClaimsTests {
        @Test
        @DisplayName("Get claims after multiple additions returns correct list size")
        void testGetClaimsAfterAdditions() {
            Claim claim1 = new Claim(player, ClaimType.FULL_HOUSE);
            Claim claim2 = new Claim(player, ClaimType.EARLY_FIVE);
            player.addClaim(claim1);
            player.addClaim(claim2);

            List<Claim> claims = player.getClaims();
            assertEquals(2, claims.size(), "The claims list should contain exactly 2 claims");
        }
    }
}