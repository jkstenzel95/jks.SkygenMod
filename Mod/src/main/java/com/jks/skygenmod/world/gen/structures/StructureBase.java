package com.jks.skygenmod.world.gen.structures;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import com.jks.skygenmod.Main;
import com.jks.skygenmod.util.Reference;
import com.jks.skygenmod.util.interfaces.IStructure;

import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public abstract class StructureBase implements IStructure {
	private static Template structureTemplate = null;
	
	public boolean generate(World world, BlockPos placementOrigin) {
		ChunkPos chunkPos = world.getChunkFromBlockCoords(new BlockPos(placementOrigin.getX(), placementOrigin.getY(), placementOrigin.getZ())).getPos(); 
		if (world.provider.getDimension() == 0
				&& structureSitsInChunk(world, placementOrigin, getName())) {
			placeStructure(world, placementOrigin, getName());
			Main.getLogger().info("SKYBLOCK PLACED FOR POS {} {}", placementOrigin.getX(), placementOrigin.getZ());
			return true;
		}
		
		return false;
	}
	
	public abstract String getName();
	public abstract int getOffsetX();
	public abstract int getOffsetY();
	public abstract int getOffsetZ();
	
	public BlockPos getPlacementPos(BlockPos pos) {
		int x = pos.getX() + this.getOffsetX();
		int y = pos.getY() + this.getOffsetY();
		int z = pos.getZ() + this.getOffsetZ();
		return new BlockPos(x, y, z);
	}
	
	public void placeStructure(World world, BlockPos placementOrigin, String structureName) {
		BlockPos pos = getPlacementPos(placementOrigin);		
		Template template = getTemplate(world, structureName);
		
		if (template != null) {
			IBlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			template.addBlocksToWorldChunk(world, pos, settings);
		}
	}
	
	private Template getTemplate(World world, String structureName) {
		if (this.structureTemplate == null) {
			MinecraftServer mcServer = world.getMinecraftServer();
			TemplateManager manager = worldServer.getStructureTemplateManager();
			ResourceLocation location = new ResourceLocation(Reference.MOD_ID, structureName);
			structureTemplate = manager.get(mcServer, location);
		}
		
		return structureTemplate;
	}
	
	public Set<Pair<Integer,Integer>> getChunksForStructure(World world, BlockPos placementOrigin, String structureName) {
		Template template = getTemplate(world, structureName);
		Set<Pair<Integer,Integer>> contained = new HashSet<Pair<Integer,Integer>>();
		if (template == null) {
			return contained;
		}
		
		BlockPos spos = getPlacementPos(placementOrigin);
		BlockPos templateSize = template.getSize();
		int sXMin = spos.getX();
		int sZMin = spos.getZ();
		int sXMax = sXMin + templateSize.getX();
		int sZMax = sZMin + templateSize.getZ();
		
		Chunk min = world.getChunkFromBlockCoords(new BlockPos(sXMin, 0, sZMin));
		Chunk max = world.getChunkFromBlockCoords(new BlockPos(sXMax-1, 0, sZMax-1));
		
		for (int cx = min.x; cx <= max.x; cx++) {
			for (int cz = min.z; cz <= max.z; cz++) {
				contained.add(Pair.of(cx, cz));
			}
		}
		
		return contained;
	}
	
	public boolean structureSitsInChunk(World world, BlockPos placementOrigin, String structureName) {
		ChunkPos chunkPos = world.getChunkFromBlockCoords(new BlockPos(placementOrigin.getX(), placementOrigin.getY(), placementOrigin.getZ())).getPos();
		return getChunksForStructure(world, placementOrigin, structureName).contains(Pair.of(chunkPos.x, chunkPos.z));
	}
}
