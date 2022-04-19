package com.jks.skygenmod.world.gen;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.logging.log4j.Logger;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.layer.GenLayer;

public class WorldTypeSkygen extends WorldType {
	public WorldTypeSkygen()
	{
		super("Skygen");
	}
	
	@Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions)
    {
		return new SkygenChunkGenerator(
				new ChunkGeneratorOverworld(
						world, 
						world.getSeed(), 
						false, 
						generatorOptions),
				world,
				world.getWorldInfo().isMapFeaturesEnabled());
    }
}