package uk.co.oathompsonjones;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FairyLightsBlockEntities {
    public static void initialize() {
    }    public static final BlockEntityType<ColoredCandleBlockEntity> COLOURED_CANDLE = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(FairyLights.MOD_ID, "coloured_candle"),
            FabricBlockEntityTypeBuilder.create(ColoredCandleBlockEntity::new, Blocks.CANDLE).build(null)
    );


}
