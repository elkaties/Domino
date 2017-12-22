package com.Studying;

import java.util.Comparator;

public class TileComparator implements Comparator<Tile> {
    @Override
    public int compare(Tile o1, Tile o2) {
        if (o1.a > o2.a) {
            return 1;
        }
        if (o1.a < o2.a) {
            return -1;
        }
        if (o1.b > o2.b) {
            return 1;
        }
        if (o1.b < o2.b) {
            return -1;
        }
        return 0;
    }
}
