package com.astreal.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import com.astreal.client.mods.HUDManager;
import com.astreal.client.gui.AstrealClickGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AstrealClient implements ClientModInitializer {
    public static final String MOD_ID = "astreal-client";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // 1. Create the Click GUI Keybinding (Right Shift)
    private static KeyBinding configKey;

    @Override
    public void onInitializeClient() {
        LOGGER.info("------------------------------------------");
        LOGGER.info("ASTREAL CLIENT: Initializing PvP Engine...");
        LOGGER.info("------------------------------------------");

        // 2. Register the HUD (FPS, CPS, Armor durabilty)
        HUDManager.init();

        // 3. Setup the Keybind
        configKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.astreal.settings", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_RIGHT_SHIFT, 
                "category.astreal.client"
        ));

        // 4. Listen for the Key Press to open the Animated GUI
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (configKey.wasPressed()) {
                client.setScreen(new AstrealClickGui());
            }
        });
    }
}
