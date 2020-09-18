package com.lededesigns.detools.items;

import com.lededesigns.detools.DETools;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class DeToolsItem extends Item implements Comparable<DeToolsItem> {
    protected final int sortid;

    public DeToolsItem() {
        super(new Item.Properties().group(DETools.TAB).maxStackSize(64));
        this.sortid = 1;
    }
    public DeToolsItem(Properties properties, int sortid){
        super(properties.group(DETools.TAB));
        this.sortid = sortid;
    }

    public void initElements() {
        
    }
    public void init(FMLCommonSetupEvent event)
    {

    }

    public void serverLoad(FMLServerStartingEvent event)
    {

    }

    @OnlyIn(Dist.CLIENT)
    public void clientLoad(FMLClientSetupEvent event) {

    }

    @Override
    public int compareTo(DeToolsItem other)
    {
        return this.sortid - other.sortid;
    }
}
