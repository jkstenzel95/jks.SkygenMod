package com.jks.skygenmod.proxy.modinterop.worldtype;

import com.jks.skygenmod.util.Reference;

import biomesoplenty.common.world.ChunkGeneratorOverworldBOP;
import biomesoplenty.common.world.WorldTypeBOP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VanillaBaseWorldTypeProxy implements IBaseWorldTypeProxy {
	@Override
	public WorldType initWorldType(WorldType worldType) {
		return worldType;
	}
	
	@Override
	public WorldType getInnerWorldType() {
		return new WorldType(Reference.SKYGEN_WORLD_NAME);
	}
	
	@Override
	public BiomeProvider getBaseBiomeProvider(WorldType worldType, World world) {
		return worldType.getBiomeProvider(world);
	}

	@Override
	public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
		return new ChunkGeneratorOverworldBOP(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(),
				generatorOptions);
	}

	@Override
	public boolean isCustomizable(WorldType worldType) {
		return worldType.isCustomizable();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void onCustomizeButton(WorldType worldType, Minecraft mc, GuiCreateWorld guiCreateWorld) {
		worldType.onCustomizeButton(mc, guiCreateWorld);
	}
}
