package com.lededesigns.ledecore.blocks;

import static java.lang.Integer.min;

public class GeneratorBlock implements IPowerGenerate {
    private int powerMax;
    private int powerStored;
    private int powerGenerate;
    private int tickTime;

    public static GeneratorBlock SIMPLEGENERATOR(){
        return new GeneratorBlock(20000, 0, 200, 20);
    }

    public GeneratorBlock(int powerMax, int powerStored, int powerGenerate, int tickTime) {
        this.powerMax = powerMax;
        this.powerStored = powerStored;
        this.powerGenerate = powerGenerate;
        this.tickTime = tickTime;
    }

    public GeneratorBlock(GeneratorBlock clone) {
        powerMax = clone.powerMax;
        powerStored = clone.powerStored;
        powerGenerate = clone.powerGenerate;
        tickTime = clone.tickTime;
    }

    public void GenerateTick() {
        powerStored = min(powerMax, powerStored + powerGenerate);
    }

    public int getTickTime(){
        return tickTime;
    }
}
