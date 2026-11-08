package com.serilum.randombonemealflowers.util;

import com.natamus.collective.config.DuskConfig;
import com.natamus.collective.config.DuskConfigMigration;
import com.natamus.collective.data.GlobalVariables;
import com.natamus.collective.functions.BlockFunctions;
import com.natamus.collective.functions.ConfigFunctions;
import com.serilum.randombonemealflowers.config.ConfigHandler;
import com.serilum.randombonemealflowers.data.Variables;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;

import java.util.ArrayList;
import java.util.List;

public class Util {
	public static List<Block> allflowers = new ArrayList<Block>();

	public static void attemptFlowerlistProcessing(Level level) {
		if (!Variables.processedBlacklist) {
			setFlowerList(level);
			Variables.processedBlacklist = true;
		}
	}

	public static void setFlowerList(Level level) {
		Registry<Block> blockRegistry = level.registryAccess().lookupOrThrow(Registries.BLOCK);

		allflowers = new ArrayList<Block>();
		for (Block block : blockRegistry) {
			if (block instanceof FlowerBlock || block.defaultBlockState().is(BlockTags.SMALL_FLOWERS)) {
				allflowers.add(block);
			}
		}
	}

	public static Block getRandomFlower(Level level) {
		List<Block> allowedFlowers = new ArrayList<>();
		for (Block flower : allflowers) {
			if (!ConfigFunctions.isInIdList(ConfigHandler.flowerBlacklist, BlockFunctions.getBlockId(level, flower))) {
				allowedFlowers.add(flower);
			}
		}

		if (allowedFlowers.isEmpty()) {
			return null;
		}
		return allowedFlowers.get(GlobalVariables.random.nextInt(allowedFlowers.size()));
	}

	public static void moveBlacklistFileIntoConfig() {
		String disabledFlowers = DuskConfigMigration.takeDisabledIdsFromFile(Reference.MOD_ID, "blacklist.txt");
		if (disabledFlowers == null) {
			return;
		}

		ConfigHandler.flowerBlacklist = disabledFlowers;
		DuskConfig.write(Reference.MOD_ID);
	}
}

