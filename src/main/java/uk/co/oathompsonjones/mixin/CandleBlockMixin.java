package uk.co.oathompsonjones.mixin;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.co.oathompsonjones.ColoredCandleBlockEntity;

@Mixin(CandleBlock.class)
public abstract class CandleBlockMixin extends AbstractCandleBlock implements Waterloggable, BlockEntityProvider {
    protected CandleBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ColoredCandleBlockEntity(pos, state);
    }

    @Inject(method="onUse", at=@At("HEAD"), cancellable=true)
    private void fairyLights$onUse(
            BlockState state,
            World world,
            BlockPos pos,
            PlayerEntity player,
            Hand hand,
            BlockHitResult hit,
            CallbackInfoReturnable<ActionResult> ci
    ) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() instanceof DyeItem dyeItem) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof ColoredCandleBlockEntity candleBE) {
                candleBE.setFlameColour(dyeItem.getColor());
                if (!player.isCreative())
                    stack.decrement(1);
                ci.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }
}
