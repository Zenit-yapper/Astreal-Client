package com.astreal.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.awt.Color;

public class AstrealClickGui extends Screen {
    private float anim = 0;

    public AstrealClickGui() {
        super(Text.of("Astreal Client"));
    }

    // Helper method for the Rainbow Effect
    private int getRainbowColor() {
        float hue = (System.currentTimeMillis() % 2000) / 2000f; // 2 second cycle
        return Color.HSBtoRGB(hue, 0.8f, 1.0f);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (anim < 1.0f) anim += 0.1f * delta;

        // Dark fade
        context.fill(0, 0, width, height, new Color(0, 0, 0, (int)(180 * anim)).getRGB());

        int x = (width - 320) / 2;
        int y = (int) ((height - 200) / 2 * anim);
        int rainbow = getRainbowColor();

        // Main Panel with Rainbow Glow Border
        context.fill(x, y, x + 320, y + 200, new Color(10, 10, 10, 245).getRGB());
        
        // Drawing a 2-pixel thick rainbow border
        context.fill(x - 1, y - 1, x + 321, y, rainbow); // Top
        context.fill(x - 1, y + 200, x + 321, y + 201, rainbow); // Bottom

        context.drawCenteredTextWithShadow(textRenderer, "ASTREAL CLIENT v1.0", width / 2, y + 10, rainbow);

        // Sidebar Categories with hover animation
        renderCategory(context, "COMBAT", x + 10, y + 40, mouseX, mouseY);
        renderCategory(context, "VISUAL", x + 10, y + 70, mouseX, mouseY);
        renderCategory(context, "MODS", x + 10, y + 100, mouseX, mouseY);

        super.render(context, mouseX, mouseY, delta);
    }

    private void renderCategory(DrawContext context, String name, int x, int y, int mx, int my) {
        boolean hover = mx >= x && mx <= x + 60 && my >= y && my <= y + 10;
        int color = hover ? getRainbowColor() : 0xFFFFFF;
        context.drawTextWithShadow(textRenderer, "> " + name, x, y, color);
    }

    @Override public boolean shouldPause() { return false; }
}
