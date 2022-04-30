package com.jks.skygenmod.util.handlers;

import com.jks.skygenmod.commands.CommandSkygen;
import com.jks.skygenmod.util.Reference;
import com.jks.skygenmod.util.Registries;
import com.jks.skygenmod.util.helpers.RegistryHelper;
import com.jks.skygenmod.world.gen.WorldTypeSkygen;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(Registries.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(Registries.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onSoundRegister(RegistryEvent.Register<SoundEvent> event){
		event.getRegistry().registerAll(Registries.SOUNDS.toArray(new SoundEvent[0]));
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for (Item item : Registries.ITEMS) {
			RegistryHelper.registerItemModel(item);
		}

		for (Block block : Registries.BLOCKS) {
			RegistryHelper.registerBlockItemModel(block);
		}
	}

	public static void postInitRegistries(FMLPostInitializationEvent event) {
		WorldType SKYGEN = new WorldTypeSkygen();
	}

	public static void serverRegistries(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandSkygen());
	}
}
