package uk.co.oathompsonjones;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class FairyLightsParticles {
    public static final Map<String, DefaultParticleType> FLAMES = Arrays
            .stream(FairyLights.COLORS)
            .collect(Collectors.toMap(color -> color, color -> register(color + "_flame")));

    public static final Map<String, DefaultParticleType> SMALL_FLAMES = Arrays.stream(FairyLights.COLORS).collect(
            Collectors.toMap(color -> color, color -> register(color + "_small_flame")));

    public static DefaultParticleType register(String name) {
        return Registry.register(Registries.PARTICLE_TYPE,
                new Identifier(FairyLights.MOD_ID, name),
                FabricParticleTypes.simple()
        );
    }

    public static void initialize() {
    }
}
