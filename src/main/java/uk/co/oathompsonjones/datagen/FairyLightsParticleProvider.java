package uk.co.oathompsonjones.datagen;

import com.google.gson.JsonArray;
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

public class FairyLightsParticleProvider implements DataProvider {
    private final FabricDataOutput output;

    public FairyLightsParticleProvider(FabricDataOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (String color : FairyLights.COLORS) {
            JsonObject json = new JsonObject();
            JsonArray  textures = new JsonArray();
            textures.add(FairyLights.MOD_ID + ":" + color + "_flame");
            json.add("textures", textures);

            Path filePath1 = output.getResolver(FabricDataOutput.OutputType.RESOURCE_PACK, "particles").resolve(Identifier.of(FairyLights.MOD_ID, color + "_flame"),
                    "json"
            );
            Path filePath2 = output.getResolver(FabricDataOutput.OutputType.RESOURCE_PACK, "particles").resolve(Identifier.of(FairyLights.MOD_ID, color + "_small_flame"),
                    "json"
            );

            futures.add(DataProvider.writeToPath(writer, json, filePath1));
            futures.add(DataProvider.writeToPath(writer, json, filePath2));
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    @Override
    public String getName() {
        return "ParticleDataProvider";
    }
}