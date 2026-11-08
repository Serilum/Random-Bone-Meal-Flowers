package com.serilum.randombonemealflowers.config;

import com.natamus.collective.config.DuskConfig;
import com.serilum.randombonemealflowers.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<>();

	@Entry(registry = "block", filter = "flowers") public static String flowerBlacklist = "minecraft:wither_rose,betterarcheology:growth_totem";

	public static void initConfig() {
		configMetaData.put("flowerBlacklist", Arrays.asList(
			"A comma separated list of flower ids that bone meal never grows. Use * as a wildcard."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}
