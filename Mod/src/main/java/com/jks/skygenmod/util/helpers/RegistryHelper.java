package com.jks.skygenmod.util.helpers;

import com.jks.skygenmod.SkygenMod;
import com.jks.skygenmod.util.Registries;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.SoundEvent;

public class RegistryHelper {
	public static void registerBlock(Block block, String name) {
		registerBlock(block, name, CreativeTabs.BUILDING_BLOCKS);
	}
	
	public static void registerItem(Item item, String name, CreativeTabs tab) {
		item.setUnlocalizedName(name);
		item.setRegistryName(name);
		// setCreativeTab(getCreativeTab();
		
		Registries.ITEMS.add(item);
	}
	
	public static void registerBlock(Block block, String name, CreativeTabs tab) {
		block.setUnlocalizedName(name);
		block.setRegistryName(name);
		block.setCreativeTab(tab);
		
		Registries.BLOCKS.add(block);
		Registries.ITEMS.add(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}
	
	public static void registerSound(SoundEvent sound, String name) {
		sound.setRegistryName(name);
		
		Registries.SOUNDS.add(sound);
	}
	
	public static void registerBlockItemModel(Block block) {
		registerItemModel(Item.getItemFromBlock(block));
	}
	
	public static void registerItemModel(Item item) {
		SkygenMod.proxy.registerItemRenderer(item, 0, "inventory");
	}
}
