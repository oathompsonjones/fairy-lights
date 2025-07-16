package uk.co.oathompsonjones.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.AllOfLootCondition;
import net.minecraft.loot.condition.InvertedLootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.NumberRange.IntRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import uk.co.oathompsonjones.FairyLights;
import uk.co.oathompsonjones.FairyLightsBlocks;

public class FairyLightsBlockLootTableProvider extends FabricBlockLootTableProvider {
    public FairyLightsBlockLootTableProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate() {
        for (String color : FairyLights.COLORS) {
            addCustomGlowstone(FairyLightsBlocks.GLOWSTONE_BLOCKS.get(color), color);
            addCustomSeaLantern(FairyLightsBlocks.SEA_LANTERN_BLOCKS.get(color), color);
            addDrop(FairyLightsBlocks.LANTERN_BLOCKS.get(color));
            addDrop(FairyLightsBlocks.TORCH_BLOCKS.get(color));
            addDrop(FairyLightsBlocks.WALL_TORCH_BLOCKS.get(color), FairyLightsBlocks.TORCH_BLOCKS.get(color));
        }
    }

    private void addCustomGlowstone(Block block, String color) {
        // Pool 1: Silk Touch drops the block, no dye
        LootPool.Builder silkTouchPool = LootPool
                .builder()
                .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder
                        .create()
                        .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, IntRange.ANY))))
                .with(ItemEntry.builder(block));

        // Pool 2: No Silk Touch drops 2 dust always
        LootPool.Builder dustPool = LootPool.builder().conditionally(InvertedLootCondition.builder(
                MatchToolLootCondition.builder(ItemPredicate.Builder
                        .create()
                        .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, IntRange.ANY))))).with(ItemEntry
                .builder(Items.GLOWSTONE_DUST)
                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2))));

        // Pool 3: No Silk Touch drops dye with 50% chance
        LootPool.Builder dyePool = LootPool.builder().conditionally(AllOfLootCondition.builder(
                InvertedLootCondition.builder(MatchToolLootCondition.builder(ItemPredicate.Builder
                        .create()
                        .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, IntRange.ANY)))),
                RandomChanceLootCondition.builder(0.5f)
        )).with(ItemEntry.builder(FairyLights.getDye(color)));

        addDrop(block, LootTable.builder().pool(silkTouchPool).pool(dustPool).pool(dyePool));
    }

    private void addCustomSeaLantern(Block block, String color) {
        // Pool 1: Silk Touch drops the block, no dye
        LootPool.Builder silkTouchPool = LootPool
                .builder()
                .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder
                        .create()
                        .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, IntRange.ANY))))
                .with(ItemEntry.builder(block));

        // Pool 2: No Silk Touch drops 3 prismarine crystals always
        LootPool.Builder crystalPool = LootPool.builder().conditionally(InvertedLootCondition.builder(
                MatchToolLootCondition.builder(ItemPredicate.Builder
                        .create()
                        .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, IntRange.ANY))))).with(ItemEntry
                .builder(Items.PRISMARINE_CRYSTALS)
                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(3))));

        // Pool 3: No Silk Touch drops dye with 50% chance
        LootPool.Builder dyePool = LootPool.builder().conditionally(AllOfLootCondition.builder(
                InvertedLootCondition.builder(MatchToolLootCondition.builder(ItemPredicate.Builder
                        .create()
                        .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, IntRange.ANY)))),
                RandomChanceLootCondition.builder(0.5f)
        )).with(ItemEntry.builder(FairyLights.getDye(color)));

        addDrop(block, LootTable.builder().pool(silkTouchPool).pool(crystalPool).pool(dyePool));
    }
}
