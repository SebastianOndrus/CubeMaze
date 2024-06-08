package org.gamestudio.game.core;

import org.gamestudio.game.core.tiles.*;

import java.util.ArrayList;
import java.util.List;

public class Levels {

    private List<int[][]> levelMapList = new ArrayList<>();
    private List<int[]> levelInfoList = new ArrayList<>();


    public Levels() {
        // 0 - empty
        // 1 - road
        // 2 - finish
        // 11,12,13 markers - NONE, BLUE, RED
        // 21,22,23 marked - NONE, BLUE, RED
        int[][] level1Map = {
                {0, 0, 2, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 23, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {13, 0, 0, 0, 0, 0, 0}
        };
        this.levelMapList.add(level1Map);
        int[][] level2Map = {
                {0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 13, 1, 1, 0, 0},
                {0, 1, 0, 1, 1, 13},
                {0, 1, 1, 1, 0, 0},
                {1, 23, 0, 0, 0, 0, 0},
                {23, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0}
        };
        this.levelMapList.add(level2Map);
        int[][] level3Map = {
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 13, 0, 0},
                {0, 0, 1, 0, 1, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1},
                {1, 1, 23, 0, 11, 0, 1},
                {1, 0, 1, 1, 1, 1, 1},
                {23, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0}
        };
        this.levelMapList.add(level3Map);
        int[][] level4Map = {
                {0, 0, 13, 0, 0, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 23, 1, 23, 2},
                {0, 0, 13, 0, 0, 1, 1, 0, 0, 0, 0},
        };
        this.levelMapList.add(level4Map);
        int[][] level5Map = {
                {0, 0, 0, 1, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 0, 1},
                {2, 0, 0, 1, 1},
                {1, 0, 0, 23, 13},
                {22, 0, 12, 1, 0},
                {23, 1, 22, 1, 0},
                {1, 1, 1, 0, 0}
        };
        this.levelMapList.add(level5Map);
        int[][] level6Map = {
                {0, 1, 13, 1, 23, 23, 23, 0, 0, 0, 0},
                {1, 1, 1, 0, 23, 12, 23, 1, 1, 22, 2},
                {0, 1, 13, 1, 23, 23, 23, 0, 0, 0, 0}
        };
        this.levelMapList.add(level6Map);
        int[][] level7Map = {
                {0, 0, 0, 1, 11, 23, 22, 23, 1, 2},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {1, 1, 0, 23, 22, 23, 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 1, 13, 12, 13, 1, 1, 0, 0}
        };
        this.levelMapList.add(level7Map);
        int[][] level8Map = {
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 13, 1, 1},
                {0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 11, 0},
                {1, 1, 1, 12, 0, 1, 0},
                {1, 0, 0, 1, 1, 23, 0},
                {1, 1, 1, 1, 0, 22, 0},
                {0, 0, 0, 0, 0, 2, 0}
        };
        this.levelMapList.add(level8Map);

        // width, height, columnCube, row.Cube, finalRow, finalCol
        int[] level1Info = {7, 7, 6, 3, 0, 2};
        this.levelInfoList.add(level1Info);
        int[] level2Info = {6, 9, 2, 0, 8, 0};
        this.levelInfoList.add(level2Info);
        int[] level3Info = {7, 9, 2, 0, 8, 0};
        this.levelInfoList.add(level3Info);
        int[] level4Info = {11, 3, 0, 1, 1, 10};
        this.levelInfoList.add(level4Info);
        int[] level5Info = {5, 8, 3, 0, 3, 0};
        this.levelInfoList.add(level5Info);
        int[] level6Info = {11, 3, 0, 1, 1, 10};
        this.levelInfoList.add(level6Info);
        int[] level7Info = {10, 5, 0, 2, 0, 9};
        this.levelInfoList.add(level7Info);
        int[] level8Info = {7, 10, 5, 0, 9, 5};
        this.levelInfoList.add(level8Info);
    }

    public int[][] getLevelMapNumbers(int levelNumber) {
        return levelMapList.get(levelNumber);
    }


    public Tile[][] getLevelMap(int levelNumber) {
        int width = getLevelWidth(levelNumber);
        int height = getLevelHeight(levelNumber);
        int[][] mapDescription = levelMapList.get(levelNumber);
        Tile[][] tiles = new Tile[height][width];

        for ( int row = 0; row < height; row++ ) {
            for ( int col = 0; col < width; col++ ) {
                int tileToSet =  mapDescription[row][col];
                if ( tileToSet == 0 ) {
                    tiles[row][col] = new Empty();
                } else if ( tileToSet == 1) {
                    tiles[row][col] = new Road();
                } else if ( tileToSet == 2) {
                    tiles[row][col] = new Finish();
                } else if ( tileToSet == 11) {
                    tiles[row][col] = new Marker(Colours.NONE);
                } else if ( tileToSet == 12) {
                    tiles[row][col] = new Marker(Colours.BLUE);
                } else if ( tileToSet == 13) {
                    tiles[row][col] = new Marker(Colours.RED);
                } else if ( tileToSet == 21) {
                    tiles[row][col] = new Marked(Colours.NONE);
                } else if ( tileToSet == 22) {
                    tiles[row][col] = new Marked(Colours.BLUE);
                } else if ( tileToSet == 23) {
                    tiles[row][col] = new Marked(Colours.RED);
                } else {
                    throw new RuntimeException("Unexpected tile type");
                }
            }
        }

        return tiles;
    }

    private int[] getLevelInfo( int levelNumber ) {
        return levelInfoList.get(levelNumber);
    }

    public int getCubeX( int levelNumber) {
        int[] info = getLevelInfo(levelNumber);
        return info[2];
    }

    public int getCubeY( int levelNumber) {
        int[] info = getLevelInfo(levelNumber);
        return info[3];

    }

    public int getLevelHeight( int levelNumber ) {
        int[] info = getLevelInfo(levelNumber);
        return info[1];
    }

    public int getLevelWidth( int levelNumber ) {
        int[] info = getLevelInfo(levelNumber);
        return info[0];
    }



}
