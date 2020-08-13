package com.Planes;

public class Main {

    public static void main(String[] args) {
        Board board = new Board(15, 15);
        Board board2 = new Board(15, 15);
        Player player1 = new Player(board);
        Player player2 = new Player(board2);
        Game game = new Game(player1, player2);
        Console console = new Console(game);
        console.run();
    }
}
