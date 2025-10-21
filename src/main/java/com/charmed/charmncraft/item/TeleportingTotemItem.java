package com.charmed.charmncraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

/**
 * Teleporting Totem - Teleports to a random safe location within 50 blocks.
 */
public class TeleportingTotemItem extends SingleUseTotemItem {


    public TeleportingTotemItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean useTotem(ItemStack stack, LivingEntity entity) {
        // Apply base totem effects
        applyBaseEffects(entity, stack);

        if (entity instanceof ServerPlayerEntity player) {
            // Teleport exactly 25 blocks away in a random direction
            Vec3d currentPos = entity.getPos();
            double angle = entity.getWorld().random.nextDouble() * 2 * Math.PI;
            double distance = 25.0;

            double x = currentPos.x + Math.cos(angle) * distance;
            double z = currentPos.z + Math.sin(angle) * distance;
            double y = currentPos.y;

            player.teleport(x, y, z);
        }

        stack.decrement(1);
        return true;
    }
}
