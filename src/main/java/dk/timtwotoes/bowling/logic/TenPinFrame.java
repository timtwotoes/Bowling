package dk.timtwotoes.bowling.logic;

public class TenPinFrame extends Frame {
    private static final int INITIAL_ROLLS = 2;

    public TenPinFrame(int[] points, Frame nextFrame, boolean isLastFrame) {
        super(points, nextFrame, isLastFrame);
    }

    public enum PinCount {
        OPEN,
        SPARE,
        STRIKE
    }

    public PinCount getPinCount() {
        int firstRoll = points.length == 0 ? 0 : points[0];
        int secondRoll = points.length < 2 ? 0 : points[1];

        if (firstRoll == 10) {
            return PinCount.STRIKE;
        } else if (firstRoll + secondRoll == 10) {
            return PinCount.SPARE;
        } else {
            return PinCount.OPEN;
        }
    }

    public int pinCountBonus(PinCount count) {
        switch (count) {
            case OPEN: return 0;
            case SPARE:
            case STRIKE:
                return 1;
            default:
                throw new IllegalStateException("Unexpected value: " + count);
        }
    }

    @Override
    public int computePoints() {
        int rolls = INITIAL_ROLLS + pinCountBonus(getPinCount());
        TenPinFrame currentFrame = this;
        int sumOfPoints = 0;

        while (rolls > 0 && currentFrame != null) {
            int pointIndex = 0;
            PinCount count = currentFrame.getPinCount();

            if (count == PinCount.STRIKE && currentFrame.isLastFrameInGame == false) {
                sumOfPoints += currentFrame.points[pointIndex];
                rolls--;
            } else {
                while (pointIndex < currentFrame.points.length && rolls > 0) {
                    sumOfPoints += currentFrame.points[pointIndex];
                    rolls--;
                    pointIndex++;
                }

                // Simulate a zero was rolled if we encounter none or one roll in points array.
                rolls -= currentFrame.points.length < 2 ? 2 - currentFrame.points.length : 0;
            }

            currentFrame = (TenPinFrame)currentFrame.nextFrame;
        }

        return sumOfPoints;
    }
}