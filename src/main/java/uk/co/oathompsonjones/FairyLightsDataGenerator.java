package uk.co.oathompsonjones;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import uk.co.oathompsonjones.datagen.FairyLightsBlockLootTableProvider;
import uk.co.oathompsonjones.datagen.FairyLightsModelProvider;
import uk.co.oathompsonjones.datagen.FairyLightsParticleProvider;

public class FairyLightsDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(FairyLightsModelProvider::new);
        pack.addProvider(FairyLightsParticleProvider::new);
        pack.addProvider(FairyLightsBlockLootTableProvider::new);
    }
}
