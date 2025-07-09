package uk.co.oathompsonjones.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import uk.co.oathompsonjones.FairyLights;
import uk.co.oathompsonjones.FairyLightsBlocks;

import java.util.concurrent.CompletableFuture;

public class FairyLightsBlockTagProvider extends FabricTagProvider<Block> {
    public FairyLightsBlockTagProvider(
            FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        for (String color : FairyLights.COLORS)
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(FairyLightsBlocks.LANTERN_BLOCKS.get(color));
    }
}

