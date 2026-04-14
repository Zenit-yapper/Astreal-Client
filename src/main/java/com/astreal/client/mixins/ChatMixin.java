package com.astreal.client.mixins;

public class ChatMixin {
    // This method calculates the rainbow color correctly within a class
    public static int getRainbowColor() {
        float hue = (System.currentTimeMillis() % 5000) / 5000f;
        return java.awt.Color.HSBtoRGB(hue, 0.8f, 1.0f);
    }
}
