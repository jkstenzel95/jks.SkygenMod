package com.jks.skygenmod.proxy.modinterop.chunks;

import com.jks.skygenmod.SkygenMod;

import biomesoplenty.common.world.ChunkGeneratorOverworldBOP;
import net.minecraft.world.World;
import net.minecraft.world.gen.IChunkGenerator;

public class BOPBaseChunkGeneratorProxy implements IBaseChunkGeneratorProxy {

	@Override
	public IChunkGenerator getBaseChunkGenerator(World world, String generatorOptions) {
		SkygenMod.getLogger().info("Yo what the FUCK is up, Arby's?");
		return new ChunkGeneratorOverworldBOP(world, world.getSeed(), false,
				generatorOptions);
	}
	
}
