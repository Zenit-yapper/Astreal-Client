package com.astreal.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class AstrealClickGui extends Screen {
    private float anim = 0;
    private final List<String> categories = new ArrayList<>();

    public AstrealClickGui() {
        super(Text.of("Astreal Menu"));
        categories.add("COMBAT");
        categories.add("MOVEMENT");
        categories.add("VISUAL");
        categories.add("MISC");
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Linear interpolation for smooth slide-in
        if (anim < 1.0f) anim += 0.08f * delta;

        // Background Blur effect
        context.fill(0, 0, width, height, new Color(0, 0, 0, (int)(160 * anim)).getRGB());

        int x = (width - 350) / 2;
        int y = (int) ((height - 220) / 2 * anim);

        // Main Glow Panel
        context.fill(x, y, x + 350, y + 220, new Color(15, 15, 15, 240).getRGB());
        context.drawBorder(x, y, 350, 220, 0x00FFFF); // Cyan "Gen Z" border

        // Sidebar for Categories
        context.fill(x, y, x + 80, y + 220, new Color(10, 10, 10, 255).getRGB());

        for (int i = 0; i < categories.size(); i++) {
            int catY = y + 20 + (i * 30);
            boolean hovered = mouseX >= x && mouseX <= x + 80 && mouseY >= catY && mouseY <= catY + 20;
            
            // Text color changes if hovered
            int color = hovered ? 0x00FFFF : 0xAAAAAA;
            context.drawTextWithShadow(textRenderer, categories.get(i), x + 10, catY, color);
        }

        // Mod List Area
        context.drawCenteredTextWithShadow(textRenderer, "--- ACTIVE MODS ---", x + 215, y + 10, 0xFFFFFF);
        renderMod(context, "Crystal Optimizer", x + 95, y + 40, true);
        renderMod(context, "NoHurtCam", x + 95, y + 60, true);
        renderMod(context, "Fullbright", x + 95, y + 80, false);

        super.render(context, mouseX, mouseY, delta);
    }

    private void renderMod(DrawContext context, String name, int x, int y, boolean on) {
        int color = on ? 0x00FF00 : 0xFF3333;
        context.drawTextWithShadow(textRenderer, "● " + name, x, y, color);
    }

    @Override public boolean shouldPause() { return false; }
}
