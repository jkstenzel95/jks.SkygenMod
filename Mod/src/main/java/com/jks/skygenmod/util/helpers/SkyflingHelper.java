package com.jks.skygenmod.util.helpers;

import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import com.jks.skygenmod.SkygenConfig;
import com.jks.skygenmod.SkygenMod;
import com.jks.skygenmod.data.ChunkTaintManager;
import com.jks.skygenmod.sounds.SoundSkyfling;
import com.jks.skygenmod.util.Registries;
import com.jks.skygenmod.world.gen.structures.StructureBase;
import com.jks.skygenmod.world.gen.structures.WorldGenSkyblockStructure;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class SkyflingHelper {
	public static void doSkyfling(World worldIn, EntityPlayer playerIn) {
		if(!worldIn.isRemote) {
			BlockPos flingPos = SkyflingHelper.getSkyflingPositionAttempt(SkygenConfig.initialGenBounds, WorldGenSkyblockStructure.instance);
			SkygenMod.getLogger().info("Will fling to: {}, {}, {}", flingPos.getX(), flingPos.getY(), flingPos.getZ());
			Set<Pair<Integer, Integer>> chunkCoords = WorldGenSkyblockStructure.instance.getChunksForStructure(worldIn, flingPos, WorldGenSkyblockStructure.instance.getName());
			for (Pair<Integer, Integer> pair : chunkCoords) {
				SkygenMod.getLogger().info("Loading for Skyfling: {}, {}", pair.getLeft(), pair.getRight());
				Chunk chunk = worldIn.getChunkFromChunkCoords(pair.getLeft(), pair.getRight());
				if (!chunk.isPopulated()) {
					worldIn.getWorldType().getChunkGenerator(worldIn, worldIn.getWorldInfo().getGeneratorOptions()).populate(pair.getLeft(), pair.getRight());
				}
				
				ChunkTaintManager.markChunkTainted(pair.getLeft(), pair.getRight());
			}
			
			Chunk chunk = worldIn.getChunkFromBlockCoords(flingPos);
			worldIn.getWorldInfo().setAdditionalProperties(null);
			ChunkPos centerPos = new ChunkPos(worldIn.getChunkFromBlockCoords(flingPos).x, worldIn.getChunkFromBlockCoords(flingPos).z);
			WorldGenSkyblockStructure.instance.generate(worldIn, flingPos);
			playerIn.setPositionAndUpdate(flingPos.getX(), flingPos.getY(), flingPos.getZ());
			SoundSkyfling sound = (SoundSkyfling)Registries.SOUND_SKYFLING;
			playerIn.playSound(sound, sound.getVolume(), 1);
			SkygenMod.getLogger().info("Skyflung: {}, {} <{}, {}, {}>", centerPos.x, centerPos.z, flingPos.getX(), flingPos.getY(), flingPos.getZ());
		}
    }
	
	public static BlockPos getSkyflingPositionAttempt(int boundary, StructureBase structure) {
		Random random = new Random();
		int minX = Math.max(structure.getOffsetX() + -boundary, -boundary);
		int maxX = Math.min(structure.getOffsetX() + boundary, boundary);
		int minY = Math.max(structure.getOffsetY(), SkygenConfig.minPlacementY);
		int maxY = Math.min(structure.getOffsetY() + SkygenConfig.maxPlacementY, SkygenConfig.maxPlacementY);
		int minZ = Math.max(structure.getOffsetZ() + -boundary, -boundary);
		int maxZ = Math.min(structure.getOffsetZ() + boundary, boundary);
		
		return new BlockPos(
				randomBetween(random, minX, maxX),
				randomBetween(random, minY, maxY),
				randomBetween(random, minZ, maxZ));
	}
	
	private static int randomBetween(Random random, int min, int max) {
		return (random.nextInt((max - min)) + min);
	}
}