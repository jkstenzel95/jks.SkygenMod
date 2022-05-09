package com.jks.skygenmod.world.gen;

import com.jks.skygenmod.SkygenMod;
import com.jks.skygenmod.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldTypeSkygen extends WorldType {
	private static WorldType innerWorldType;
	
	public WorldTypeSkygen()
	{
		super(Reference.SKYGEN_WORLD_NAME);
		SkygenMod.baseWorldTypeProxy.initWorldType(this);
		innerWorldType = SkygenMod.baseWorldTypeProxy.getInnerWorldType();
	}
	
	@Override
	public BiomeProvider getBiomeProvider(World world) {
		return SkygenMod.baseWorldTypeProxy.getBaseBiomeProvider(innerWorldType, world);
	}
	
	@Override
	public boolean isCustomizable() {
		return SkygenMod.baseWorldTypeProxy.isCustomizable(this);
	}
	
	@Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions)
    {
		SkygenMod.getLogger().info(generatorOptions);
		return new SkygenChunkGenerator(
				SkygenMod.baseChunkGeneratorProxy.getBaseChunkGenerator(world, generatorOptions),
				world,
				world.getWorldInfo().isMapFeaturesEnabled());
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onCustomizeButton(Minecraft mc, GuiCreateWorld guiCreateWorld) {
		// TODO Auto-generated method stub
		SkygenMod.baseWorldTypeProxy.onCustomizeButton(this, mc, guiCreateWorld);
	}
}