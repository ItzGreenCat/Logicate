package dev.onyxcat.logicate;

import dev.onyxcat.logicate.block.BlockManager;
import net.fabricmc.api.ModInitializer;


public class Logicate implements ModInitializer {
    public static final String MODID = "logicate";
    @Override
    public void onInitialize() {
        BlockManager.init();
    }
}
