package com.game.tambola;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
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

    public void print() {
        System.out.println("🏆" + this.player.getName() + " won " + claimType + " claim at " + timeOfClaim);
    }
}
