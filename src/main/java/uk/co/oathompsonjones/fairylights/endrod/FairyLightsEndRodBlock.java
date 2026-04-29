package uk.co.oathompsonjones.fairylights.endrod;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndRodBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class FairyLightsEndRodBlock extends EndRodBlock {
    public static final ColorProperty BASE_COLOR = ColorProperty.of("base_color");

    public FairyLightsEndRodBlock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(BASE_COLOR, NullableDyeColor.byName("null", null)));
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(BASE_COLOR);
    }

    @Override
    public ActionResult onUse(
            BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit
    ) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() instanceof DyeItem dye) {
            NullableDyeColor color = NullableDyeColor.byDyeColor(dye.getColor());
            if (color != state.get(BASE_COLOR)) {
                world.setBlockState(pos, state.with(BASE_COLOR, color), Block.NOTIFY_ALL);
                if (!player.isCreative())
                    stack.decrement(1);
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
