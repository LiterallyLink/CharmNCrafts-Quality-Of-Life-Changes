package com.charmed.charmncraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;

/**
 * Loyal Totem - Uses amethyst blocks as a consumable. If you have an amethyst block in your inventory,
 * the totem procs and consumes an amethyst block instead of itself (and glows).
 * If you don't have an amethyst block, the totem procs and disappears (gets consumed, no glow).
 */
public class LoyalTotemItem extends SingleUseTotemItem {
    public LoyalTotemItem(Settings settings) {
        super(settings);
    }

    public static boolean useTotem(ItemStack stack, LivingEntity entity) {
        // Apply base totem effects
        applyBaseEffects(entity, stack);

        // Check if player has an amethyst block
        boolean hasAmethystBlock = false;
        if (entity instanceof ServerPlayerEntity player) {
            // Check main inventory and offhand
            hasAmethystBlock = consumeAmethystBlock(player);
        }

        // If no amethyst block was found, consume the totem instead
        if (!hasAmethystBlock) {
            stack.decrement(1);
        }
        // If amethyst block exists, we already decremented it above, so totem stays

        return true;
    }

    /**
     * Efficiently searches for and consumes an amethyst block from inventory or offhand.
     */
    private static boolean consumeAmethystBlock(ServerPlayerEntity player) {
        // Check main inventory
        for (ItemStack invStack : player.getInventory().main) {
            if (invStack.getItem() == Items.AMETHYST_BLOCK) {
                invStack.decrement(1);
                return true;
            }
        }

        // Check offhand
        ItemStack offHandStack = player.getInventory().offHand.get(0);
        if (offHandStack.getItem() == Items.AMETHYST_BLOCK) {
            offHandStack.decrement(1);
            return true;
        }

        return false;
    }
}
