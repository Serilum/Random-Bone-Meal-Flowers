package com.natamus.randombonemealflowers.util;

import com.natamus.collective.data.GlobalVariables;
import com.natamus.collective.functions.DataFunctions;
import com.natamus.randombonemealflowers.data.Variables;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Util {
	public static List<Block> allflowers = new ArrayList<Block>();
	public static List<Block> flowers = new ArrayList<Block>();
	
	private static final String dirpath = DataFunctions.getConfigDirectory() + File.separator + "randombonemealflowers";
	private static final File dir = new File(dirpath);
	private static final File file = new File(dirpath + File.separator + "blacklist.txt");

	public static void attemptFlowerlistProcessing(Level level) {
		if (!Variables.processedBlacklist) {
			try {
				setFlowerList(level);
				Variables.processedBlacklist = true;
			} catch (Exception ex) {
				System.out.println("[" + Reference.NAME + "] Error: Unable to generate flower list.");
			}
		}
	}

	public static void setFlowerList(Level level) throws IOException {
		Registry<Block> blockRegistry = level.registryAccess().registryOrThrow(Registries.BLOCK);
		List<String> blacklist = new ArrayList<String>();

		allflowers = new ArrayList<Block>();
		flowers = new ArrayList<Block>();
		
		PrintWriter writer = null;
		if (!dir.isDirectory() || !file.isFile()) {
			if (dir.mkdirs()) {
				writer = new PrintWriter(dirpath + File.separator + "blacklist.txt", StandardCharsets.UTF_8);
			}
		}
		else {
			String blcontent = new String(Files.readAllBytes(Paths.get(dirpath + File.separator + "blacklist.txt")));
			for (String flowerrl : blcontent.split("," )) {
				String name = flowerrl.replace("\n", "").trim();
				if (name.startsWith("!")) {
					blacklist.add(name.replace("!", ""));
				}
			}
		}
		
		for (Block block : blockRegistry) {
			if (block instanceof FlowerBlock) {
				ResourceLocation rl = blockRegistry.getKey(block);
				if (rl == null) {
					continue;
				}
				
				String name = rl.toString();
				
				allflowers.add(block);
				
				if (writer != null) {
					String prefix = "";
					if (name.equals("minecraft:wither_rose")) {
						blacklist.add(name);
						prefix = "!";
					}

					writer.println(prefix + name + ",");
				}
				
				if (!blacklist.contains(name)) {
					flowers.add(block);
				}
			}
		}
		
		if (writer != null) {
			writer.close();
		}
	}
	
	public static Block getRandomFlower() {
		int x = GlobalVariables.random.nextInt(flowers.size());

		return flowers.get(x);
	}
}
