package dk.timtwotoes.bowling.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class TenPinAlgorithmTest {
    private final FrameFactory factory = new FrameFactory();

    int[] computeTenPinPoints(int[][] frames) {
        Game game = new Game(factory.generateFrames(FrameFactory.FrameKind.TEN_PIN, frames));
        return game.sumAllFramePoints();
    }

    @Test
    public void perfect_game() {
        int[][] game = {{10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 10, 10}};

        int[] expected = {30, 60, 90, 120, 150, 180, 210, 240, 270, 300};
        int[] actual = computeTenPinPoints(game);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void open_game() {
        int[][] game = {{2, 7}, {5, 1}, {4, 4}, {8, 1}, {6, 3}, {1, 2}, {1, 1}, {2, 7}, {5, 3}, {4, 2}};

        int[] expected = {9, 15, 23, 32, 41, 44, 46, 55, 63, 69};
        int[] actual = computeTenPinPoints(game);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void spare_game() {
        int[][] game = {{3, 7}, {5, 5}, {6, 4}, {8, 2}, {6, 4}, {8, 2}, {0, 10}, {2, 8}, {7, 3}, {4, 6, 10}};

        int[] expected = {15, 31, 49, 65, 83, 93, 105, 122, 136, 156};
        int[] actual = computeTenPinPoints(game);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void mixed_game() {
        int[][] game = {{3, 7}, {10, 0}, {3, 4}, {8, 2}, {0, 4}, {10, 0}, {10, 0}, {2, 4}, {10, 0}, {10, 6, 4}};

        int[] expected = {20, 37, 44, 54, 58, 80, 96, 102, 128, 148};
        int[] actual = computeTenPinPoints(game);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void missing_rolls() {
        int[][] game = {{3, 7}, {10}, {3, 4}, {8}, {0, 4}, {}};

        int[] expected = {20, 37, 44, 52, 56, 56};
        int[] actual = computeTenPinPoints(game);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void missing_rolls_in_tenth_frame() {
        int[][] game = {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {10, 0}, {10}};

        int[] expected = {0, 0, 0, 0, 0, 0, 0, 0, 20, 30};
        int[] actual = computeTenPinPoints(game);

        assertArrayEquals(expected, actual);
    }
}
