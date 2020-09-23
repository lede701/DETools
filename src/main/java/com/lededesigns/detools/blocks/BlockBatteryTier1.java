package com.lededesigns.detools.blocks;

import com.lededesigns.detools.DETools;
import com.lededesigns.detools.items.DeToolsElement;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;

public class BlockBatteryTier1 extends DeToolsBlock {
    public BlockBatteryTier1() {
        super(Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(2.0f, 6.0f)
                .harvestLevel(0)
                .lightValue(0)
                .sound(SoundType.METAL)
        );
    }

    @Override
    public void initElements() {
    }
}
