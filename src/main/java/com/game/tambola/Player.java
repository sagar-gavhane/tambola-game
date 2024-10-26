package com.game.tambola;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    public String getName() {
        return name;
    }

    public List<Claim> getClaims() {
        return Collections.unmodifiableList(claims);
    }

    public Ticket getTicket() {
        return ticket;
    }
}
