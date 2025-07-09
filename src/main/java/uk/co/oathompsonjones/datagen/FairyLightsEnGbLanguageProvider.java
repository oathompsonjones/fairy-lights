package uk.co.oathompsonjones.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import uk.co.oathompsonjones.FairyLights;

public class FairyLightsEnGbLanguageProvider extends FabricLanguageProvider {
    public FairyLightsEnGbLanguageProvider(FabricDataOutput dataGenerator) {
        super(dataGenerator, "en_gb");
    }

    public static String correctSpelling(String str) {
        return str.replace("gray", "grey");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        for (String color : FairyLights.COLORS) {
            String newColor = FairyLightsEnUsLanguageProvider.format(correctSpelling(color));
            translationBuilder.add("block." + FairyLights.MOD_ID + "." + color + "_glowstone", newColor + " Glowstone");
            translationBuilder.add("block." + FairyLights.MOD_ID + "." + color + "_lantern", newColor + " Lantern");
            translationBuilder.add("block." + FairyLights.MOD_ID + "." + color + "_torch", newColor + " Torch");
        }
    }
}

