package com.jks.skygenmod.commands.util;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.commons.lang3.tuple.Triple;

import com.google.common.collect.Lists;
import com.jks.skygenmod.Main;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class GenerateSky {
	private final static int chunkSize = 16;
	
	public static void generate(World world, int distance) {
		WorldBorder border = world.getWorldBorder();
		strategy1(world, distance);
	}
	
	public static void strategy1(World world, int distance) {
		Instant starts = Instant.now();
		for (int x = -distance; x <= distance; x++) {
			for (int z = -distance; z <= distance; z++) {
				for (int y = 0; y < world.getActualHeight(); y++) {
					// Main.getLogger().info("AIR {}, {}, {}", x, y, z);
					world.setBlockToAir(new BlockPos(x, y, z));
				}
			}
		}
		Instant ends = Instant.now();
		Main.getLogger().info("{} elapsed to delete {} x {} blocks)", Duration.between(starts, ends).toString(), 2*distance + 1, 2*distance + 1);
	}
	
	public static void strategy2(World world, int distance) {
		if (!world.isRemote)
		{
			for (int x = getChunkStartBound(-distance); x < getChunkStartBound(distance + 1) + chunkSize; x += chunkSize)
			{
				for (int z = getChunkStartBound(-distance); z < getChunkStartBound(distance + 1) + chunkSize; z += chunkSize)
				{
					clearChunkWithinBounds(world, x, z, distance);
				}
			}
			
			int totalBlocks = 0;
			int totalAir = 0;
			for (int x = -distance; x < distance+1; x++)
			{
				for (int z = -distance; z < distance+1; z++)
				{
					for (int y = 0; y < world.getActualHeight() + 1; y++)
					{
						totalBlocks++;
						if (Block.isEqualTo(world.getBlockState(new BlockPos(x,y,z)).getBlock(), Blocks.AIR));
						{
							totalAir++;
						}
					}
				}
			}
			
			Main.getLogger().info("{} / {} airified in world, total", totalAir, totalBlocks);
		}
	}
	
	private static void clearChunkWithinBounds(World world, int chunkStartX, int chunkStartZ, int distance)
	{
		// World coordinates:
		int chunkEndX = chunkStartX + chunkSize;
		int chunkEndZ = chunkStartZ + chunkSize;
		
		// the higher number between 0 and
		
		int startWipeX = (-distance > chunkStartX) ? Math.floorMod(-distance, chunkSize) : 0;
		int endWipeX = (distance+1 < chunkEndX) ? Math.floorMod(distance+1, chunkSize) : chunkSize;
		int startWipeZ = (-distance > chunkStartZ) ? Math.floorMod(-distance, chunkSize) : 0;
		int endWipeZ = (distance+1 < chunkEndZ) ? Math.floorMod(distance+1, chunkSize) : chunkSize;
		
		List<Triple<Integer, Integer, Integer>> airified = Lists.newArrayList();
		
		Chunk chunk = world.getChunkFromChunkCoords(chunkStartX, chunkStartZ);
		
		for (ExtendedBlockStorage storage : chunk.getBlockStorageArray()) 
	    {
	        if (storage != null) 
	        {
	            for (int wipeX = startWipeX; wipeX < endWipeX; ++wipeX) 
	            {
	                for (int wipeZ = startWipeZ; wipeZ < endWipeZ; ++wipeZ) 
	                {
	                    for (int wipeY = 0; wipeY < chunkSize; ++wipeY) 
	                    {
	                    	//logger.info("<< JKS SKYGEN >> REWRITING BLOCK AT X:{}, Y: {}, Z: {}", x, y, z);
	                    	airified.add(Triple.of(wipeX,wipeY,wipeZ));
	                    	storage.set(wipeX, wipeY, wipeZ, Blocks.AIR.getDefaultState());
	                    }
	                }
	            }
	        }
	    }
		
		int storageCounter = 0;
		int blockCounter = airified.size();
		for (Triple<Integer, Integer, Integer> t : airified)
		{
			if (Block.isEqualTo(chunk.getBlockState(t.getLeft(), t.getMiddle(), t.getRight()).getBlock(), Blocks.AIR));
			{
				storageCounter++;
			}
		}
		
		Main.getLogger().info("CHUNK DELETED x:[{},{}), z:[{},{}). STATS:", chunkStartX, chunkEndX, chunkStartZ, chunkEndZ);
		Main.getLogger().info("distance: {}", distance);
		Main.getLogger().info("swx:{}, ewx:{}, swz:{}, ewz:{}", startWipeX, endWipeX, startWipeZ, endWipeZ);
		Main.getLogger().info("pre: {} / {} airified storage, pre", storageCounter, blockCounter);
		
	    chunk.setModified(true); // this is important as it marks it to be saved
	    
	    
	    storageCounter = 0;
		for (Triple<Integer, Integer, Integer> t : airified)
		{
			if (Block.isEqualTo(chunk.getBlockState(t.getLeft(), t.getMiddle(), t.getRight()).getBlock(), Blocks.AIR));
			{
				storageCounter++;
			}
		}
		
		Main.getLogger().info("pre: {} / {} airified storage, post", storageCounter, blockCounter);
	}
	
	// Provided an x or z coordinate, returns the coordinate of the start of the chunk
	private static int getChunkStartBound(int v)
	{
		return v - Math.floorMod(v, chunkSize);
	}
}
