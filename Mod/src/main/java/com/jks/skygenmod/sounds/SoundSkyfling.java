package com.jks.skygenmod.sounds;

import com.jks.skygenmod.util.Reference;
import com.jks.skygenmod.util.helpers.RegistryHelper;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class SoundSkyfling extends SoundEvent implements IHasPlaybackInfo {
	private static String sound = "sound_skyfling";

	public SoundSkyfling() {
		super(new ResourceLocation(Reference.MOD_ID, sound));
		RegistryHelper.registerSound(this, sound);
	}

	@Override
	public SoundCategory getCategory() {
		return SoundCategory.AMBIENT;
	}

	@Override
	public float getVolume() {
		return 0.7f;
	}
	
}
