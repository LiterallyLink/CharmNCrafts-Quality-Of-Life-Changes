package com.charmed.charmncraft;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharmNCraft implements ModInitializer {
    public static final String MOD_ID = "charmncraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[CharmNCraft] Initialize");

        ModBlocks.initialize();
        ModItems.initialize();
        ModEntities.initialize();
    }
}