package com.natamus.randombonemealflowers.forge.events;

import com.natamus.collective.functions.WorldFunctions;
import com.natamus.randombonemealflowers.events.FlowerEvent;
import com.natamus.randombonemealflowers.util.Util;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeFlowerEvent {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeFlowerEvent.class);

		LevelEvent.Load.BUS.addListener(ForgeFlowerEvent::onWorldLoad);
	}

    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load e) {
        Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
        if (level == null) {
            return;
        }

		Util.attemptFlowerlistProcessing(level);
    }
}
