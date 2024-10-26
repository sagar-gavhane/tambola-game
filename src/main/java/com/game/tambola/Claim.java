package com.game.tambola;

import java.time.LocalDateTime;

public class Claim {
    private final ClaimType claimType;
    private final LocalDateTime timeOfClaim;
    private final Player player;

    public Claim(Player player, ClaimType claimType) {
        if (player == null) {
            throw new NullPointerException("Player cannot be null");
        }

        if (claimType == null) {
            throw new NullPointerException("Claim type cannot be null");
        }

        this.player = player;
        this.claimType = claimType;
        this.timeOfClaim = LocalDateTime.now();
    }

    public ClaimType getClaimType() {
        return claimType;
    }

    public LocalDateTime getTimeOfClaim() {
        return timeOfClaim;
    }

    public void print() {
        System.out.println("🏆" + this.player.getName() + " won " + claimType + " claim at " + timeOfClaim);
    }
}
