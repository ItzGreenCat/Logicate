package dev.onyxcat.logicate.block;

import dev.onyxcat.logicate.blockentity.AndGateBlockEntity;
import dev.onyxcat.logicate.blockentity.BlockEntityManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class AndGateBlock extends GateBlockBase {
    @Override
    public BlockEntityType<AndGateBlockEntity> getCurrentGateEntityType() {
        return BlockEntityManager.AND_GATE_BLOCK_ENTITY_BLOCK_ENTITY_TYPE;
    }

    public AndGateBlock(Properties properties) {
        super(properties);
    }
    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new AndGateBlockEntity(worldPosition,blockState);
    }
}
