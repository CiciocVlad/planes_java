package com.Planes;

import java.util.Vector;

public class Player {

    private final Vector<Plane> planes = new Vector<>();
    private final Board board;

    public Player(Board board) {
        this.board = board;
    }

    private boolean planeExists(Plane plane) {
        for (var i : planes) {
            if (i.getHead() == plane.getHead())
                return true;
            if (plane.getBody().contains(i.getHead()))
                return true;
            if (i.getBody().contains(plane.getHead()))
                return true;
            for (var j : i.getBody())
                if (plane.getBody().contains(j))
                    return true;
        }
        return false;
    }

    public void addPlane(Plane plane) throws PlaneCollideException {
        if (!planeExists(plane))
            planes.add(plane);
        else throw new PlaneCollideException("another plane exists there\ntry again...");
    }

    public void clearPlane(Plane plane) throws OutOfRange {
        board.placeOnBoard(plane.getHead().x, plane.getHead().y, ' ');
        for (var i : plane.getBody())
            board.placeOnBoard(i.x, i.y, ' ');
    }

    public int countPlanes() {
        return planes.size();
    }

    public void addOnBoard() {
        for (var i : planes)
            board.placePlane(i);
    }

    public Board getBoard() {
        return board;
    }

    public boolean getAttacked(Pair<Integer, Integer> coordinates) throws OutOfRange {
        for (var i : planes) {
            if (i.equalCoords(coordinates).x == 1) {
                clearPlane(i);
                planes.remove(i);
                return true;
            }
            else if (i.equalCoords(coordinates).x == 2) {
                System.out.println(i.equalCoords(coordinates).y.x + " " + i.equalCoords(coordinates).y.y);
                board.placeOnBoard(i.equalCoords(coordinates).y.x, i.equalCoords(coordinates).y.y, 'X');
                return true;
            }
        }
        return false;
    }
}
