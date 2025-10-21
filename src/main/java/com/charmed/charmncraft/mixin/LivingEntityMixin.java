package com.charmed.charmncraft.mixin;

import com.charmed.charmncraft.item.SingleUseTotemItem;
import com.charmed.charmncraft.item.TotemOfNeverdyingItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to intercept the tryUseTotem method and handle custom totem logic.
 * Uses HEAD injection to run before vanilla totem logic, ensuring proper priority.
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void tryUseCustomTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity.getWorld().isClient) {
            return;
        }

        for (Hand hand : Hand.values()) {
            ItemStack stack = entity.getStackInHand(hand);

            // Skip empty stacks
            if (stack.isEmpty()) {
                continue;
            }

            // Check TotemOfNeverdying first (special priority - multi-use totem)
            if (stack.getItem() instanceof TotemOfNeverdyingItem totem) {
                if (totem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            // All other custom totems extend SingleUseTotemItem, so we can handle them all here
            // Each totem's specific effects are implemented in their own useTotem() method
            else if (stack.getItem() instanceof SingleUseTotemItem totem) {
                if (totem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
        }
    }
}
