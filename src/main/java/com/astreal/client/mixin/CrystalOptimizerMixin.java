package com.astreal.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.Minecraft.class)
public class CrystalOptimizerMixin {
    @Inject(method = "doItemUse", at = @At("HEAD"))
    private void onCrystalPlace(CallbackInfo ci) {
        // This removes the 4-tick delay for item usage when spamming crystals
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.player.getStackInHand(Hand.MAIN_HAND).getTranslationKey().contains("end_crystal")) {
            // Force item use tick reset for "Zero-Delay" feel
            ((IMinecraftClientAccessor)client).setItemUseCooldown(0);
        }
    }
}

