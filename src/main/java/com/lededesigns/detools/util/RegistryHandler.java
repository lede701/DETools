package com.lededesigns.detools.util;

import com.lededesigns.detools.DETools;
import com.lededesigns.detools.items.DeToolsElement;
import com.lededesigns.detools.items.ElementGearPattern;
import com.lededesigns.detools.tools.DEToolsItemTier;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class RegistryHandler {
    // Item Registry
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, DETools.MOD_ID);

    // Block Registry
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS, DETools.MOD_ID);

    public static final List<DeToolsElement> ELEMENTS = new ArrayList<>();

    // Registry methods
    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Items
    public static final RegistryObject<Item> ITEM_DIAMOND_EMERALD = ITEMS.register("item_diamond_emerald", DeToolsElement::new);

    public static final RegistryObject<Item> ITEM_MACHINE_FRAME = ITEMS.register("item_machine_frame", DeToolsElement::new);
    public static final RegistryObject<Item> ITEM_GEAR_PATTERN = ITEMS.register("item_gear_pattern", ElementGearPattern::new);

    // Diamond Emerald Tools
    public static final RegistryObject<SwordItem> DE_SWORD = ITEMS.register("de_sword", () ->
            new SwordItem(DEToolsItemTier.DIAMOND_EMERALD, 3, -3.0F, new Item.Properties().group(DETools.TAB)));
    public static final RegistryObject<PickaxeItem> DE_PICKAXE = ITEMS.register("de_pickaxe", () ->
            new PickaxeItem(DEToolsItemTier.DIAMOND_EMERALD, -3, -2.5F, new Item.Properties().group(DETools.TAB)));
    public static final RegistryObject<AxeItem> DE_AXE = ITEMS.register("de_axe", ()->
            new AxeItem(DEToolsItemTier.DIAMOND_EMERALD, 4, -4.0F, new Item.Properties().group(DETools.TAB)));
    public static final RegistryObject<ShovelItem> DE_SHOVEL = ITEMS.register("de_shovel", () ->
            new ShovelItem(DEToolsItemTier.DIAMOND_EMERALD, -7, -1F, new Item.Properties().group(DETools.TAB)));
    public static final RegistryObject<HoeItem> DE_HOE = ITEMS.register("de_hoe", () ->
            new HoeItem(DEToolsItemTier.DIAMOND_EMERALD, -1F, new Item.Properties().group(DETools.TAB)));
    // Emerald Tools
    public static final RegistryObject<SwordItem> E_SWORD = ITEMS.register("e_sword", () ->
            new SwordItem(DEToolsItemTier.EMERALD, 3, -3.0F, new Item.Properties().group(DETools.TAB)));
    public static final RegistryObject<PickaxeItem> E_PICKAXE = ITEMS.register("e_pickaxe", () ->
            new PickaxeItem(DEToolsItemTier.EMERALD, -3, -2.5F, new Item.Properties().group(DETools.TAB)));
    public static final RegistryObject<AxeItem> E_AXE = ITEMS.register("e_axe", ()->
            new AxeItem(DEToolsItemTier.EMERALD, 4, -4.0F, new Item.Properties().group(DETools.TAB)));
    public static final RegistryObject<ShovelItem> E_SHOVEL = ITEMS.register("e_shovel", () ->
            new ShovelItem(DEToolsItemTier.EMERALD, -7, -1F, new Item.Properties().group(DETools.TAB)));
    public static final RegistryObject<HoeItem> E_HOE = ITEMS.register("e_hoe", () ->
            new HoeItem(DEToolsItemTier.EMERALD, -1F, new Item.Properties().group(DETools.TAB)));

}
