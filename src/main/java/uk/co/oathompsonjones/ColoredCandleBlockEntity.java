package uk.co.oathompsonjones;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;

public class ColoredCandleBlockEntity extends BlockEntity {
    private DyeColor flameColour = null;

    public ColoredCandleBlockEntity(BlockPos pos, BlockState state) {
        super(FairyLightsBlockEntities.COLOURED_CANDLE, pos, state);
    }

    public DyeColor getFlameColour() {
        return flameColour;
    }

    public void setFlameColour(DyeColor flameColour) {
        this.flameColour = flameColour;
        markDirty();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("FlameColour"))
            this.flameColour = DyeColor.byName(nbt.getString("FlameColour"), null);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (flameColour != null)
            nbt.putString("FlameColour", flameColour.getName());
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }
}

