package com.jks.skygenmod.sounds;

import net.minecraft.util.SoundCategory;

public interface IHasPlaybackInfo {
	public abstract SoundCategory getCategory();
	public abstract float getVolume();
}
