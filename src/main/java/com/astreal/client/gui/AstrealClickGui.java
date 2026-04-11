package com.astreal.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.awt.Color;

public class AstrealClickGui extends Screen {
    private float animationProgress = 0;

    public AstrealClickGui() {
        super(Text.of("Astreal Client Menu"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Smooth Animation Logic (0 to 1)
        if (animationProgress < 1.0f) animationProgress += 0.05f * delta;

        // Dark Background Fade
        context.fill(0, 0, width, height, new Color(0, 0, 0, (int)(150 * animationProgress)).getRGB());

        // Animated Main Panel
        int panelWidth = 300;
        int panelHeight = 200;
        int x = (width - panelWidth) / 2;
        int y = (int) (((height - panelHeight) / 2) * animationProgress); // Slides down

        context.fill(x, y, x + panelWidth, y + panelHeight, new Color(20, 20, 20, 230).getRGB());
        context.drawBorder(x, y, panelWidth, panelHeight, 0x00FFFF); // Cyan Border

        context.drawCenteredTextWithShadow(this.textRenderer, "--- ASTREAL CLIENT MODS ---", width / 2, y + 10, 0x00FFFF);

        // Mod Toggle Example
        renderModButton(context, "Fullbright", x + 20, y + 40, true);
        renderModButton(context, "VelocityOptic", x + 20, y + 65, false);

        super.render(context, mouseX, mouseY, delta);
    }

    private void renderModButton(DrawContext context, String name, int x, int y, boolean enabled) {
        int color = enabled ? 0x00FF00 : 0xFF0000;
        context.drawTextWithShadow(this.textRenderer, name + ": " + (enabled ? "[ON]" : "[OFF]"), x, y, color);
    }

    @Override
    public boolean shouldPause() { return false; }
}

