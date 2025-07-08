package uk.co.oathompsonjones;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FairyLights implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final String MOD_NAME = "Fairy Lights";
    public static final String MOD_ID   = MOD_NAME.toLowerCase().replace(" ", "");
    public static final Logger LOGGER   = LoggerFactory.getLogger(MOD_ID);

    public static final String[] COLORS = {
            "white",
            "light_gray",
            "gray",
            "black",
            "brown",
            "red",
            "orange",
            "yellow",
            "lime",
            "green",
            "cyan",
            "light_blue",
            "blue",
            "purple",
            "magenta",
            "pink"
    };

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("{} is initializing!", MOD_ID);

        // Register all particle types
        FairyLightsParticles.initialize();

        // Register all items, item groups and blocks
        FairyLightsBlocks.initialize();
    }
}