package com.jks.skygenmod.world.biome;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public interface ISkygenBiome {
	Biome getBaseBiome();

    ResourceLocation getBaseBiomeResLoc();

    int getBaseBiomeId();
}
