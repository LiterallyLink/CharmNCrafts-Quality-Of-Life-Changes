package com.charmed.charmncraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;

/**
 * Base class for all single-use totem items.
 * Works exactly like vanilla Totem of Undying - triggers once and disappears.
 */
public class SingleUseTotemItem extends Item {

    public SingleUseTotemItem(Settings settings) {
        super(settings);
    }

    public static boolean useTotem(ItemStack stack, LivingEntity entity) {
        applyBaseEffects(entity, stack);
        stack.decrement(1);
        return true;
    }

    /**
     * Applies standard totem effects: healing, regeneration, absorption, fire resistance.
     * Used by all custom totems.
     */
    protected static void applyBaseEffects(LivingEntity entity, ItemStack stack) {
        entity.setHealth(1.0F);
        entity.clearStatusEffects();
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));

        entity.getWorld().sendEntityStatus(entity, (byte) 35);

        if (entity instanceof ServerPlayerEntity player) {
            player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
        }
    }
}
