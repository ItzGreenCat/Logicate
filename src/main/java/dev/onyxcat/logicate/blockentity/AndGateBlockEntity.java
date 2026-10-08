package dev.onyxcat.logicate.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class AndGateBlockEntity extends GateBlockEntityBase {
    public AndGateBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(BlockEntityManager.AND_GATE_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, worldPosition, blockState);
    }

    @Override
    public void tickEvent(Level level, BlockPos blockPos, BlockState blockState) {

    }
}
