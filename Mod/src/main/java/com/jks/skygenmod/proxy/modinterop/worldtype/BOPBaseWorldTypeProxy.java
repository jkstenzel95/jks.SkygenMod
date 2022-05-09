package com.jks.skygenmod.proxy.modinterop.worldtype;

import com.jks.skygenmod.util.Reference;

import biomesoplenty.client.gui.GuiBOPConfigureWorld;
import biomesoplenty.common.world.BiomeProviderBOP;
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

public class BOPBaseWorldTypeProxy implements IBaseWorldTypeProxy {
	@Override
	public WorldType initWorldType(WorldType worldType) {
		worldType.hasInfoNotice();
		return worldType;
	}
	
	@Override
	public WorldType getInnerWorldType() {
		return new WorldTypeBOP();
	}
	
	@Override
	public BiomeProvider getBaseBiomeProvider(WorldType worldType, World world) {
		return new BiomeProviderBOP(world.getSeed(), worldType, world.getWorldInfo().getGeneratorOptions());
	}

	@Override
	public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
		return new ChunkGeneratorOverworldBOP(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(),
				generatorOptions);
	}

	@Override
	public boolean isCustomizable(WorldType worldType) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void onCustomizeButton(WorldType worldType, Minecraft mc, GuiCreateWorld guiCreateWorld) {
		mc.displayGuiScreen(new GuiBOPConfigureWorld(guiCreateWorld, guiCreateWorld.chunkProviderSettingsJson));
	}
}
