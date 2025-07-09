package uk.co.oathompsonjones.mixin;

import net.minecraft.block.AbstractCandleBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.oathompsonjones.ColoredCandleBlockEntity;
import uk.co.oathompsonjones.FairyLightsParticles;

@Mixin(AbstractCandleBlock.class)
public abstract class AbstractCandleBlockMixin extends Block {
    @Shadow
    @Final
    public static BooleanProperty LIT;

    protected AbstractCandleBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    protected abstract Iterable<Vec3d> getParticleOffsets(BlockState state);

    @Inject(method="randomDisplayTick", at=@At("HEAD"), cancellable=true)
    private void fairyLights$randomDisplayTick(
            BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci
    ) {
        if (world.getBlockEntity(pos) instanceof ColoredCandleBlockEntity candleBE) {
            DyeColor colour = candleBE.getFlameColour();
            if (colour != null && state.get(LIT) && FairyLightsParticles.SMALL_FLAMES.containsKey(colour.asString())) {
                this.getParticleOffsets(state).forEach((offset) -> {
                    double x = pos.getX() + offset.x;
                    double y = pos.getY() + offset.y;
                    double z = pos.getZ() + offset.z;

                    world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0);
                    world.addParticle(FairyLightsParticles.SMALL_FLAMES.get(colour.asString()), x, y, z, 0.0, 0.0, 0.0);
                });
                ci.cancel();
            }
        }
    }
}


