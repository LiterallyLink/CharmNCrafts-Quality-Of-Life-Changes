package com.charmed.charmncraft;

import com.charmed.charmncraft.item.OrbVialItem;
import com.charmed.charmncraft.item.XPVialItem;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class CharmNCraftClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Register model predicates for vial fill levels using capacity from items
        registerVialFillLevel(ModItems.ORB_VIAL, ((OrbVialItem) ModItems.ORB_VIAL).getCapacity());
        
        // Get capacity from actual vial items
        XPVialItem smallVial = (XPVialItem) ModItems.XP_VIAL_SMALL;
        XPVialItem mediumVial = (XPVialItem) ModItems.XP_VIAL_MEDIUM;
        XPVialItem largeVial = (XPVialItem) ModItems.XP_VIAL_LARGE;
        
        registerVialFillLevel(ModItems.XP_VIAL_SMALL, smallVial.getCapacity());
        registerVialFillLevel(ModItems.XP_VIAL_MEDIUM, mediumVial.getCapacity());
        registerVialFillLevel(ModItems.XP_VIAL_LARGE, largeVial.getCapacity());
    }

    /**
     * Registers model predicate for vial fill level based on stored XP and capacity
     * @param item The vial item to register
     * @param capacity The maximum XP capacity of the vial
     */
    private void registerVialFillLevel(net.minecraft.item.Item item, int capacity) {
        ModelPredicateProviderRegistry.register(item, new Identifier("fill"), (stack, world, entity, seed) -> {
            int storedXP = stack.getOrCreateNbt().getInt("StoredXP");
            return Math.min((float) storedXP / capacity, 1.0f);
        });
    }
}
