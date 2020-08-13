package com.Planes;

public class Board implements Cloneable {
    private final char[][] board = new char[30][30];
    private final int WIDTH;
    private final int HEIGHT;

    public Board(int m, int n) {
        WIDTH = m + 1;
        HEIGHT = n + 1;
        for (int i = 1; i <= WIDTH; i++) {
            for (int j = 0; j <= HEIGHT; j++) {
                board[i][j] = j == 0 ? (char)(i + 64) : ' ';
            }
        }
    }

    public Board clone() throws CloneNotSupportedException {
        return (Board)super.clone();
    }

    private void printFirstLine() {
        System.out.print(' ');
        for (int i = 1; i <= WIDTH; i++)
            System.out.printf("%d ", i);
        System.out.println();
    }

    public void printBoard() {
        printFirstLine();
        for (int i = 1; i <= WIDTH; i++) {
            for (int j = 0; j <= HEIGHT; j++)
                System.out.printf("%c ", board[i][j]);
            System.out.println();
        }
    }

    public void placeOnBoard(int i, int j, char c) throws OutOfRange {
        if (i <= 0 || i >= WIDTH || j <= 0 || j >= HEIGHT)
            throw new OutOfRange("out of range");
        board[i][j] = c;
    }

    public void placePlane(Plane plane) {
        try {
            placeOnBoard(plane.getHead().x, plane.getHead().y, 'H');
            for (var i : plane.getBody())
                placeOnBoard(i.x, i.y, 'B');
        } catch (OutOfRange err) {
            System.out.println(err.getLocalizedMessage());
        }
    }
}
