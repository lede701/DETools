package com.lededesigns.detools.items;

import com.lededesigns.detools.DETools;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class DeToolsItem extends Item {
    public DeToolsItem() {
        super(new Item.Properties().group(DETools.TAB).maxStackSize(64));
    }
    public DeToolsItem(Properties properties){
        super(properties.group(DETools.TAB));
    }

    public void initElements() {
        
    }
}
