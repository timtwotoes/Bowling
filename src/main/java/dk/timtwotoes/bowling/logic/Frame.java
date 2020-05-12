package dk.timtwotoes.bowling.logic;

import java.util.Arrays;

public abstract class Frame {
    protected int[] points;
    protected Frame nextFrame;
    protected boolean isLastFrameInGame;

    public Frame(int[] points, Frame nextFrame, boolean isLastFrame) {
        this.points = new int[points.length];
        System.arraycopy(points, 0, this.points, 0, points.length);
        this.nextFrame = nextFrame;
        isLastFrameInGame = isLastFrame;
    }

    public int[] getPoints() {
        return Arrays.copyOf(points, points.length);
    }

    public abstract int computePoints();
}