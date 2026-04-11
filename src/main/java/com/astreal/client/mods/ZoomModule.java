package com.astreal.client.mods;

import net.minecraft.client.MinecraftClient;

public class ZoomModule {
    public static boolean isZooming = false;
    private static final float ZOOM_LEVEL = 4.0f; // 4x Zoom

    public static float getZoom(float currentFov) {
        return isZooming ? currentFov / ZOOM_LEVEL : currentFov;
    }
}

