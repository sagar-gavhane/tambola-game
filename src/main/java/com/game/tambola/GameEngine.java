package com.game.tambola;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameEngine {
    private final List<Player> players = new ArrayList<>();

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void crossNumber(int announcedNumber) {
        players.forEach(player -> player.getTicket().crossCell(announcedNumber));
    }

    public void validateClaims() {
        players.forEach(player -> {
            for (ClaimType claimType : ClaimType.values()) {
                ClaimValidator.validateClaim(player, claimType);
            }
        });
    }

    public void result() {
        System.out.println("\n👉 Game Results");

        players.stream()
                .flatMap(player -> player.getClaims().stream())
                .sorted(Comparator.comparing(Claim::getTimeOfClaim))
                .forEach(Claim::print);
    }
}
