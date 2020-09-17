package com.lededesigns.detools.util;

import com.lededesigns.detools.DETools;
import com.lededesigns.detools.items.DeToolsItem;
import com.lededesigns.detools.items.ItemGearPattern;
import com.lededesigns.detools.tools.DEToolsItemTier;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    // Item Registry
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, DETools.MOD_ID);

    // Registry methods
    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Items
    public static final RegistryObject<Item> ITEM_DIAMOND_EMERALD = ITEMS.register("item_diamond_emerald", DeToolsItem::new);

    public static final RegistryObject<Item> ITEM_MACHINE_FRAME = ITEMS.register("item_machine_frame", DeToolsItem::new);
    public static final RegistryObject<Item> ITEM_GEAR_PATTERN = ITEMS.register("item_gear_pattern", ItemGearPattern::new);

    // Tools
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
}
