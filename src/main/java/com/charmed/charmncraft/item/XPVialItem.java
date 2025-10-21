package com.charmed.charmncraft.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class XPVialItem extends Item {
    private static final String NBT_KEY = "StoredXP";
    private final int capacity;

    public XPVialItem(Settings settings, int capacity) {
        super(settings);
        this.capacity = capacity;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        
        if (world.isClient) {
            return TypedActionResult.pass(stack);
        }

        int storedXP = stack.getOrCreateNbt().getInt(NBT_KEY);
        
        if (storedXP > 0) {
            // Release stored XP
            user.addExperience(storedXP);
            stack.getOrCreateNbt().putInt(NBT_KEY, 0);
            return TypedActionResult.success(stack);
        }
        
        // Store player XP
        int playerXP = user.totalExperience;
        int xpToStore = Math.min(playerXP, capacity);
        
        if (xpToStore > 0) {
            user.addExperience(-xpToStore);
            stack.getOrCreateNbt().putInt(NBT_KEY, xpToStore);
            return TypedActionResult.success(stack);
        }
        
        return TypedActionResult.pass(stack);
    }

    public int getCapacity() {
        return capacity;
    }
}
