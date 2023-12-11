package com.natamus.randombonemealflowers.forge.events;

import com.natamus.collective.functions.WorldFunctions;
import com.natamus.randombonemealflowers.events.FlowerEvent;
import com.natamus.randombonemealflowers.util.Util;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeFlowerEvent {
    @SubscribeEvent
    public void onWorldLoad(LevelEvent.Load e) {
        Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
        if (level == null) {
            return;
        }

		Util.attemptFlowerlistProcessing(level);
    }

	@SubscribeEvent
	public void onBonemeal(BonemealEvent e) {
		FlowerEvent.onBonemeal(e.getLevel(), e.getPos(), null, e.getStack());
	}
}
