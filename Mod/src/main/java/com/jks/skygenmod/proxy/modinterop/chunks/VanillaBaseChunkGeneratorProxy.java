package com.jks.skygenmod.proxy.modinterop.chunks;

import com.jks.skygenmod.SkygenMod;
import com.jks.skygenmod.world.gen.SkygenChunkGenerator;

import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;

public class VanillaBaseChunkGeneratorProxy implements IBaseChunkGeneratorProxy {
	@Override
	public IChunkGenerator getBaseChunkGenerator(World world, String generatorOptions)
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
