package com.jks.skygenmod;

import com.jks.skygenmod.util.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.RangeInt;

@Config(modid = Reference.MOD_ID)
public class SkygenConfig {
	@RangeInt(min = 0)
	public static int initialGenBounds = 384;
	
	@RangeInt(min = 1)
	public static int genBoundsStepSize = 128;
	
	@RangeInt(min = 0)
	public static int blocksSeparation = 128;
	
	@RangeInt(min = 0, max = 10)
	public static int placementAttempts = 1;
	
	@RangeInt(min = 0, max = 255)
	public static int minPlacementY = 64;
	
	@RangeInt(min = 0, max = 255)
	public static int maxPlacementY = 127;
	
	public static boolean useSpawnHub = true;
}
