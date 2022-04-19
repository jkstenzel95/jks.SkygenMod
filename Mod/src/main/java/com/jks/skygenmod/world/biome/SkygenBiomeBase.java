package com.jks.skygenmod.world.biome;

import javax.annotation.Nonnull;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public class SkygenBiomeBase {

	private final Biome baseBiome;
	private final ResourceLocation baseBiomeResLoc;
    private final int baseBiomeId;

	public SkygenBiomeBase(@Nonnull final Biome baseBiome) {
		ResourceLocation resloc = baseBiome.getRegistryName();
		this.baseBiome = baseBiome;
        this.baseBiomeResLoc = resloc;
        this.baseBiomeId = Biome.getIdForBiome(baseBiome);
	}
	
	public final Biome baseBiome() {
        return baseBiome;
    }
}
