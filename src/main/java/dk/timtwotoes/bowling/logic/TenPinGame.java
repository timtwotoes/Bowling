package dk.timtwotoes.bowling.logic;

class TenPinGame extends Game {
    protected TenPinGame(Frame[] frames) {
        super(frames);
    }

    public int[] sumAllFramePoints() {
        // An eleventh frame is allowed as a way of representing a perfect game.
        int amountOfFrames = frames.length > 10 ? 10 : frames.length;
        int[] points = new int[amountOfFrames];
        int sum = 0;

        for (int index = 0; index < amountOfFrames; index++) {
            sum += frames[index].computePoints();
            points[index] = sum;
        }

        return points;
    }
}
