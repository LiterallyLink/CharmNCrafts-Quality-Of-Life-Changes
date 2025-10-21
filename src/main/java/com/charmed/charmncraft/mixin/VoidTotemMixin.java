package com.charmed.charmncraft.mixin;

import com.charmed.charmncraft.item.VoidTotemItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to intercept damage events and handle void totem early.
 * This hooks into the damage method to catch void damage before it becomes fatal.
 */
@Mixin(LivingEntity.class)
public abstract class VoidTotemMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void handleVoidDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity.getWorld().isClient) {
            return;
        }

        // Check if this is specifically out-of-world (void) damage
        if (source.isOf(DamageTypes.OUT_OF_WORLD)) {
            // Try to use void totem from either hand
            for (Hand hand : Hand.values()) {
                ItemStack stack = entity.getStackInHand(hand);

                if (stack.getItem() instanceof VoidTotemItem totem) {
                    // Use the totem
                    if (totem.useTotem(stack, entity)) {
                        cir.setReturnValue(true);
                        return;
                    }
                }
            }
        }
    }
}
