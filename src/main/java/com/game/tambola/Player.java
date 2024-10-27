package com.game.tambola;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
public class Player {
    private final String name;
    private final Ticket ticket;
    private final List<Claim> claims = new ArrayList<>();

    public Player(String name) {
        this.name = Objects.requireNonNull(name, "Player name cannot be null");
        this.ticket = new Ticket();
        this.ticket.print(name);
    }

    public List<Claim> addClaim(Claim claim) {
        claims.add(Objects.requireNonNull(claim, "Claim cannot be null"));
        return Collections.unmodifiableList(claims);
    }
}
