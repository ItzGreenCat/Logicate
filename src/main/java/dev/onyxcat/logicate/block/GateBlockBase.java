package dev.onyxcat.logicate.block;

import dev.onyxcat.logicate.blockentity.GateBlockEntityBase;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jspecify.annotations.Nullable;

public abstract class GateBlockBase extends BaseEntityBlock {
    public static final BooleanProperty outputActivated = BooleanProperty.create("outputActivated");
    public abstract BlockEntityType<? extends GateBlockEntityBase> getCurrentGateEntityType();

    public GateBlockBase(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(outputActivated,false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(outputActivated);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        return createTickerHelper(type,getCurrentGateEntityType(), GateBlockEntityBase::tick);
    }
}