package com.Studying;

public class Tile {
    int a;
    int b;
    boolean inv;

    Tile(int a, int b, boolean inv) {
        this.a = a;
        this.b = b;
        this.inv = inv;
    }

    int getNext() {
        if (inv == false) {
            return b;
        }
        else {
            return a;
        }
    }
}
