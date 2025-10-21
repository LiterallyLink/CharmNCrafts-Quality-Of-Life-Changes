package com.charmed.charmncraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

/**
 * Totem of the Void - Only activates when taking out-of-world (void) damage.
 * Teleports the player directly to world spawn and prevents fall damage on landing.
 * 
 * The activation check is handled in LivingEntityMixin to ensure we only
 * trigger this totem when actual void damage is taken.
 */
public class VoidTotemItem extends SingleUseTotemItem {
    private static final int FALL_DAMAGE_IMMUNITY_DURATION = 100; // 5 seconds
    
    public VoidTotemItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean useTotem(ItemStack stack, LivingEntity entity) {
        entity.setHealth(1.0F);

        entity.clearStatusEffects();
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0));
        // Add slow falling to prevent fall damage when landing
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, FALL_DAMAGE_IMMUNITY_DURATION, 0));
        entity.getWorld().sendEntityStatus(entity, (byte) 35);

        if (entity instanceof ServerPlayerEntity player) {
            player.incrementStat(Stats.USED.getOrCreateStat((Item) stack.getItem()));

            Vec3d spawnPos = Vec3d.ofCenter(player.getWorld().getSpawnPos());

            player.teleport(spawnPos.x, spawnPos.y, spawnPos.z);

            // Reset fall distance to prevent any accumulated fall damage
            player.fallDistance = 0.0F;
        }

        stack.decrement(1);

        return true;
    }
}
