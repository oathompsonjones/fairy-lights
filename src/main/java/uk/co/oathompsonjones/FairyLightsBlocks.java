package uk.co.oathompsonjones;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FairyLightsBlocks {
    public static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(Registries.ITEM_GROUP.getKey(),
            Identifier.of(FairyLights.MOD_ID, "item_group")
    );

    public static final Map<String, Block> GLOWSTONE_BLOCKS = Arrays
            .stream(FairyLights.COLORS)
            .collect(Collectors.toMap(color -> color,
                    color -> register(new Block(AbstractBlock.Settings.copy(Blocks.GLOWSTONE)), color + "_glowstone")
            ));

    public static final Map<String, Block> LANTERN_BLOCKS = Arrays.stream(FairyLights.COLORS).collect(Collectors.toMap(color -> color,
            color -> register(new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN).nonOpaque()),
                    color + "_lantern"
            )
    ));

    public static final Map<String, Block> TORCH_BLOCKS = new HashMap<>();
    public static final Map<String, Block> WALL_TORCH_BLOCKS = new HashMap<>();

    static {
        for (String color : FairyLights.COLORS) {
            TorchBlock torch = new TorchBlock(AbstractBlock.Settings.copy(Blocks.TORCH).nonOpaque(),
                    FairyLightsParticles.FLAMES.get(color)
            );
            WallTorchBlock wallTorch = new WallTorchBlock(AbstractBlock.Settings.copy(Blocks.WALL_TORCH).nonOpaque(),
                    FairyLightsParticles.FLAMES.get(color)
            );

            TORCH_BLOCKS.put(color, register(torch, color + "_torch", () -> new TorchItem(torch, wallTorch)));
            WALL_TORCH_BLOCKS.put(color, register(wallTorch, "wall_" + color + "_torch", null));
        }
    }

    public static Block register(Block block, String name) {
        return register(block, name, () -> new BlockItem(block, new Item.Settings()));
    }

    public static Block register(Block block, String name, Supplier<Item> itemSupplier) {
        Identifier id = new Identifier(FairyLights.MOD_ID, name);

        if (itemSupplier != null) {
            Registry.register(Registries.ITEM, id, itemSupplier.get());
        }

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP,
                ITEM_GROUP,
                FabricItemGroup
                        .builder()
                        .icon(() -> new ItemStack(GLOWSTONE_BLOCKS.get("magenta")))
                        .displayName(Text.of(FairyLights.MOD_NAME))
                        .build()
        );

        // Register all the blocks in the item group
        for (String color : FairyLights.COLORS)
            ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register((group) -> group.add(GLOWSTONE_BLOCKS
                    .get(color)
                    .asItem()));
        for (String color : FairyLights.COLORS)
            ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register((group) -> group.add(LANTERN_BLOCKS
                    .get(color)
                    .asItem()));
        for (String color : FairyLights.COLORS)
            ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register((group) -> group.add(TORCH_BLOCKS
                    .get(color)
                    .asItem()));
    }

    private static class TorchItem extends BlockItem {
        private final Block wallBlock;

        public TorchItem(Block standingBlock, Block wallBlock) {
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
}