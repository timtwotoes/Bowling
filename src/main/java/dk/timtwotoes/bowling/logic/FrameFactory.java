package dk.timtwotoes.bowling.logic;

import java.util.Arrays;
import java.util.List;

public class FrameFactory {
    private static final int TEN_PIN_LAST_FRAME_INDEX = 9;

    public enum FrameKind {
        TEN_PIN
    }

    public Frame[] generateFrames(FrameKind kind, int[][] frames) {

        switch (kind) {
            case TEN_PIN:
                Frame[] tenPinFrames = new TenPinFrame[frames.length];
                int lastFrame = frames.length - 1;

                for (int frameIndex = lastFrame; frameIndex >= 0; frameIndex--) {
                    boolean isLastFrame = frameIndex == TEN_PIN_LAST_FRAME_INDEX;
                    Frame nextFrame = frameIndex == lastFrame ? null : tenPinFrames[frameIndex + 1];

                    Frame currentFrame = new TenPinFrame(frames[frameIndex], nextFrame, isLastFrame);
                    tenPinFrames[frameIndex] = currentFrame;
                }
                return tenPinFrames;

            default: return new TenPinFrame[0];
        }
    }
}