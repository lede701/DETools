package com.lededesigns.detools.blocks;

import com.lededesigns.detools.DETools;
import com.lededesigns.detools.items.DeToolsElement;
import net.minecraft.item.Item;

public class BlockBatteryTier1 extends DeToolsElement {
    public BlockBatteryTier1() {
        super(new Item.Properties().group(DETools.TAB).maxStackSize(1), 73);
    }

    @Override
    public void initElements() {

    }
}
