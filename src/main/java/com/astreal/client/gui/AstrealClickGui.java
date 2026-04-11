package com.astreal.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class AstrealClickGui extends Screen {
    private float anim = 0.0f;
    private boolean closing = false;
    private final List<String> categories = new ArrayList<>();

    public AstrealClickGui() {
        super(Text.of("Astreal Client Menu"));
        // Define your mod categories
        categories.add("COMBAT");
        categories.add("MOVEMENT");
        categories.add("VISUAL");
        categories.add("MISC");
    }

    // This creates the "Freestyle" Rainbow effect
    private int getRainbow(int speed, int offset) {
        float hue = ((System.currentTimeMillis() + offset) % speed) / (float) speed;
        return Color.HSBtoRGB(hue, 0.7f, 1.0f);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // --- 1. Animation Logic (Twixtor Style) ---
        if (closing) {
            anim = Math.max(0, anim - 0.12f * delta);
            if (anim <= 0) this.close();
        } else {
            anim = Math.min(1.0f, anim + 0.12f * delta);
        }

        // --- 2. Background Darkening ---
        int alpha = (int)(180 * anim);
        context.fill(0, 0, width, height, new Color(0, 0, 0, alpha).getRGB());

        // --- 3. Panel Dimensions & Movement ---
        int pWidth = 360;
        int pHeight = 220;
        int x = (width - pWidth) / 2;
        int y = (height - pHeight) / 2;
        
        // Dynamic slide-up effect based on animation progress
        int animatedY = y + (int)((1.0f - anim) * 60);
        int rainbow = getRainbow(3000, 0);

        // --- 4. Draw Main Panel ---
        // Main Body
        context.fill(x, animatedY, x + pWidth, animatedY + pHeight, new Color(15, 15, 15, 255).getRGB());
        // Top Rainbow Accent Bar
        context.fill(x, animatedY, x + pWidth, animatedY + 2, rainbow);
        // Rainbow Border Glow
        context.drawBorder(x, animatedY, pWidth, pHeight, rainbow);

        // --- 5. Header & Categories ---
        context.drawCenteredTextWithShadow(textRenderer, "ASTREAL CLIENT", width / 2, animatedY + 10, rainbow);
        
        // Sidebar Background
        context.fill(x, animatedY + 25, x + 90, animatedY + pHeight, new Color(10, 10, 10, 255).getRGB());

        for (int i = 0; i < categories.size(); i++) {
            int catY = animatedY + 40 + (i * 25);
            boolean isHovered = mouseX >= x && mouseX <= x + 90 && mouseY >= catY && mouseY <= catY + 15;
            
            int color = isHovered ? rainbow : 0xBBBBBB;
            context.drawTextWithShadow(textRenderer, "> " + categories.get(i), x + 10, catY, color);
        }

        // --- 6. Mod List (The Scraps) ---
        int listX = x + 110;
        renderMod(context, "Crystal Optimizer", listX, animatedY + 45, true, rainbow);
        renderMod(context, "NoHurtCam", listX, animatedY + 70, true, rainbow);
        renderMod(context, "Fullbright", listX, animatedY + 95, false, rainbow);
        renderMod(context, "ImmediatelyFast", listX, animatedY + 120, true, rainbow);

        super.render(context, mouseX, mouseY, delta);
    }

    private void renderMod(DrawContext context, String name, int x, int y, boolean enabled, int rainbow) {
        int color = enabled ? rainbow : 0x555555;
        String prefix = enabled ? "[ON] " : "[OFF] ";
        context.drawTextWithShadow(textRenderer, prefix + name, x, y, color);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // If ESC or Right Shift (or whatever key you want) is pressed, close smoothly
        if (keyCode == 256) { // 256 is the ESC key
            closing = true;
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean shouldPause() {
        return false; // Game keeps running behind the menu
    }
}
