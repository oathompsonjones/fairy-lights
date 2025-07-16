package uk.co.oathompsonjones.datagen;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.util.Identifier;
import uk.co.oathompsonjones.FairyLights;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FairyLightsLanternAnimationProvider implements DataProvider {
    private final FabricDataOutput output;

    public FairyLightsLanternAnimationProvider(FabricDataOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (String color : FairyLights.COLORS) {
            JsonObject json = new JsonObject();
            JsonObject animation = new JsonObject();
            animation.addProperty("frametime", 8);
            json.add("animation", animation);

            Path filePath1 = output.getResolver(FabricDataOutput.OutputType.RESOURCE_PACK, "textures/block").resolve(Identifier.of(FairyLights.MOD_ID, color + "_lantern"),
                    "png.mcmeta"
            );
            Path filePath2 = output.getResolver(FabricDataOutput.OutputType.RESOURCE_PACK, "textures/block").resolve(Identifier.of(FairyLights.MOD_ID, color + "_sea_lantern"),
                    "png.mcmeta"
            );

            futures.add(DataProvider.writeToPath(writer, json, filePath1));
            futures.add(DataProvider.writeToPath(writer, json, filePath2));
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    @Override
    public String getName() {
        return "FairyLightsLanternAnimationProvider";
    }
}
