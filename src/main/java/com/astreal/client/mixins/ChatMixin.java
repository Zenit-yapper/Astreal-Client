// Add this logic to your chat renderer
float hue = (System.currentTimeMillis() % 5000) / 5000f;
int rainbow = java.awt.Color.HSBtoRGB(hue, 0.8f, 1.0f);
// Apply 'rainbow' to your username prefix in the HUD

