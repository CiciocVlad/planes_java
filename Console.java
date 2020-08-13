package com.Planes;

import java.util.Scanner;

public class Console {

    private final Game game;
    private final Scanner scanner = new Scanner(System.in);

    public Console(Game game) {
        this.game = game;
    }

    public void placePlane(Player player) {
        try {
            System.out.print("coordinates head: ");
            char horizontal = scanner.next().charAt(0);
            int vertical = scanner.nextInt();
            Pair<Integer, Integer> head = new Pair<>(horizontal - 64, vertical);
            System.out.print("direction: ");
            char direction = scanner.next().charAt(0);
            game.addPlane(player, head, direction);
        } catch (PlaneCollideException err) {
            System.out.println(err.getLocalizedMessage());
        }
    }

    public void printBoard(Player player) {
        player.getBoard().printBoard();
    }

    public void placePlaneFirstPlayer() {
        while (game.getPlayer1().countPlanes() < 3) {
            printBoard(game.getPlayer1());
            placePlane(game.getPlayer1());
        }
        printBoard(game.getPlayer1());
    }

    public void placePlaneSecondPlayer() {
        while (game.getPlayer2().countPlanes() < 3) {
            printBoard(game.getPlayer2());
            placePlane(game.getPlayer2());
        }
        printBoard(game.getPlayer2());
    }

    public Pair<Integer, Integer> attack() {
        System.out.print("Enter attack coordinates: ");
        char x = scanner.next().charAt(0);
        int y = scanner.nextInt();
        return new Pair<>(x - 64, y);
    }

    public void run() {
        System.out.println("Player 1 deploys");
        placePlaneFirstPlayer();
        System.out.println("Player 2 deploys");
        placePlaneSecondPlayer();
        while (game.numberOfPlanes(game.getPlayer1()) > 0 && game.numberOfPlanes(game.getPlayer2()) > 0) {
            try {
                System.out.printf("Player %d attacks\n", game.getTurn() ? 1 : 2);
                System.out.println("Your board");
                game.switchTurn();
                printBoard(game.whichTurn());
                game.switchTurn();
                System.out.println(game.attack(game.whichTurn(), attack()));
                game.switchTurn();
            } catch (OutOfRange err) {
                System.out.println(err.getLocalizedMessage());
            }
        }
        if (game.numberOfPlanes(game.getPlayer1()) == 0)
            System.out.println("Player 2 wins!");
        else System.out.println("Player 1 wins!");
    }
}
