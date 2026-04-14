package com.astreal.client;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.KeyMapping; // Updated from KeyBinding
import net.minecraft.client.Minecraft; // Updated from MinecraftClient
import org.lwjgl.glfw.GLFW;

public class AstrealClient implements ModInitializer {
    // KeyMapping is the Mojang name for KeyBinding
    public static KeyMapping configKey;

    @Override
    public void onInitialize() {
        configKey = new KeyMapping(
            "key.astreal.config", 
            GLFW.GLFW_KEY_RIGHT_SHIFT, 
            "category.astreal.main"
        );
    }
}
