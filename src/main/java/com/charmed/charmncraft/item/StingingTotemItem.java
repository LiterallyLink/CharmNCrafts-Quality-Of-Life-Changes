package com.charmed.charmncraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.BlockPos;

/**
 * Stinging Totem - Works like a regular totem, then applies Poison to all nearby mobs.
 */
public class StingingTotemItem extends SingleUseTotemItem {
    private static final double EFFECT_RADIUS = 16.0;
    private static final int POISON_DURATION = 200;     // 10 seconds
    private static final int POISON_AMPLIFIER = 0;      // Poison I

    public StingingTotemItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean useTotem(ItemStack stack, LivingEntity entity) {
        // Apply base totem effects (healing, regeneration, etc.)
        applyBaseEffects(entity, stack);

        // Apply poison to nearby mobs
        applyPoisonToNearbyMobs(entity);

        stack.decrement(1);
        return true;
    }

    /**
     * Applies Poison to all mobs within EFFECT_RADIUS of the player.
     */
    private void applyPoisonToNearbyMobs(LivingEntity entity) {
        BlockPos playerPos = entity.getBlockPos();
        Box effectBox = new Box(playerPos).expand(EFFECT_RADIUS);

        entity.getWorld()
            .getOtherEntities(entity, effectBox, targetEntity -> targetEntity instanceof LivingEntity)
            .stream()
            .map(targetEntity -> (LivingEntity) targetEntity)
            .filter(livingEntity -> !livingEntity.isPlayer()) // Don't affect players
            .forEach(livingEntity -> livingEntity.addStatusEffect(
                new StatusEffectInstance(StatusEffects.POISON, POISON_DURATION, POISON_AMPLIFIER)
            ));
    }
}
