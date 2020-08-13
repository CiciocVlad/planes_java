package com.Planes;

import java.util.Vector;

public class Plane {

    private final Pair<Integer, Integer> head;
    private final Vector<Pair<Integer, Integer>> body = new Vector<>();

    public Plane(Pair<Integer, Integer> head, char direction) {
        this.head = head;
        createBody(direction);
    }

    private boolean containsPair(Pair<Integer, Integer> pair) {
        return body.contains(pair);
    }

    public Pair<Integer, Integer> getHead() {
        return head;
    }

    public Vector<Pair<Integer, Integer>> getBody() {
        return body;
    }

    public Pair<Integer, Pair<Integer, Integer>> equalCoords(Pair<Integer, Integer> coordonates) {
        if (this.head.equals(coordonates))
            return new Pair<>(1, this.head);
        return containsPair(coordonates) ? new Pair<>(2, coordonates) : new Pair<>(0, new Pair<>(0, 0));
    }

    public boolean crash(Plane other) {
        if (this.equalCoords(other.getHead()).x > 0)
            return true;
        for (var i : other.getBody())
            if (this.equalCoords(i).x > 0)
                return true;
        return false;
    }

    private void createBody(char direction) {
        if (direction == 'N') {
            for (int i = head.y - 2; i <= head.y + 2; i++)
                body.add(new Pair<>(head.x - 1, i));
            body.add(new Pair<>(head.x - 2, head.y));
            for (int i = head.y - 1; i <= head.y + 1; i++)
                body.add(new Pair<>(head.x - 3, i));
        }
        if (direction == 'S') {
            for (int i = head.y - 2; i <= head.y + 2; i++)
                body.add(new Pair<>(head.x + 1, i));
            body.add(new Pair<>(head.x + 2, head.y));
            for (int i = head.y - 1; i <= head.y + 1; i++)
                body.add(new Pair<>(head.x + 3, i));
        }
        if (direction == 'E') {
            for (int i = head.x - 2; i <= head.x + 2; i++)
                body.add(new Pair<>(i, head.y + 1));
            body.add(new Pair<>(head.x, head.y + 2));
            for (int i = head.x - 1; i <= head.x + 1; i++)
                body.add(new Pair<>(i, head.y + 3));
        }
        if (direction == 'W') {
            for (int i = head.x - 2; i <= head.x + 2; i++)
                body.add(new Pair<>(i, head.y - 1));
            body.add(new Pair<>(head.x, head.y - 2));
            for (int i = head.x - 1; i <= head.x + 1; i++)
                body.add(new Pair<>(i, head.y - 3));
        }
    }
}
