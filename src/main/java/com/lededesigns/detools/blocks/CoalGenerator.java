package com.lededesigns.detools.blocks;

import com.lededesigns.detools.DETools;
import com.lededesigns.ledecore.blocks.GeneratorBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CoalGenerator extends GeneratorBlock {
    private static final Logger LOGGER = LogManager.getLogger(DETools.MOD_ID);
    private final int fuelSlot = 0;
    private int fuelTimeToBurn = 0;
    private Map<Item, Integer> allowedFuel;

    public CoalGenerator() {
        super(GeneratorBlock.SIMPLE_GENERATOR());
        this.setDefaultState(this.getStateContainer().getBaseState().with(DirectionalBlock.FACING, Direction.NORTH));
        allowedFuel = new HashMap<Item, Integer>();
        // Coal is great
        allowedFuel.put(Items.COAL, 200);
        allowedFuel.put(Items.COAL_BLOCK, 1800);

        // Wood fuels
        allowedFuel.put(Items.ACACIA_PLANKS, 50);
        allowedFuel.put(Items.BIRCH_PLANKS, 50);
        allowedFuel.put(Items.DARK_OAK_PLANKS, 50);
        allowedFuel.put(Items.OAK_PLANKS, 50);
        allowedFuel.put(Items.SPRUCE_PLANKS, 50);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(DirectionalBlock.FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(DirectionalBlock.FACING, context.getFace());
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onBlockAdded(state, world, pos, oldState, isMoving);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        world.getPendingBlockTicks().scheduleTick(new BlockPos(x,y,z), this, this.tickRate(world));
        if(!world.getWorld().isRemote) {
            LOGGER.debug("Block added to world!");
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        super.tick(state, world, pos, rand);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        {
            Map<String, Object> _dependencies = new HashMap<>();
            _dependencies.put("x", x);
            _dependencies.put("y", y);
            _dependencies.put("z", z);
            _dependencies.put("world", world);
            GeneratorProcess(_dependencies);
        }
        world.getPendingBlockTicks().scheduleTick(new BlockPos(x, y, z), this, this.tickRate(world));
    }

    public void GeneratorProcess(Map<String, Object> dependencies) {
        if (dependencies.get("x") == null) {
            System.err.println("Failed to load dependency [x] for procedure GeneratorProcess in class CoalGenerator");
            return;
        }
        if (dependencies.get("y") == null) {
            System.err.println("Failed to load dependency [y] for procedure GeneratorProcess in class CoalGenerator");
            return;
        }
        if (dependencies.get("z") == null) {
            System.err.println("Failed to load dependency [z] for procedure GeneratorProcess in class CoalGenerator");
            return;
        }
        if(dependencies.get("world") == null) {
            System.err.println("Failed to load dependency world for procedure GeneratorProcess in class CoalGenerator");
            return;
        }
        double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
        double y = dependencies.get("x") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
        double z = dependencies.get("x") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
        IWorld world = (IWorld) dependencies.get("world");

        if(!world.getWorld().isRemote) {
            // TODO: Calculate item count and what item is in the fuel slot
            // Get count of item in fuel slot
            int fuelSlotCount = 0;
            // Get what kind of item is in fuel slot
            Item itemInFuelSlot = Items.COAL;

            // Need to check if there is fuel in the feulslot
            if (fuelTimeToBurn == 0 && fuelSlotCount > 0 && allowedFuel.containsKey(itemInFuelSlot)) {
                // Take one item from the fuel slot
                fuelTimeToBurn = allowedFuel.get(itemInFuelSlot);
            }

            // Generate Power if we have burn time
            if (fuelTimeToBurn > 0) {
                GenerateTick();
                fuelTimeToBurn = Integer.max(0, fuelTimeToBurn - getTickTime());
                LOGGER.debug("Powerlevel: " + getPowerLevel() + "%");
            }
        }
    }
}
