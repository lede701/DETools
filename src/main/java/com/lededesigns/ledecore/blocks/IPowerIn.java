package com.lededesigns.ledecore.blocks;

public interface IPowerIn {
    public int ReceivePower(int received);
    public boolean CanReceivePowerInDirection(PowerDirections powerDir);
}
