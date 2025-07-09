package uk.co.oathompsonjones.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import uk.co.oathompsonjones.FairyLights;

public class FairyLightsEnUsLanguageProvider extends FabricLanguageProvider {
    public FairyLightsEnUsLanguageProvider(FabricDataOutput dataGenerator) {
        super(dataGenerator);
    }

    public static String format(String str) {
        return toTitleCase(str.replace("_", " "));
    }

    public static String toTitleCase(String str) {
        if (str == null || str.isEmpty())
            return str;

        String[]      words     = str.split(" ");
        StringBuilder titleCase = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty())
                titleCase.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).append(
                        " ");
        }
        return titleCase.toString().trim();
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        for (String color : FairyLights.COLORS) {
            String newColor = format(color);
            translationBuilder.add("block." + FairyLights.MOD_ID + "." + color + "_glowstone", newColor + " Glowstone");
            translationBuilder.add("block." + FairyLights.MOD_ID + "." + color + "_lantern", newColor + " Lantern");
            translationBuilder.add("block." + FairyLights.MOD_ID + "." + color + "_torch", newColor + " Torch");
        }
    }
}

