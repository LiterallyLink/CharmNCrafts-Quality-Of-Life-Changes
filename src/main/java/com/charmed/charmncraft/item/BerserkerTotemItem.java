package com.charmed.charmncraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;

/**
 * Berserker Totem - Grants strength and speed for 30 seconds.
 */
public class BerserkerTotemItem extends SingleUseTotemItem {
    private static final int STRENGTH_DURATION = 600; // 30 seconds in ticks
    private static final int SPEED_DURATION = 600;
    private static final int AMPLIFIER = 2; // Level 3

    public BerserkerTotemItem(Settings settings) {
        super(settings);
    }

    public static boolean useTotem(ItemStack stack, LivingEntity entity) {
        // Apply base totem effects
        entity.setHealth(1.0F);
        entity.clearStatusEffects();
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));

        // Add berserker effects
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, STRENGTH_DURATION, AMPLIFIER));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, SPEED_DURATION, AMPLIFIER));

        entity.getWorld().sendEntityStatus(entity, (byte) 35);

        if (entity instanceof ServerPlayerEntity player) {
            player.incrementStat(Stats.USED.getOrCreateStat((Item) stack.getItem()));
        }

        stack.decrement(1);
        return true;
    }
}
