package com.jks.skygenmod.proxy.modinterop.chunks;

import net.minecraft.world.World;
import net.minecraft.world.gen.IChunkGenerator;

public interface IBaseChunkGeneratorProxy {
	public IChunkGenerator getBaseChunkGenerator(World world, String generatorOptions);
}
