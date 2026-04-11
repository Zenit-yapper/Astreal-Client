package com.astreal.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import com.astreal.client.mods.HUDManager; // We will put the drawing logic here

public class AstrealClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // This is the registration! 
        // It tells the game: "Every time you draw the HUD, run my code."
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            HUDManager.render(drawContext, tickDelta);
        });
        
        System.out.println("Astreal Client HUD Initialized!");
    }
}

