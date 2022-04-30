package com.jks.skygenmod.util;

import java.util.ArrayList;
import java.util.List;

import com.jks.skygenmod.objects.blocks.BlockSkyfling;
import com.jks.skygenmod.sounds.SoundSkyfling;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;

public class Registries {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static final List<SoundEvent> SOUNDS = new ArrayList<SoundEvent>();
	
	public static final Block BLOCK_SKYFLING = new BlockSkyfling();	
	public static final SoundEvent SOUND_SKYFLING = new SoundSkyfling();
}
