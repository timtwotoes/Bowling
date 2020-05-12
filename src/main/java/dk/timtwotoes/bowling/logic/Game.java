package dk.timtwotoes.bowling.logic;

public class Game {
    private Frame[] frames;

    public Game(Frame[] frames) {
        this.frames = frames;
    }

    public int[] sumAllFramePoints() {
        int[] points = new int[frames.length];
        int sum = 0;

        for (int index = 0; index < frames.length; index++) {
            sum += frames[index].computePoints();
            points[index] = sum;
        }

        return points;
    }
}