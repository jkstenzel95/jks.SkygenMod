package com.jks.skygenmod.world.gen.structures;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import com.jks.skygenmod.SkygenMod;
import com.jks.skygenmod.util.Reference;

import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenSkyblockStructure extends StructureBase {
	public static WorldGenSkyblockStructure instance = new WorldGenSkyblockStructure();
	
	@Override
	public int getOffsetX() {
		return -2;
	}

	@Override
	public int getOffsetY() {
		return -3;
	}

	@Override
	public int getOffsetZ() {
		return -5;
	}

	@Override
	public String getName() {
		return "skyblock";
	}
}