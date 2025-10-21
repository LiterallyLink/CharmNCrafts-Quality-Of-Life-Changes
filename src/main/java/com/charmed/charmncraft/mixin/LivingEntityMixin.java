package com.charmed.charmncraft.mixin;

import com.charmed.charmncraft.item.AngelicTotemItem;
import com.charmed.charmncraft.item.BerserkerTotemItem;
import com.charmed.charmncraft.item.EchoTotemItem;
import com.charmed.charmncraft.item.ExplosiveTotemItem;
import com.charmed.charmncraft.item.IronGolemTotemItem;
import com.charmed.charmncraft.item.LoyalTotemItem;
import com.charmed.charmncraft.item.PhantomTotemItem;
import com.charmed.charmncraft.item.SingleUseTotemItem;
import com.charmed.charmncraft.item.SkeletalTotemItem;
import com.charmed.charmncraft.item.StingingTotemItem;
import com.charmed.charmncraft.item.TeleportingTotemItem;
import com.charmed.charmncraft.item.TentacledTotemItem;
import com.charmed.charmncraft.item.TotemOfNeverdyingItem;
import com.charmed.charmncraft.item.WitheredTotemItem;
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
            
            // Check TotemOfNeverdying first (priority)
            if (stack.getItem() instanceof TotemOfNeverdyingItem) {
                if (TotemOfNeverdyingItem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            // Check custom totems in priority order
            else if (stack.getItem() instanceof TeleportingTotemItem) {
                if (TeleportingTotemItem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            else if (stack.getItem() instanceof TentacledTotemItem) {
                if (TentacledTotemItem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            else if (stack.getItem() instanceof WitheredTotemItem) {
                if (WitheredTotemItem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            else if (stack.getItem() instanceof StingingTotemItem) {
                if (StingingTotemItem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            else if (stack.getItem() instanceof LoyalTotemItem) {
                if (LoyalTotemItem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            else if (stack.getItem() instanceof PhantomTotemItem) {
                if (PhantomTotemItem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            else if (stack.getItem() instanceof BerserkerTotemItem) {
                if (BerserkerTotemItem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            else if (stack.getItem() instanceof ExplosiveTotemItem) {
                if (ExplosiveTotemItem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            else if (stack.getItem() instanceof EchoTotemItem) {
                if (EchoTotemItem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            else if (stack.getItem() instanceof AngelicTotemItem) {
                if (AngelicTotemItem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            else if (stack.getItem() instanceof SkeletalTotemItem) {
                if (SkeletalTotemItem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            else if (stack.getItem() instanceof IronGolemTotemItem) {
                if (IronGolemTotemItem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            else if (stack.getItem() instanceof SingleUseTotemItem) {
                if (SingleUseTotemItem.useTotem(stack, entity)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
        }
    }
}
