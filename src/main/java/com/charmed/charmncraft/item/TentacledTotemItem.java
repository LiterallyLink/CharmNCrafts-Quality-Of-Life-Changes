package com.charmed.charmncraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;

/**
 * Tentacled Totem of Undying - Only works if you die while underwater.
 * Applies standard totem effects plus Water Breathing and Dolphin's Grace.
 */
public class TentacledTotemItem extends SingleUseTotemItem {
    private static final int WATER_BREATHING_DURATION = 900; // 45 seconds
    private static final int DOLPHIN_GRACE_DURATION = 900;   // 45 seconds

    public TentacledTotemItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean useTotem(ItemStack stack, LivingEntity entity) {
        // Only trigger if player is underwater
        if (!entity.isSubmergedInWater()) {
            return false;
        }

        // Apply base totem effects
        applyBaseEffects(entity, stack);

        // Apply Water Breathing and Dolphin's Grace
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, WATER_BREATHING_DURATION, 0));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, DOLPHIN_GRACE_DURATION, 0));

        stack.decrement(1);
        return true;
    }
}
