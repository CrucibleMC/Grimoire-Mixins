package io.github.crucible.grimoire.mixins.thaumcraft;

import com.gamerforea.eventhelper.util.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.common.items.wands.WandManager;

@Mixin(value = WandManager.class, remap = false)
public abstract class MixinWandManager {
    @Inject(method = "createCrucible", at = @At("HEAD"), cancellable = true)
    private static void checkPermission(ItemStack is, EntityPlayer player, World world, int x, int y, int z, CallbackInfoReturnable<Boolean> ci) {
        if (EventUtils.cantBreak(player, x, y, z)) {
            player.addChatMessage(new ChatComponentTranslation("servertext.focus.permission"));
            ci.setReturnValue(false);
            ci.cancel();
        }
    }

    @Inject(method = "createThaumonomicon", at = @At("HEAD"), cancellable = true)
    private static void checkPermission2(ItemStack is, EntityPlayer player, World world, int x, int y, int z, CallbackInfoReturnable<Boolean> ci) {
        if (EventUtils.cantBreak(player, x, y, z)) {
            player.addChatMessage(new ChatComponentTranslation("servertext.focus.permission"));
            ci.setReturnValue(false);
            ci.cancel();
        }
    }

}
