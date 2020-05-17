package dk.timtwotoes.bowling.logic;

public abstract class Game {
    protected Frame[] frames;

    protected Game(Frame[] frames) {
        this.frames = frames;
    }

    public static class Builder {
        private Frame[] frames;
        private Option option;

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

        private Game createGame(Option option, Frame[] frames) {
            switch (option) {
                case TEN_PIN: return new TenPinGame(frames);

                default:
                    throw new IllegalStateException("Unexpected option: " + option);
            }
        }

        public Builder(Option option, int[][] frames) {
            this.option = option;
            this.frames = createFrames(option, frames.length);
            int lastFrame = frames.length - 1;

            for (int frameIndex = lastFrame; frameIndex >= 0; frameIndex--) {
                Frame nextFrame = frameIndex == lastFrame ? null : this.frames[frameIndex + 1];
                Frame currentFrame = createFrame(option, frames[frameIndex], nextFrame);
                this.frames[frameIndex] = currentFrame;
            }
        }

        public Game build() {
            return createGame(option, frames);
        }
    }

    public abstract int[] sumAllFramePoints();
}