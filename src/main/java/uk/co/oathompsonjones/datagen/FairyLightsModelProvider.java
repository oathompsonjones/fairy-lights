package uk.co.oathompsonjones.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import uk.co.oathompsonjones.FairyLights;
import uk.co.oathompsonjones.FairyLightsBlocks;

public class FairyLightsModelProvider extends FabricModelProvider {
    public FairyLightsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        for (String color : FairyLights.COLORS) {
            blockStateModelGenerator.registerSimpleCubeAll(FairyLightsBlocks.GLOWSTONE_BLOCKS.get(color));
            blockStateModelGenerator.registerLantern(FairyLightsBlocks.LANTERN_BLOCKS.get(color));
            blockStateModelGenerator.registerTorch(FairyLightsBlocks.TORCH_BLOCKS.get(color),
                    FairyLightsBlocks.WALL_TORCH_BLOCKS.get(color)
            );
            blockStateModelGenerator.registerSimpleCubeAll(FairyLightsBlocks.SEA_LANTERN_BLOCKS.get(color));
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }
}
