package com.jks.skygenmod.proxy.modinterop.worldtype;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBaseWorldTypeProxy {
	public WorldType initWorldType(WorldType worldType);
	
	public WorldType getInnerWorldType();
	
	public BiomeProvider getBaseBiomeProvider(WorldType worldType, World world);

	public IChunkGenerator getChunkGenerator(World world, String generatorOptions);

	public boolean isCustomizable(WorldType worldType);

	@SideOnly(Side.CLIENT)
	public void onCustomizeButton(WorldType worldType, Minecraft mc, GuiCreateWorld guiCreateWorld);
}
