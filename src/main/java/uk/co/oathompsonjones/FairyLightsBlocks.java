package uk.co.oathompsonjones;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import uk.co.oathompsonjones.endrod.FairyLightsEndRodBlock;
import uk.co.oathompsonjones.torch.FairyLightsTorchItem;
import uk.co.oathompsonjones.torch.FairyLightsWallTorchBlock;

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

    public static final Map<String, Block> SEA_LANTERN_BLOCKS = Arrays
            .stream(FairyLights.COLORS)
            .collect(Collectors.toMap(color -> color,
                    color -> register(new Block(AbstractBlock.Settings.copy(Blocks.SEA_LANTERN).nonOpaque()),
                            color + "_sea_lantern"
                    )
            ));

    public static final Map<String, Block> END_ROD_BLOCKS = Arrays.stream(FairyLights.COLORS).collect(Collectors.toMap(
            color -> color,
            color -> register(
                    new FairyLightsEndRodBlock(AbstractBlock.Settings.copy(Blocks.END_ROD).nonOpaque()),
                    color + "_end_rod"
            )
    ));

    public static final Map<String, Block> TORCH_BLOCKS = new HashMap<>();
    public static final Map<String, Block> WALL_TORCH_BLOCKS = new HashMap<>();

    static {
        for (String color : FairyLights.COLORS) {
            TorchBlock torch = new TorchBlock(AbstractBlock.Settings.copy(Blocks.TORCH).nonOpaque(),
                    FairyLightsParticles.FLAMES.get(color)
            );
            WallTorchBlock wallTorch = new FairyLightsWallTorchBlock(AbstractBlock.Settings
                    .copy(Blocks.WALL_TORCH)
                    .nonOpaque(), color);

            TORCH_BLOCKS.put(color,
                    register(torch, color + "_torch", () -> new FairyLightsTorchItem(torch, wallTorch))
            );
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
            ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register((group) -> group.add(SEA_LANTERN_BLOCKS
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
        for (String color : FairyLights.COLORS)
            ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register((group) -> group.add(END_ROD_BLOCKS
                    .get(color)
                    .asItem()));
    }
}