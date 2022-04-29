package com.jks.skygenmod.objects.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.jks.skygenmod.Main;
import com.jks.skygenmod.util.helpers.SkyflingHelper;
import com.jks.skygenmod.world.gen.structures.WorldGenSkyblockStructure;

public class BlockSkyfling extends BlockBase {
	private final WorldGenSkyblockStructure worldGenSkyblockStructure = new WorldGenSkyblockStructure();

	public BlockSkyfling() {
		super("block_skyfling", Material.ROCK);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		BlockPos flingPos = SkyflingHelper.getSkyflingPositionAttempt(500, worldGenSkyblockStructure);
		Main.getLogger().info("Will fling to: {}, {}, {}", flingPos.getX(), flingPos.getY(), flingPos.getZ());
        return true;
    }
}
