package com.lededesigns.ledecore.blocks;

public enum PowerDirections {
    NORTH(1),
    SOUTH(2),
    EAST(4),
    WEST(8),
    UP(16),
    DOWN(32);

    private final int DIR;

    PowerDirections(int dir)
    {
        DIR = dir;
    }
}
