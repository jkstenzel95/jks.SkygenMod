package com.jks.skygenmod.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.jks.skygenmod.SkygenMod;
import com.jks.skygenmod.sounds.IHasPlaybackInfo;
import com.jks.skygenmod.sounds.SoundSkyfling;
import com.jks.skygenmod.util.Registries;
import com.jks.skygenmod.util.helpers.RegistryHelper;
import com.jks.skygenmod.util.helpers.SkyflingHelper;
import com.jks.skygenmod.world.gen.structures.WorldGenSkyblockStructure;

public class BlockSkyfling extends Block {
	public BlockSkyfling() {
		super(Material.ROCK);
		this.setBlockUnbreakable();
		RegistryHelper.registerBlock(this, "block_skyfling");
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		SkyflingHelper.doSkyfling(worldIn, playerIn);
        return true;
    }
}
