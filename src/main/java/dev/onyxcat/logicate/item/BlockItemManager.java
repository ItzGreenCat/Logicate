package dev.onyxcat.logicate.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class BlockItemManager {
    private static final Map<String, BlockItem> blockItemInstances = new HashMap<>();
    public static void addBlockItem(Block block, BlockItem blockItem){
        blockItemInstances.put(block.properties().blockId().registry().getPath(),blockItem);
    }
    public static BlockItem getBlockItem(Block block){
        return blockItemInstances.get(block.properties().blockId().registry().getPath());
    }
}
