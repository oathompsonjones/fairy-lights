package uk.co.oathompsonjones.fairylights.torch;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.Direction;

public class FairyLightsTorchItem extends BlockItem {
    private final Block wallBlock;

    public FairyLightsTorchItem(Block standingBlock, Block wallBlock) {
        super(standingBlock, new Item.Settings());
        this.wallBlock = wallBlock;
    }

    @Override
    protected BlockState getPlacementState(ItemPlacementContext context) {
        BlockState wallState = this.wallBlock.getPlacementState(context);
        return wallState != null
                && wallState.canPlaceAt(context.getWorld(), context.getBlockPos())
                && context.getSide() != Direction.UP
                && context.getSide() != Direction.DOWN ? wallState : super.getPlacementState(context);
    }
}