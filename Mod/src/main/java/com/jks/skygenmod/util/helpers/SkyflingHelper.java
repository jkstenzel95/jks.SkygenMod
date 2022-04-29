package com.jks.skygenmod.util.helpers;

import java.util.Random;

import com.jks.skygenmod.world.gen.structures.StructureBase;
import com.jks.skygenmod.world.gen.structures.WorldGenSkyblockStructure;

import net.minecraft.util.math.BlockPos;

public class SkyflingHelper {
	private StructureBase skyflingDestinationStructure = new WorldGenSkyblockStructure();
	
	public static BlockPos getSkyflingPositionAttempt(int boundary, StructureBase structure) {
		Random random = new Random();
		int minX = Math.max(structure.getOffsetX() + -boundary, -boundary);
		int maxX = Math.min(structure.getOffsetX() + boundary, boundary);
		int minY = Math.max(structure.getOffsetY(), 0);
		int maxY = Math.min(structure.getOffsetY(), 255);
		int minZ = Math.max(structure.getOffsetZ() + -boundary, -boundary);
		int maxZ = Math.min(structure.getOffsetZ() + boundary, boundary);
		
		return new BlockPos(
				randomBetween(random, minX, maxX),
				randomBetween(random, minY, maxY),
				randomBetween(random, minZ, maxZ));
	}
	
	private static int randomBetween(Random random, int min, int max) {
		return ((random.nextInt() % (max - min)) + min);
	}
}