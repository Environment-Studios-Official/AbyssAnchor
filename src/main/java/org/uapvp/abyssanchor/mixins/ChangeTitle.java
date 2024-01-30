package org.uapvp.abyssanchor.mixins;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class ChangeTitle {
    @Inject(method = "updateTitle", at = @At("RETURN"))
    private void createTitle(CallbackInfo ci) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getWindow().setTitle(String.valueOf(new StringBuilder("Abyss Anchor")));
    }
}
