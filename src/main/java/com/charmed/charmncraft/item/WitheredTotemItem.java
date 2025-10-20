package com.charmed.charmncraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.BlockPos;

/**
 * Totem of Undying variant that applies Wither effect to all mobs
 * within a certain radius when activated.
 */
public class WitheredTotemItem extends SingleUseTotemItem {
    private static final double EFFECT_RADIUS = 16.0;
    private static final int WITHER_DURATION = 200; // 10 seconds at 20 ticks/sec
    private static final int WITHER_AMPLIFIER = 1; // Wither II

    public WitheredTotemItem(Settings settings) {
        super(settings);
    }

    public static boolean useTotem(ItemStack stack, LivingEntity entity) {
        // Apply base totem effects (healing, regeneration, etc.)
        applyBaseEffects(entity, stack);
        
        // Apply wither effect to nearby mobs
        applyWitherToNearbyMobs(entity);
        
        stack.decrement(1);
        return true;
    }

    /**
     * Applies the Wither effect to all mobs within EFFECT_RADIUS of the player.
     */
    private static void applyWitherToNearbyMobs(LivingEntity entity) {
        BlockPos playerPos = entity.getBlockPos();
        Box effectBox = new Box(playerPos).expand(EFFECT_RADIUS);
        
        entity.getWorld()
            .getOtherEntities(entity, effectBox, targetEntity -> targetEntity instanceof LivingEntity)
            .stream()
            .filter(targetEntity -> targetEntity instanceof LivingEntity)
            .map(targetEntity -> (LivingEntity) targetEntity)
            .filter(livingEntity -> !livingEntity.isPlayer()) // Don't affect players
            .forEach(livingEntity -> livingEntity.addStatusEffect(
                new StatusEffectInstance(StatusEffects.WITHER, WITHER_DURATION, WITHER_AMPLIFIER)
            ));
    }
}
