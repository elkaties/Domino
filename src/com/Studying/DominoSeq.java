package com.Studying;

import java.io.FileReader;
import java.util.ArrayList;

public class DominoSeq {
    static ArrayList<Tile> tiles;
    static ArrayList<Tile> maxTiles;
    static ArrayList<Tile> currentTiles;
    static boolean[] use;

    static int getInt(FileReader reader) throws Exception {
        int c;

        while (true) {
            c = reader.read();
            if (c == -1) {
                return -1;
            }
            if ((c >= '1') && (c <= '6')) {
                return c - '0';
            }
        }
    }

    static private void findMaxDominoSeq(Tile prev) {
        if (currentTiles.size() > maxTiles.size()) {
            maxTiles = new ArrayList<>();
            for (int i = 0; i < currentTiles.size(); i++) {
                maxTiles.add(new Tile(currentTiles.get(i).a, currentTiles.get(i).b, currentTiles.get(i).inv));
            }
        }

        Tile current = new Tile(0, 0, false);
        for (int i = 0; i < tiles.size(); i++) {
            if ((prev == null) || ((new TileComparator().compare(tiles.get(i), current) != 0) && (use[i] == false))) {
                current = tiles.get(i);
                if (prev == null || prev.getNext() == current.a) {
                    current.inv = false;
                    use[i] = true;
                    currentTiles.add(current);
                    findMaxDominoSeq(current);
                    currentTiles.remove(currentTiles.size() - 1);
                    use[i] = false;
                }
                if ((current.a != current.b) && (prev == null || prev.getNext() == current.b)) {
                    current.inv = true;
                    use[i] = true;
                    currentTiles.add(current);
                    findMaxDominoSeq(current);
                    currentTiles.remove(currentTiles.size() - 1);
                    use[i] = false;
                }
            }
        }
    }

    static public void findMaxDominoSeq(String dir) throws Exception {
        tiles = new ArrayList<>();
        maxTiles = new ArrayList<>();
        currentTiles = new ArrayList<>();

        FileReader reader = new FileReader(dir);
        int a;
        int b;
        while ((a = getInt(reader)) != -1) {
            b = getInt(reader);
            tiles.add(new Tile(a, b, false));
        }

        use = new boolean[tiles.size()];

        tiles.sort(new TileComparator());

        findMaxDominoSeq((Tile) null);

        for (Tile t : maxTiles) {
            if (t.inv == true) {
                System.out.print(t.b + ":" + t.a + " ");
            }
            else {
                System.out.print(t.a + ":" + t.b + " ");
            }
        }
    }


}
