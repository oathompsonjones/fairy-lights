package uk.co.oathompsonjones.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import uk.co.oathompsonjones.FairyLights;
import uk.co.oathompsonjones.FairyLightsBlocks;

import java.util.Objects;
import java.util.function.Consumer;

public class FairyLightsRecipeProvider extends FabricRecipeProvider {
    public FairyLightsRecipeProvider(FabricDataOutput generator) {
        super(generator);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        // Glowstones — 2x2, 3 glowstone dust + appropriate dye in any corner
        // Torches — stick + appropriate dye above
        // Lanterns — Coloured torch + 8 iron nuggets

        for (String color : FairyLights.COLORS) {
            Item dye = FairyLights.getDye(color);

            // Glowstone blocks
            Block block = FairyLightsBlocks.GLOWSTONE_BLOCKS.get(color);

            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, block).group(Objects
                    .requireNonNull(Identifier.of(FairyLights.MOD_ID, color + "_glowstone"))
                    .toString()).pattern("gg").pattern("gd").input('g', Items.GLOWSTONE_DUST).input('d', dye).criterion(
                    FabricRecipeProvider.hasItem(Items.GLOWSTONE_DUST),
                    FabricRecipeProvider.conditionsFromItem(Items.GLOWSTONE_DUST)
            ).criterion(FabricRecipeProvider.hasItem(dye), FabricRecipeProvider.conditionsFromItem(dye)).offerTo(exporter,
                    Identifier.of(FairyLights.MOD_ID, color + "_glowstone_1")
            );

            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, block).group(Objects
                    .requireNonNull(Identifier.of(FairyLights.MOD_ID, color + "_glowstone"))
                    .toString()).pattern("gg").pattern("dg").input('g', Items.GLOWSTONE_DUST).input('d', dye).criterion(
                    FabricRecipeProvider.hasItem(Items.GLOWSTONE_DUST),
                    FabricRecipeProvider.conditionsFromItem(Items.GLOWSTONE_DUST)
            ).criterion(FabricRecipeProvider.hasItem(dye), FabricRecipeProvider.conditionsFromItem(dye)).offerTo(exporter,
                    Identifier.of(FairyLights.MOD_ID, color + "_glowstone_2")
            );

            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, block).group(Objects
                    .requireNonNull(Identifier.of(FairyLights.MOD_ID, color + "_glowstone"))
                    .toString()).pattern("dg").pattern("gg").input('g', Items.GLOWSTONE_DUST).input('d', dye).criterion(
                    FabricRecipeProvider.hasItem(Items.GLOWSTONE_DUST),
                    FabricRecipeProvider.conditionsFromItem(Items.GLOWSTONE_DUST)
            ).criterion(FabricRecipeProvider.hasItem(dye), FabricRecipeProvider.conditionsFromItem(dye)).offerTo(exporter,
                    Identifier.of(FairyLights.MOD_ID, color + "_glowstone_3")
            );

            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, block).group(Objects
                    .requireNonNull(Identifier.of(FairyLights.MOD_ID, color + "_glowstone"))
                    .toString()).pattern("gd").pattern("gg").input('g', Items.GLOWSTONE_DUST).input('d', dye).criterion(
                    FabricRecipeProvider.hasItem(Items.GLOWSTONE_DUST),
                    FabricRecipeProvider.conditionsFromItem(Items.GLOWSTONE_DUST)
            ).criterion(FabricRecipeProvider.hasItem(dye), FabricRecipeProvider.conditionsFromItem(dye)).offerTo(exporter,
                    Identifier.of(FairyLights.MOD_ID, color + "_glowstone_4")
            );

            // Torches
            Block torch = FairyLightsBlocks.TORCH_BLOCKS.get(color);
            ShapedRecipeJsonBuilder
                    .create(RecipeCategory.DECORATIONS, torch)
                    .pattern("d")
                    .pattern("s")
                    .input('d', dye)
                    .input('s', Items.STICK)
                    .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                            FabricRecipeProvider.conditionsFromItem(Items.STICK)
                    )
                    .criterion(FabricRecipeProvider.hasItem(dye), FabricRecipeProvider.conditionsFromItem(dye))
                    .offerTo(exporter, Identifier.of(FairyLights.MOD_ID, color + "_torch"));

            // Lanterns
            Block lantern = FairyLightsBlocks.LANTERN_BLOCKS.get(color);
            ShapedRecipeJsonBuilder
                    .create(RecipeCategory.DECORATIONS, lantern)
                    .pattern("nnn")
                    .pattern("ntn")
                    .pattern("nnn")
                    .input('n', Items.IRON_NUGGET)
                    .input('t', torch)
                    .criterion(FabricRecipeProvider.hasItem(Items.IRON_NUGGET),
                            FabricRecipeProvider.conditionsFromItem(Items.IRON_NUGGET)
                    )
                    .criterion(FabricRecipeProvider.hasItem(torch), FabricRecipeProvider.conditionsFromItem(torch))
                    .offerTo(exporter, Identifier.of(FairyLights.MOD_ID, color + "_lantern"));
        }
    }
}
