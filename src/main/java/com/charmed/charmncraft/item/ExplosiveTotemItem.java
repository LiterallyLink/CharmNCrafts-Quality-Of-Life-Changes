package com.charmed.charmncraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.explosion.Explosion;

/**
 * Explosive Totem - Creates an explosion that damages nearby enemies.
 */
public class ExplosiveTotemItem extends SingleUseTotemItem {
    private static final float EXPLOSION_POWER = 5.0F; // Moderate explosion

    public ExplosiveTotemItem(Settings settings) {
        super(settings);
    }

    public static boolean useTotem(ItemStack stack, LivingEntity entity) {
        // Apply base totem effects
        applyBaseEffects(entity, stack);

        // Create and trigger explosion
        Explosion explosion = new Explosion(entity.getWorld(), entity,
                entity.getX(), entity.getY(), entity.getZ(),
                EXPLOSION_POWER, false, Explosion.DestructionType.DESTROY);
        explosion.collectBlocksAndDamageEntities();
        explosion.affectWorld(true);

        stack.decrement(1);
        return true;
    }
}