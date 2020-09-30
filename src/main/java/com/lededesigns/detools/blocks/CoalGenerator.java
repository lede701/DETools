package com.lededesigns.detools.blocks;

import com.lededesigns.detools.DETools;
import com.lededesigns.ledecore.blocks.GeneratorBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CoalGenerator extends GeneratorBlock {
    private static final Logger LOGGER = LogManager.getLogger(DETools.MOD_ID);
    private final int fuelSlot = 0;
    private int fuelTimeToBurn = 0;
    private double efficiency = 1f;
    private Map<Item, Integer> allowedFuel;

    public CoalGenerator() {
        super(GeneratorBlock.SIMPLE_GENERATOR());
        this.setDefaultState(this.getStateContainer().getBaseState().with(DirectionalBlock.FACING, Direction.NORTH));
        updateFuelGen();
    }

    public void updateFuelGen() {
        if(allowedFuel == null) {
            allowedFuel = new HashMap<Item, Integer>();
        }else {
            allowedFuel.clear();
        }
        // Coal is great
        allowedFuel.put(Items.COAL, (int)(200f * efficiency));
        allowedFuel.put(Items.COAL_BLOCK, (int)(1800f * efficiency));

        // Wood fuels
        allowedFuel.put(Items.ACACIA_PLANKS, (int)(50f * efficiency));
        allowedFuel.put(Items.BIRCH_PLANKS, (int)(50f * efficiency));
        allowedFuel.put(Items.DARK_OAK_PLANKS, (int)(50f * efficiency));
        allowedFuel.put(Items.OAK_PLANKS, (int)(50f * efficiency));
        allowedFuel.put(Items.SPRUCE_PLANKS, (int)(50f * efficiency));
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
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand handIn, BlockRayTraceResult hit) {
        super.onBlockActivated(state, world, pos, entity, handIn, hit);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if(entity instanceof ServerPlayerEntity) {
            NetworkHooks.openGui((ServerPlayerEntity) entity, new INamedContainerProvider() {
                @Override
                public ITextComponent getDisplayName() {
                    return new StringTextComponent("Coal Generator");
                }

                @Override
                public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
                    return null;
                }
            });
        }
        return ActionResultType.SUCCESS;
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
            Item itemInFuelSlot = Items.DIRT;

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
