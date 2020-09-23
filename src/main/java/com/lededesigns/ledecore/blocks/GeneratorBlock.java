package com.lededesigns.ledecore.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class GeneratorBlock extends Block implements IPowerGenerate, IPowerOut {
    private static final Logger LOGGER = LogManager.getLogger();

    private int powerMax;
    private int powerStored;
    private int powerGenerate;
    private EnumSet<PowerDirections> powerDirection;
    private int tickTime;

    public Item fuelSlot;

    public static Properties SIMPLE_GENERATOR(){
        return new GeneratorBlock.Properties()
                .PowerMax(20000)
                .PowerStored(0)
                .PowerGenerated(200)
                .PowerDirection(PowerDirections.ALL)
                .TickTime(20);
    }

    @Override
    public int SendPower(int usageRequest) {
        int canSend = min(powerStored, usageRequest);
        powerStored = max(0, powerStored - usageRequest);
        return canSend;
    }

    @Override
    public boolean CanSendPowerInDirection(PowerDirections powerDir) {
        return powerDirection.contains(powerDir);
    }

    public static class Properties{
        private int powerMax = 0;
        private int powerStored = 0;
        private int powerGenerate = 0;
        private EnumSet<PowerDirections> powerDirection = EnumSet.of(PowerDirections.NONE);
        private int tickTime = 20;

        public Properties() {

        }

        public final Properties PowerMax(int powerMax) {
            this.powerMax = powerMax;
            return this;
        }

        public final Properties PowerStored(int powerStored) {
            this.powerStored = powerStored;
            return this;
        }

        public final Properties PowerGenerated(int powerGenerate) {
            this.powerGenerate = powerGenerate;
            return this;
        }

        public final Properties PowerDirection(PowerDirections powDir) {
            // Check which direction to add
            switch(powDir){
                case ALL: {
                    powerDirection.clear();
                    powerDirection = EnumSet.of(PowerDirections.NORTH, PowerDirections.SOUTH, PowerDirections.EAST,
                            PowerDirections.WEST, PowerDirections.UP, PowerDirections.DOWN);
                }
                break;
                case NONE: {
                    powerDirection.clear();
                    powerDirection.add(powDir);
                }
                break;
                default: {
                    if (powerDirection.contains(PowerDirections.NONE)) {
                        powerDirection.remove(PowerDirections.NONE);
                    }
                    if (powerDirection.contains(powDir)) {
                        powerDirection.remove(powDir);
                    } else {
                        powerDirection.add(powDir);
                    }
                }
                break;
            }

            return this;
        }

        public final Properties TickTime(int tickTime) {
            this.tickTime = tickTime;
            return this;
        }

        public final int getPowerMax(){
            return this.powerMax;
        }

        public final int getPowerStored() {
            return this.powerStored;
        }

        public final int getPowerGenerate() {
            return this.powerGenerate;
        }

        public final EnumSet<PowerDirections> getPowerDirection() {
            return this.powerDirection;
        }

        public final int getTickTime() {
            return this.tickTime;
        }
    }

    public GeneratorBlock(Properties properties) {
        super(Block.Properties.create(Material.ROCK)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE));

        this.powerMax = properties.powerMax;
        this.powerStored = properties.powerStored;
        this.powerGenerate = properties.powerGenerate;
        this.tickTime = properties.tickTime;
    }

    public void GenerateTick() {
        powerStored = min(powerMax, powerStored + powerGenerate);
    }

    public int getTickTime(){
        return tickTime;
    }
    public double getPowerLevel() {
        return ((double) powerStored) / ((double)powerMax) * 100f;
    }

    @Override
    public int tickRate(IWorldReader worldIn) {
        return tickTime;
    }
}
