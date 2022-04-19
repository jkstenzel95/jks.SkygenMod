package com.jks.skygenmod.world.gen;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jks.skygenmod.Main;
import com.jks.skygenmod.util.Reference;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.MapGenBase;
import net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent.ReplaceBiomeBlocks;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.BlockEvent.CreateFluidSourceEvent;
import net.minecraftforge.event.world.BlockEvent.FluidPlaceBlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

// @EventBusSubscriber
public class WorldGenHandler {
	// @SubscribeEvent(priority=EventPriority.LOWEST, receiveCanceled=true)
	public static void onPopulate(PopulateChunkEvent.Populate event)
	{
		Logger logger = LogManager.getLogger(Reference.MOD_ID);
		logger.info("<< JKS SKYGEN >> BLOCK POPULATE OF TYPE {}", event.getType().name());
	    
		if (!event.getWorld().isRemote)
		{
			clearChunkSetArrayMethod(
					event.getWorld(),
					event.getWorld().getChunkFromChunkCoords(event.getChunkX(), event.getChunkZ())
				);
		    event.setResult(Result.DENY);
		}
	}
	
	// @SubscribeEvent(priority=EventPriority.LOWEST, receiveCanceled=true)
	public static void onEvent(PopulateChunkEvent.Post event)
	{
		Logger logger = LogManager.getLogger(Reference.MOD_ID);
		logger.info("<< JKS SKYGEN >> PopulateChunk rewrite");
	    
		clearChunkSetArrayMethod(event.getWorld(), event.getWorld().getChunkFromChunkCoords(event.getChunkX(), event.getChunkZ()));
	}
	
	// @SubscribeEvent(priority=EventPriority.LOWEST, receiveCanceled=true)
	public static void onDecorateBiome(DecorateBiomeEvent.Decorate event)
	{
		Logger logger = LogManager.getLogger(Reference.MOD_ID);
		logger.info("<< JKS >> onDecorateBiome: {}", event.getType().name());
		if (!event.getWorld().isRemote)
		{
			/*
			 * clearChunkSetArrayMethod( event.getWorld(),
			 * event.getWorld().getChunkFromChunkCoords(event.getChunkPos().getXStart(),
			 * event.getChunkPos().getZStart()) );
			 */
			event.setResult(Result.DENY);
		}
	}
	
	// @SubscribeEvent(priority=EventPriority.LOWEST, receiveCanceled=true)
	public static void onInitMapGen(InitMapGenEvent event)
	{
		Logger logger = LogManager.getLogger(Reference.MOD_ID);
		logger.info("<< JKS >> onInitMapGen: {}", event.getType().name());
		event.setResult(Result.DENY);
	}
	
	private static void clearChunkStorageMethod(World world, Chunk chunk) {
		if (!world.isRemote)
		{
		    for (ExtendedBlockStorage storage : chunk.getBlockStorageArray()) 
		    {
		        if (storage != null) 
		        {
		            for (int x = 0; x < 16; ++x) 
		            {
		                for (int y = 0; y < 16; ++y) 
		                {
		                    for (int z = 0; z < 16; ++z) 
		                    {
		                    	//logger.info("<< JKS SKYGEN >> REWRITING BLOCK AT X:{}, Y: {}, Z: {}", x, y, z);
		                    	storage.set(x, y, z, Blocks.AIR.getDefaultState());
		                    }
		                }
		            }
		        }
		    }
		    
		    chunk.setModified(true); // this is important as it marks it to be saved
		}
	}
	
	public static void clearChunkSetArrayMethod(World world, Chunk chunk) {
		chunk.setStorageArrays(new ExtendedBlockStorage[16]);
		chunk.setModified(true);
		// chunk.setTerrainPopulated(false);
	}
	
	public static void clearChunkWorldMethod(World world, Chunk chunk) {
		Instant starts = Instant.now();
		int cleared = 0;
		int wx = chunk.x * 16;
		int wz = chunk.z * 16;
		for (int x = wx; wx <= wx + 16; x++) {
			for (int z = wz; wz <= wz + 16; z++) {
				for (int y = 0; y < world.getActualHeight(); y++) {
					// Main.getLogger().info("AIR {}, {}, {}", x, y, z);
					world.setBlockToAir(new BlockPos(x, y, z));
					cleared++;
				}
			}
		}
		
		Instant ends = Instant.now();
		Main.getLogger().info("{} elapsed, {} cleared for {},{}", Duration.between(starts, ends).toString(), cleared, chunk.x, chunk.z);
	}
}
