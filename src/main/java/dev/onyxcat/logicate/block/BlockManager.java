package dev.onyxcat.logicate.block;

import dev.onyxcat.logicate.Logicate;
import dev.onyxcat.logicate.item.BlockItemManager;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Map;
import java.util.function.Function;

public class BlockManager {
    public static AndGateBlock AND_GATE_BLOCK;
    public static void init(){
        AND_GATE_BLOCK = register("and_gate",AndGateBlock::new,BlockBehaviour.Properties.of());
    }
    private static <T extends Block> T register(String name, Function<BlockBehaviour.Properties,T> blockFunction, BlockBehaviour.Properties properties){
        BlockItemId blockItemIds = BlockItemId.create(Identifier.fromNamespaceAndPath(Logicate.MODID,name),Identifier.fromNamespaceAndPath(Logicate.MODID,name + "_item"));
        T block = blockFunction.apply(properties.setId(blockItemIds.block()));
        Registry.register(BuiltInRegistries.BLOCK,blockItemIds.block(),block);
        BlockItem item = new BlockItem(block,new Item.Properties().setId(blockItemIds.item()));
        Registry.register(BuiltInRegistries.ITEM,blockItemIds.item(),item);
        BlockItemManager.addBlockItem(block,item);
        return block;
    }
}
