package com.jks.skygenmod.init;

import java.util.ArrayList;
import java.util.List;

import com.jks.skygenmod.objects.blocks.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block BLOCK_SKYFLING = new BlockBase("block_skyfling", Material.ROCK);
}
