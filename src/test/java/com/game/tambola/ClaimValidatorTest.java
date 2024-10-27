package com.game.tambola;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ClaimValidatorTest {
    private Player player;
    private Ticket ticket;

    @BeforeEach
    public void setUp() {
        ticket = mock(Ticket.class);
        player = mock(Player.class);
        when(player.getTicket()).thenReturn(ticket);
        when(player.getName()).thenReturn("John");
    }

    @Test
    public void testValidateEarlyFiveClaimAccepted() {
        when(ticket.getCrossedCounter()).thenReturn(5);
        ClaimValidator.validateClaim(player, ClaimType.EARLY_FIVE);
        verify(player).addClaim(any(Claim.class));
    }

    @Test
    public void testValidateEarlyFiveClaimRejected() {
        when(ticket.getCrossedCounter()).thenReturn(4);
        ClaimValidator.validateClaim(player, ClaimType.EARLY_FIVE);
        verify(player, never()).addClaim(any(Claim.class));
    }

    @Test
    public void testValidateFullHouseClaimAccepted() {
        when(ticket.getCrossedCounter()).thenReturn(15);
        ClaimValidator.validateClaim(player, ClaimType.FULL_HOUSE);
        verify(player).addClaim(any(Claim.class));
    }

    @Test
    public void testValidateFullHouseClaimRejected() {
        when(ticket.getCrossedCounter()).thenReturn(14);
        ClaimValidator.validateClaim(player, ClaimType.FULL_HOUSE);
        verify(player, never()).addClaim(any(Claim.class));
    }

    @Test
    public void testValidateTopLineClaimAccepted() {
        when(ticket.getTopLineCounter()).thenReturn(5);
        ClaimValidator.validateClaim(player, ClaimType.TOP_LINE);
        verify(player).addClaim(any(Claim.class));
    }

    @Test
    public void testValidateTopLineClaimRejected() {
        when(ticket.getTopLineCounter()).thenReturn(4);
        ClaimValidator.validateClaim(player, ClaimType.TOP_LINE);
        verify(player, never()).addClaim(any(Claim.class));
    }
}