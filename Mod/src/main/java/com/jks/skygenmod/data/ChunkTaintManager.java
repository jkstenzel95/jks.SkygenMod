package com.jks.skygenmod.data;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.tuple.Pair;

import com.jks.skygenmod.SkygenMod;
import com.jks.skygenmod.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ChunkTaintManager {
	public static final String KEY = Reference.MOD_ID + ":IsTainted";
	private static ConcurrentHashMap<Pair<Integer, Integer>, Boolean> isChunkTainted = new ConcurrentHashMap<Pair<Integer, Integer>, Boolean>();

	@SubscribeEvent
	public void onBlockEvent(BlockEvent.EntityPlaceEvent event) {
		SkygenMod.getLogger().info("onBlockEvent");
		if (event.getEntity() instanceof EntityPlayer) {
			SkygenMod.getLogger().info("It's a player!");
			Chunk chunk = event.getWorld().getChunkFromBlockCoords(event.getPos());
			markChunkTainted(chunk.x, chunk.z);
		}
	}

	@SubscribeEvent
	public void chunkLoad(ChunkDataEvent.Load event) {
		SkygenMod.getLogger().info("load...");
		if (!event.getWorld().isRemote) {
			Chunk chunk = event.getChunk();
			loadIsChunkTainted(chunk.x, chunk.z, event.getData());
		}
	}

	@SubscribeEvent
	public void chunkSave(ChunkDataEvent.Save event) {
		Chunk chunk = event.getChunk();
		if (!event.getWorld().isRemote)
			saveChunkTaintData(chunk.x, chunk.z, event.getData());
	}

	@SubscribeEvent
	public void chunkUnload(ChunkDataEvent.Unload event) {
		Chunk chunk = event.getChunk();
		if (!event.getWorld().isRemote) {
			unloadChunkTaintData(chunk.x, chunk.z);
		}
	}

	public static void markChunkTainted(int x, int z) {
		SkygenMod.getLogger().info("tainting {}, {}", x, z);
		isChunkTainted.put(Pair.of(x, z), true);
	}

	private static boolean loadIsChunkTainted(int x, int z, NBTTagCompound data) {
		Pair<Integer, Integer> chunkKey = Pair.of(x, z);
		if (!isChunkTainted.containsKey(chunkKey)) {
			isChunkTainted.put(chunkKey, data.hasKey(KEY) ? data.getBoolean(KEY) : false);
		}

		SkygenMod.getLogger().info("loadIsChunkTainted {}, {}: {}", x, z, isChunkTainted.get(chunkKey));
		return isChunkTainted.get(chunkKey);
	}

	private static void saveChunkTaintData(int x, int z, NBTTagCompound data) {
		Pair<Integer, Integer> chunkKey = Pair.of(x, z);
		SkygenMod.getLogger().info("saveChunkTaintData {}, {}: {}", x, z, isChunkTainted.get(chunkKey));
		data.setBoolean(KEY, isChunkTainted.get(chunkKey));
	}

	private static void unloadChunkTaintData(int x, int z) {
		Pair<Integer, Integer> chunkKey = Pair.of(x, z);
		SkygenMod.getLogger().info("unloadChunkTaintData {}, {}", x, z);
		isChunkTainted.remove(chunkKey);
	}
}
