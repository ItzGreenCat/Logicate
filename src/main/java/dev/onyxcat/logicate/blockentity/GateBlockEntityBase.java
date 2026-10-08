package dev.onyxcat.logicate.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GateBlockEntityBase extends BlockEntity {
    public List<BlockPos> previousConnections = new ArrayList<>();
    public boolean currentOutput = false;
    public GateBlockEntityBase(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        super(type, worldPosition, blockState);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        output.putBoolean("currentOutput",currentOutput);
        output.putInt("length",previousConnections.size());
        for(int i = 0;i < previousConnections.size();i++){
            BlockPos currentBlockPos = previousConnections.get(i);
            output.putInt(String.format("connection%dX",i),currentBlockPos.getX());
            output.putInt(String.format("connection%dY",i),currentBlockPos.getY());
            output.putInt(String.format("connection%dZ",i),currentBlockPos.getZ());
        }
        super.saveAdditional(output);
    }
    @Override
    protected void loadAdditional(ValueInput input){
        super.loadAdditional(input);
        currentOutput = input.getBooleanOr("currentOutput",false);
        previousConnections.clear();
        int length = input.getIntOr("length",0);
        for(int i = 0;i < length;i++){
            int x = input.getInt(String.format("connection%dX",i)).get();
            int y = input.getInt(String.format("connection%dY",i)).get();
            int z = input.getInt(String.format("connection%dZ",i)).get();
            BlockPos pos = new BlockPos(x,y,z);
            previousConnections.add(pos);
        }
    }
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    @Override
    public void setChanged() {
        super.setChanged();

        if (level == null) return;

        BlockState state = getBlockState();
        level.sendBlockUpdated(worldPosition, state, state, Block.UPDATE_ALL);
    }
    public void addConnection(BlockPos pos){
        previousConnections.add(pos);
        this.setChanged();
    }
    public void removeConnection(BlockPos pos){
        previousConnections.remove(pos);
        this.setChanged();
    }
    public void setOutput(boolean value){
        currentOutput = value;
        this.setChanged();
    }
    public static void tick(Level level, BlockPos blockPos, BlockState blockState,GateBlockEntityBase entity) {
        if(level.isClientSide()){
            entity.clientTickEvent(level,blockPos,blockState);
        } else {
            entity.tickEvent(level,blockPos,blockState);
        }
    }
    public abstract void tickEvent(Level level, BlockPos blockPos, BlockState blockState);
    public void clientTickEvent(Level level, BlockPos blockPos, BlockState blockState){

    };
}
