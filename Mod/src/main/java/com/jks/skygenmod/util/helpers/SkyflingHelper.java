package com.jks.skygenmod.util.helpers;

import java.util.Random;

import com.jks.skygenmod.SkygenMod;
import com.jks.skygenmod.sounds.SoundSkyfling;
import com.jks.skygenmod.util.Registries;
import com.jks.skygenmod.world.gen.structures.StructureBase;
import com.jks.skygenmod.world.gen.structures.WorldGenSkyblockStructure;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class SkyflingHelper {
	private static StructureBase skyflingDestinationStructure = new WorldGenSkyblockStructure();
	
	public static void doSkyfling(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if(!worldIn.isRemote) {
			BlockPos flingPos = SkyflingHelper.getSkyflingPositionAttempt(500, skyflingDestinationStructure);
			SkygenMod.getLogger().info("Will fling to: {}, {}, {}", flingPos.getX(), flingPos.getY(), flingPos.getZ());
			Chunk chunk = worldIn.getChunkFromBlockCoords(flingPos);
			worldIn.getChunkProvider().getLoadedChunk(chunk.x, chunk.z);
			skyflingDestinationStructure.generate(worldIn, flingPos);
			playerIn.setPositionAndUpdate(flingPos.getX(), flingPos.getY(), flingPos.getZ());
			SoundSkyfling sound = (SoundSkyfling)Registries.SOUND_SKYFLING;
			worldIn.playSound(playerIn, flingPos, sound, sound.getCategory(), sound.getVolume(), 1);
		}
    }
	
	public static BlockPos getSkyflingPositionAttempt(int boundary, StructureBase structure) {
		Random random = new Random();
		int minX = Math.max(structure.getOffsetX() + -boundary, -boundary);
		int maxX = Math.min(structure.getOffsetX() + boundary, boundary);
		int minY = Math.max(structure.getOffsetY(), 0);
		int maxY = Math.min(structure.getOffsetY() + 255, 255);
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