package com.game.tambola;

public class ClaimValidator {
    public static void validateClaim(Player player, ClaimType claimType) {
        Ticket ticket = player.getTicket();

        boolean isClaimValid;

        switch (claimType) {
            case EARLY_FIVE -> isClaimValid = ticket.getCrossedCounter() >= 5;
            case FULL_HOUSE -> isClaimValid = ticket.getCrossedCounter() == 15;
            case TOP_LINE -> isClaimValid = ticket.getTopLineCounter() == 5;
            case MIDDLE_LINE -> isClaimValid = ticket.getMiddleLineCounter() == 5;
            case BOTTOM_LINE -> isClaimValid = ticket.getBottomLineCounter() == 5;
            default -> throw new IllegalArgumentException("Unknown claim type");
        }

        if (isClaimValid) {
            player.addClaim(new Claim(player, claimType));
            System.out.println("✅" + player.getName() + " " + claimType + " claim accepted.");
        } else {
            System.out.println("❌" + player.getName() + " " + claimType + " claim rejected.");
        }
    }
}
