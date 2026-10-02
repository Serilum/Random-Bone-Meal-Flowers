package com.serilum.randombonemealflowers.forge.events;

import com.natamus.collective.functions.WorldFunctions;
import com.serilum.randombonemealflowers.util.Util;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeFlowerEvent {
	@SubscribeEvent
	public static void onWorldLoad(LevelEvent.Load e) {
		Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
		if (level == null) {
			return;
		}

		Util.attemptFlowerlistProcessing(level);
	}
}
