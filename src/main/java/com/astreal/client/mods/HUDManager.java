package com.astreal.client.mods;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class HUDManager {
    public static void init() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            TextRenderer renderer = client.textRenderer;
            PlayerEntity player = client.player;

            if (player == null) return;

            // 1. FPS Display (Cyan 'Gen Z' Aesthetic)
            String fps = client.getCurrentFps() + " FPS";
            drawContext.drawTextWithShadow(renderer, fps, 5, 5, 0x00FFFF);

            // 2. Armor Status (Badlion Style)
            int yOffset = 20;
            for (ItemStack stack : player.getArmorItems()) {
                if (!stack.isEmpty()) {
                    String durability = (stack.getMaxDamage() - stack.getDamage()) + "/" + stack.getMaxDamage();
                    drawContext.drawItem(stack, 5, yOffset);
                    drawContext.drawTextWithShadow(renderer, durability, 25, yOffset + 4, 0xFFFFFF);
                    yOffset += 20;
                }
            }
        });
    }
}

