package com.charmed.charmncraft.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.Vec3d;

public class IronGolemTotemItem extends SingleUseTotemItem {
    private static final int GOLEM_COUNT = 3;
    private static final double SPAWN_RADIUS = 3.0;

    public IronGolemTotemItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canBeNested() {
        return false;
    }

    @Override
    public boolean useTotem(ItemStack stack, LivingEntity entity) {
        // Apply base totem effects
        applyBaseEffects(entity, stack);

        // Spawn 3 iron golems around the player
        spawnGolems(entity, GOLEM_COUNT, SPAWN_RADIUS);

        stack.decrement(1);
        return true;
    }

    private void spawnGolems(LivingEntity owner, int count, double radius) {
        if (owner.getWorld().isClient) {
            return;
        }

        Vec3d ownerPos = owner.getPos();
        double angleStep = 360.0 / count;

        for (int i = 0; i < count; i++) {
            double angle = Math.toRadians(i * angleStep);
            double x = ownerPos.x + Math.cos(angle) * radius;
            double z = ownerPos.z + Math.sin(angle) * radius;
            double y = ownerPos.y;

            LivingEntity golem = EntityType.IRON_GOLEM.create(owner.getWorld());
            if (golem != null) {
                golem.setPosition(x, y, z);
                owner.getWorld().spawnEntity(golem);
            }
        }
    }
}
