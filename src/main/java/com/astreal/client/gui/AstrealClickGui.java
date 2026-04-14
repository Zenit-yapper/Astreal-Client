package com.astreal.client.gui;

import net.minecraft.client.gui.GuiGraphics; // Updated from DrawContext
import net.minecraft.client.gui.screens.Screen; // Updated path
import net.minecraft.network.chat.Component; // Updated from Text
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class AstrealClickGui extends Screen {
    private float anim = 0.0f;
    private boolean closing = false;
    private final List<String> categories = new ArrayList<>();

    public AstrealClickGui() {
        super(Component.literal("Astreal Client Menu"));
        categories.add("COMBAT");
        categories.add("MOVEMENT");
        categories.add("VISUAL");
        categories.add("MISC");
    }

    private int getRainbow(int speed, int offset) {
        float hue = ((System.currentTimeMillis() + offset) % speed) / (float) speed;
        return Color.HSBtoRGB(hue, 0.7f, 1.0f);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        if (closing) {
            anim = Math.max(0, anim - 0.12f * delta);
            if (anim <= 0) this.onClose();
        } else {
            anim = Math.min(1.0f, anim + 0.12f * delta);
        }

        int alpha = (int)(180 * anim);
        graphics.fill(0, 0, width, height, new Color(0, 0, 0, alpha).getRGB());

        int pWidth = 360;
        int pHeight = 220;
        int x = (width - pWidth) / 2;
        int y = (height - pHeight) / 2;
        int animatedY = y + (int)((1.0f - anim) * 60);
        int rainbow = getRainbow(3000, 0);

        // Main Panel
        graphics.fill(x, animatedY, x + pWidth, animatedY + pHeight, new Color(15, 15, 15, 255).getRGB());
        graphics.fill(x, animatedY, x + pWidth, animatedY + 2, rainbow);

        // Sidebar
        graphics.fill(x, animatedY + 25, x + 90, animatedY + pHeight, new Color(10, 10, 10, 255).getRGB());

        for (int i = 0; i < categories.size(); i++) {
            int catY = animatedY + 40 + (i * 25);
            int color = (mouseX >= x && mouseX <= x + 90 && mouseY >= catY && mouseY <= catY + 15) ? rainbow : 0xBBBBBB;
            graphics.drawString(font, "> " + categories.get(i), x + 10, catY, color);
        }

        super.render(graphics, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) { // ESC
            closing = true;
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
