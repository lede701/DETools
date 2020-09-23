package com.lededesigns.ledecore.blocks;

public interface IPowerOut {
    public int SendPower(int usageRequest);
    public boolean CanSendPowerInDirection(PowerDirections powerDir);
}
