package com.natamus.randombonemealflowers.events;

import com.natamus.randombonemealflowers.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.TickTask;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlowerEvent {
	public static void onBonemeal(Level world, BlockPos pos, BlockState state, ItemStack stack) {
		if (world.isClientSide) {
			return;
		}

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		List<Block> oldblocks = new ArrayList<Block>();
		Iterator<BlockPos> it = BlockPos.betweenClosedStream(x-6, y, z-6, x+6, y+1, z+6).iterator();
		while (it.hasNext()) {
			BlockPos bp = it.next();
			Block block = world.getBlockState(bp).getBlock();
			oldblocks.add(block);
		}

		world.getServer().execute(new TickTask(world.getServer().getTickCount(), () -> {
			Iterator < BlockPos > newit = BlockPos.betweenClosedStream(x - 6, y, z - 6, x + 6, y + 1, z + 6).iterator();
			while (newit.hasNext()) {
				BlockPos bp = newit.next();
				Block block = world.getBlockState(bp).getBlock();
				if (Util.allflowers.contains(block) && !Util.allflowers.contains(oldblocks.get(0))) {
					Block randomflower = Util.getRandomFlower();

					world.setBlockAndUpdate(bp, randomflower.defaultBlockState());
				}

				oldblocks.remove(0);
			}
		}));
	}
}
