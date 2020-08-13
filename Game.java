package com.Planes;

public class Game {
    private final Player player1, player2;
    private boolean turn = true;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void addPlane(Player player, Pair<Integer, Integer> coordinates, char direction) throws PlaneCollideException {
        Plane plane = new Plane(coordinates, direction);
        player.addPlane(plane);
        player.addOnBoard();
    }

    public Player whichTurn() {
        return !turn ? getPlayer1() : getPlayer2();
    }

    public boolean getTurn() {
        return turn;
    }

    public void switchTurn() {
        turn = !turn;
    }

    public int numberOfPlanes(Player player) {
        return player.countPlanes();
    }

    public String attack(Player player, Pair<Integer, Integer> coordinates) throws OutOfRange {
        int nrPlanes = player.countPlanes();
        if (player.getAttacked(coordinates))
            if (nrPlanes > player.countPlanes())
                return "destroyed";
            else return "hit";
        else return "missed";
    }
}
