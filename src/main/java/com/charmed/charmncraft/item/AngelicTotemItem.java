package com.charmed.charmncraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;

/**
 * Angelic Totem - Heals nearby players within 15 blocks.
 */
public class AngelicTotemItem extends SingleUseTotemItem {
    private static final double HEAL_RADIUS = 15.0;
    private static final float HEAL_AMOUNT = 4.0F; // 2 hearts

    public AngelicTotemItem(Settings settings) {
        super(settings);
    }

    public static boolean useTotem(ItemStack stack, LivingEntity entity) {
        // Apply base totem effects
        applyBaseEffects(entity, stack);

        // Heal nearby entities
        Box searchBox = entity.getBoundingBox().expand(HEAL_RADIUS);
        for (LivingEntity nearby : entity.getWorld().getEntitiesByClass(LivingEntity.class, searchBox, e -> e != entity)) {
            nearby.heal(HEAL_AMOUNT);
        }

        stack.decrement(1);
        return true;
    }
}