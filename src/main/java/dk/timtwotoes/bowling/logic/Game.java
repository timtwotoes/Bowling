package dk.timtwotoes.bowling.logic;

public class Game {
    private Frame[] frames;

    private Game() {}

    public static class Builder {
        private Frame[] frames;

        public enum Option {
            TEN_PIN
        }

        private Frame createFrame(Option option, int[] points, Frame nextFrame) {
            switch (option) {
                case TEN_PIN: return new TenPinFrame(points, nextFrame);
                default: throw new IllegalStateException("Unexpected option: " + option);
            }
        }

        private Frame[] createFrames(Option option, int length) {
            switch (option) {
                case TEN_PIN: return new TenPinFrame[length];

                default: throw new IllegalStateException("Unexpected option: " + option);
            }
        }

        public Builder(Option option, int[][] frames) {
            this.frames = createFrames(option, frames.length);
            int lastFrame = frames.length - 1;

            for (int frameIndex = lastFrame; frameIndex >= 0; frameIndex--) {
                Frame nextFrame = frameIndex == lastFrame ? null : this.frames[frameIndex + 1];
                Frame currentFrame = createFrame(option, frames[frameIndex], nextFrame);
                this.frames[frameIndex] = currentFrame;
            }
        }

        public Game build() {
            Game game = new Game();
            game.frames = this.frames;
            return game;
        }
    }

    public int[] sumAllFramePoints() {
        // An eleventh frame is allowed as a way of representing a perfect game.
        int amountOfFrames = frames.length > 9 ? 10 : frames.length;
        int[] points = new int[amountOfFrames];
        int sum = 0;

        for (int index = 0; index < amountOfFrames; index++) {
            // An eleventh frame is allowed as a way of representing a perfect game.
            sum += index >= 10 ? 0 : frames[index].computePoints();
            points[index] = sum;
        }

        return points;
    }
}