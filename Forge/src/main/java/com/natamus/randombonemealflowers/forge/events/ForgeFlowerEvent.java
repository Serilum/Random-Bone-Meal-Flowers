package com.natamus.randombonemealflowers.forge.events;

import com.natamus.collective.functions.WorldFunctions;
import com.natamus.randombonemealflowers.util.Util;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeFlowerEvent {
    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load e) {
        Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getWorld());
        if (level == null) {
            return;
        }

		Util.attemptFlowerlistProcessing(level);
    }
}
