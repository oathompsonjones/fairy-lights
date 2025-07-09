package uk.co.oathompsonjones;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.RenderLayer;

public class FairyLightsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        for (String color : FairyLights.COLORS) {
            BlockRenderLayerMap.INSTANCE.putBlock(FairyLightsBlocks.LANTERN_BLOCKS.get(color), RenderLayer.getCutout());
            BlockRenderLayerMap.INSTANCE.putBlock(FairyLightsBlocks.TORCH_BLOCKS.get(color), RenderLayer.getCutout());
            BlockRenderLayerMap.INSTANCE.putBlock(FairyLightsBlocks.WALL_TORCH_BLOCKS.get(color),
                    RenderLayer.getCutout()
            );
            ParticleFactoryRegistry.getInstance().register(FairyLightsParticles.FLAMES.get(color),
                    FlameParticle.Factory::new
            );
        }
    }
}