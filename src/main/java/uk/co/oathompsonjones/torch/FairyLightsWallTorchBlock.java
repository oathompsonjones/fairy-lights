package uk.co.oathompsonjones.torch;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import uk.co.oathompsonjones.FairyLightsBlocks;
import uk.co.oathompsonjones.FairyLightsParticles;

public class FairyLightsWallTorchBlock extends WallTorchBlock {
    private final String color;

    public FairyLightsWallTorchBlock(AbstractBlock.Settings settings, String color) {
        super(settings, FairyLightsParticles.FLAMES.get(color));
        this.color = color;
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(FairyLightsBlocks.TORCH_BLOCKS.get(color));
    }
}