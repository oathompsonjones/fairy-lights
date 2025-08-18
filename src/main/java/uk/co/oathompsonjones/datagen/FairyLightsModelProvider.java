package uk.co.oathompsonjones.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import uk.co.oathompsonjones.FairyLights;
import uk.co.oathompsonjones.FairyLightsBlocks;
import uk.co.oathompsonjones.endrod.FairyLightsEndRodBlock;

import java.util.Optional;

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
            registerEndRod(blockStateModelGenerator, color);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }

    private void registerEndRod(BlockStateModelGenerator blockStateModelGenerator, String rodColor) {
        // TODO: Use block states to change base colour.
        //  Each block state file needs to handle the 16 possible colors alongside the 6 directions.
        //  Generate a model for each possible end rod colour combination.
        //  Use texture layers to apply the base texture over the rod texture.
        //  Model files: <rod_colour>_end_rod_<base_colour>_base.json
        //  Block state files: <rod_colour>_end_rod.json

        Block      BLOCK    = FairyLightsBlocks.END_ROD_BLOCKS.get(rodColor);
        TextureKey ROD      = TextureKey.of("rod");
        TextureKey BASE     = TextureKey.of("base");
        Identifier TEMPLATE = Identifier.of(FairyLights.MOD_ID, "block/end_rod_template");

        Model endRodModel = new Model(Optional.ofNullable(TEMPLATE), Optional.empty(), ROD, BASE);
        BlockStateVariantMap.DoubleProperty<Direction, DyeColor> blockStateVariantMap = BlockStateVariantMap.create(FairyLightsEndRodBlock.FACING,
                FairyLightsEndRodBlock.BASE_COLOR
        );

        Identifier itemModelId = null;

        for (DyeColor baseColor : DyeColor.values()) {
            TextureMap textureMap = new TextureMap().put(ROD,
                    new Identifier(FairyLights.MOD_ID, "block/" + rodColor + "_end_rod")
            ).put(
                    BASE,
                    new Identifier(FairyLights.MOD_ID, "block/" + baseColor.getName() + "_end_rod_base")
            );

            Identifier modelId = endRodModel.upload(
                    new Identifier(FairyLights.MOD_ID,
                            "block/" + rodColor + "_end_rod_" + baseColor.getName() + "_base"
                    ),
                    textureMap,
                    blockStateModelGenerator.modelCollector
            );

            if (baseColor.getName().equals(rodColor))
                itemModelId = modelId;

            for (Direction direction : Direction.values()) {
                blockStateVariantMap.register(direction,
                        baseColor,
                        BlockStateVariant.create().put(VariantSettings.MODEL, modelId)
                );
            }
        }

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier
                .create(BLOCK)
                .coordinate(blockStateVariantMap));

        blockStateModelGenerator.registerParentedItemModel(BLOCK, itemModelId);
    }
}
