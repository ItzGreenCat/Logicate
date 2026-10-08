package dev.onyxcat.logicate.blockentity;

import dev.onyxcat.logicate.Logicate;
import dev.onyxcat.logicate.block.BlockManager;
import dev.onyxcat.logicate.item.BlockItemManager;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntityManager {
    public static final BlockEntityType<AndGateBlockEntity> AND_GATE_BLOCK_ENTITY_BLOCK_ENTITY_TYPE = register("and_gate_block_entity",AndGateBlockEntity::new, BlockManager.AND_GATE_BLOCK);
    private static <T extends BlockEntity>BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory, Block... blocks){
        Identifier identifier = Identifier.fromNamespaceAndPath(Logicate.MODID,name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,identifier,FabricBlockEntityTypeBuilder.<T>create(entityFactory,blocks).build());
    }
}
