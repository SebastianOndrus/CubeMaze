package org.gamestudio.game.core;

public class Cube {

    private int cubeX;
    private int cubeY;

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";


    private Colours cubeFront = Colours.NONE;
    private Colours cubeBack = Colours.NONE;
    private Colours cubeLeft = Colours.NONE;
    private Colours cubeRight = Colours.NONE;
    private Colours cubeTop = Colours.NONE;
    private Colours cubeBottom = Colours.NONE;


    public Cube( int cubeX, int cubeY ) {
        this.cubeX = cubeX;
        this.cubeY = cubeY;
    }

    public void displayCubeSides() {

        if ( cubeTop == Colours.BLUE ) {
            System.out.println("  \uD83D\uDFE6");
        } else if (cubeTop == Colours.RED) {
            System.out.println("  \uD83D\uDFE5");
        } else if (cubeTop == Colours.NONE) {
            System.out.println("  ⬜");
        }

        if ( cubeLeft == Colours.BLUE ) {
            System.out.print("\uD83D\uDFE6");
        } else if (cubeLeft == Colours.RED) {
            System.out.print("\uD83D\uDFE5");
        } else if (cubeLeft == Colours.NONE) {
            System.out.print("⬜");
        }

        if ( cubeFront == Colours.BLUE ) {
            System.out.print("\uD83D\uDFE6");
        } else if (cubeFront == Colours.RED) {
            System.out.print("\uD83D\uDFE5");
        } else if (cubeFront == Colours.NONE) {
            System.out.print("⬜");
        }
        if ( cubeRight == Colours.BLUE ) {
            System.out.print("\uD83D\uDFE6");
        } else if (cubeRight == Colours.RED) {
            System.out.print("\uD83D\uDFE5");
        } else if (cubeRight == Colours.NONE) {
            System.out.print("⬜");
        }
        if ( cubeBack == Colours.BLUE ) {
            System.out.println("\uD83D\uDFE6");
        } else if (cubeBack == Colours.RED) {
            System.out.println("\uD83D\uDFE5");
        } else if (cubeBack == Colours.NONE) {
            System.out.println("⬜");
        }

        if ( cubeBottom == Colours.BLUE ) {
            System.out.println("  \uD83D\uDFE6");
        } else if (cubeBottom == Colours.RED) {
            System.out.println("  \uD83D\uDFE5");
        } else if (cubeBottom == Colours.NONE) {
            System.out.println("  ⬜");
        }



    }

    public void printCube() {
        System.out.print(ANSI_GREEN + "▧" + ANSI_RESET);
    }

    public int getCubeX() {
        return cubeX;
    }

    public void setCubeX(int cubeX) {
        this.cubeX = cubeX;
    }

    public int getCubeY() {
        return cubeY;
    }

    public void setCubeY(int cubeY) {
        this.cubeY = cubeY;
    }

    public Colours getCubeFront() {
        return cubeFront;
    }

    public void setCubeFront(Colours cubeFront) {
        this.cubeFront = cubeFront;
    }

    public Colours getCubeBack() {
        return cubeBack;
    }

    public void setCubeBack(Colours cubeBack) {
        this.cubeBack = cubeBack;
    }

    public Colours getCubeLeft() {
        return cubeLeft;
    }

    public void setCubeLeft(Colours cubeLeft) {
        this.cubeLeft = cubeLeft;
    }

    public Colours getCubeRight() {
        return cubeRight;
    }

    public void setCubeRight(Colours cubeRight) {
        this.cubeRight = cubeRight;
    }

    public Colours getCubeTop() {
        return cubeTop;
    }

    public void setCubeTop(Colours cubeTop) {
        this.cubeTop = cubeTop;
    }

    public Colours getCubeBottom() {
        return cubeBottom;
    }

    public void setCubeBottom(Colours cubeBottom) {
        this.cubeBottom = cubeBottom;
    }

}
