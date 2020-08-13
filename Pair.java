package com.Planes;

public class Pair<X, Y> {
    X x;
    Y y;

    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object v) {
        if (v instanceof Pair) {
            Pair<X, Y> ptr = (Pair<X, Y>) v;
            return ptr.x == this.x && ptr.y == this.y;
        }
        return false;
    }
}
