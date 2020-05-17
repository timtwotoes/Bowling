package dk.timtwotoes.bowling.logic;

import java.util.Arrays;

abstract class Frame {
    protected int[] points;
    protected Frame nextFrame;

    public Frame(int[] points, Frame nextFrame) {
        this.points = new int[points.length];
        System.arraycopy(points, 0, this.points, 0, points.length);
        this.nextFrame = nextFrame;
    }

    public int[] getPoints() {
        return Arrays.copyOf(points, points.length);
    }
    public abstract int computePoints();
}