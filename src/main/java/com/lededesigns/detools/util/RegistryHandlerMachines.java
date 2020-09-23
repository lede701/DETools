package com.lededesigns.detools.util;

import com.lededesigns.detools.DETools;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandlerMachines {
    // Item Registry
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, DETools.MOD_ID);

    // Block Registry
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS, DETools.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Generators
    public static final RegistryObject<Block> COAL_GENERATOR_BLOCK = BLOCKS.register("coal_generator_block", com.lededesigns.detools.blocks.CoalGenerator::new);
    public static final RegistryObject<Item> COAL_GENERATOR_ITEM = ITEMS.register("coal_generator_block", () -> new BlockItem(COAL_GENERATOR_BLOCK.get().getBlock(),
            new Item.Properties().group(DETools.TAB).maxStackSize(1)));

    // Batteries/Capacitors
    public static final RegistryObject<Block> BLOCK_BATTERY_TIER1 = BLOCKS.register("block_battery_tier1", com.lededesigns.detools.blocks.BlockBatteryTier1::new);
    public static final RegistryObject<Item> BLOCK_BATTERY_TIER1_ITEM = ITEMS.register("block_battery_tier1", ()-> new BlockItem(BLOCK_BATTERY_TIER1.get().getBlock(),
            new Item.Properties().group(DETools.TAB).maxStackSize(1)));

}
