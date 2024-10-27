package com.game.tambola;

import java.util.*;

public class App {
    public static void main(String[] args) {
        System.out.println("🤝Game started.🤝");

        GameEngine gameEngine = new GameEngine();
        Random random = new Random();

        System.out.println("🎮 Enter player names (press Enter without typing to escape):");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String playerName = scanner.nextLine();

            if (playerName.trim().isEmpty()) {
                break; // Exit the loop if the user just presses Enter
            }

            Player player = new Player(playerName);
            gameEngine.addPlayer(player);

            System.out.println("Added player: " + playerName);
        }

        System.out.println("🎲Drawing numbers...");

        List<Integer> allNumbers = new ArrayList<>();

        for (int i = 1; i <= 90; i++) {
            allNumbers.add(i);
        }

        Collections.shuffle(allNumbers);

        for (int announcedNumber : allNumbers) {
            System.out.println("Announced number:" + announcedNumber);
            gameEngine.crossNumber(announcedNumber);
            gameEngine.validateClaims();
            System.out.println("");
        }

        System.out.println("\n🎰Starting claims...");

        gameEngine.result();
    }
}
