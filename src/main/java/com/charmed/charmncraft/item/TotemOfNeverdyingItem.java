package com.charmed.charmncraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.item.Item;

/**
 * Totem of Neverdying - Multi-use totem with 3 lives.
 * Each time held in mainhand/offhand and takes fatal damage, uses one life.
 * When all 3 lives are consumed, the totem is destroyed.
 */
public class TotemOfNeverdyingItem extends SingleUseTotemItem {
    private static final String LIVES_KEY = "Lives";
    private static final int MAX_LIVES = 3;

    public TotemOfNeverdyingItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean useTotem(ItemStack stack, LivingEntity entity) {
        // Get current lives, default to MAX_LIVES if not set
        int lives = getLives(stack);

        // Apply base totem effects
        applyBaseEffects(entity, stack);

        // Decrement life count
        lives--;
        setLives(stack, lives);

        // If no lives left, consume the totem
        if (lives <= 0) {
            stack.decrement(1);
        }

        return true;
    }

    /**
     * Gets the remaining lives from NBT data. Defaults to MAX_LIVES for new totems.
     */
    private int getLives(ItemStack stack) {
        NbtCompound tag = stack.getOrCreateNbt();
        int lives = tag.getInt(LIVES_KEY);
        // If lives is 0 (unset), default to MAX_LIVES
        return lives == 0 ? MAX_LIVES : lives;
    }

    /**
     * Sets the remaining lives in NBT data.
     */
    private void setLives(ItemStack stack, int lives) {
        stack.getOrCreateNbt().putInt(LIVES_KEY, Math.max(0, lives));
    }

    /**
     * Display remaining lives in the item's name on hover.
     */
    @Override
    public Text getName(ItemStack stack) {
        int lives = getLives(stack);
        return Text.literal(super.getName(stack).getString() + " [" + lives + "/3]");
    }
}
