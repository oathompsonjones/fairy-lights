package uk.co.oathompsonjones;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import uk.co.oathompsonjones.datagen.*;

public class FairyLightsDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(FairyLightsBlockLootTableProvider::new);
        pack.addProvider(FairyLightsBlockTagProvider::new);
        pack.addProvider(FairyLightsEnGbLanguageProvider::new);
        pack.addProvider(FairyLightsEnUsLanguageProvider::new);
        pack.addProvider(FairyLightsLanternAnimationProvider::new);
        pack.addProvider(FairyLightsModelProvider::new);
        pack.addProvider(FairyLightsParticleProvider::new);
        pack.addProvider(FairyLightsRecipeProvider::new);
    }
}
