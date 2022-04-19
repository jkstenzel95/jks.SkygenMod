package com.jks.skygenmod.world.gen;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.IChunkGenerator;

public class SkygenChunkGenerator implements IChunkGenerator {
	private IChunkGenerator baseGenerator;
	private final Random rand;
	private final World world;
	private final boolean mapFeaturesEnabled;
	
	public SkygenChunkGenerator(IChunkGenerator baseGenerator, World worldIn, boolean mapFeaturesEnabledIn) {
		this.baseGenerator = baseGenerator;
		this.world = worldIn;
		this.mapFeaturesEnabled = mapFeaturesEnabledIn;
		this.rand = new Random(world.getSeed());
	}
	
	@Override
	public Chunk generateChunk(int x, int z) {
		Chunk chunk = baseGenerator.generateChunk(x, z);
		clearChunkSetArrayMethod(chunk.getWorld(), chunk);
		return chunk;
	}

	@Override
	public void populate(int x, int z) {
		baseGenerator.populate(x, z);
		clearChunkSetArrayMethod(world, world.getChunkFromChunkCoords(x, z));
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return baseGenerator.getPossibleCreatures(creatureType, pos);
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private static void clearChunkSetArrayMethod(World world, Chunk chunk) {
		chunk.setStorageArrays(new ExtendedBlockStorage[16]);
		chunk.setModified(true);
		// chunk.setTerrainPopulated(false);
	}
}
