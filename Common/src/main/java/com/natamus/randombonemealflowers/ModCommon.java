package com.natamus.randombonemealflowers;


import com.natamus.collective.globalcallbacks.GlobalCropCallback;
import com.natamus.randombonemealflowers.events.FlowerEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ModCommon {

	public static void init() {
		loadEvents();
	}

	private static void loadEvents() {
		GlobalCropCallback.ON_BONE_MEAL_APPLY.register((Player player, Level level, BlockPos blockPos, BlockState blockState, ItemStack itemStack) -> {
			FlowerEvent.onBonemeal(level, blockPos, blockState, itemStack);
			return true;
		});

		GlobalCropCallback.ON_GENERAL_BONE_MEAL_APPLY.register((Level level, BlockPos blockPos, BlockState blockState, ItemStack itemStack) -> {
			FlowerEvent.onBonemeal(level, blockPos, blockState, itemStack);
			return true;
		});
	}
}