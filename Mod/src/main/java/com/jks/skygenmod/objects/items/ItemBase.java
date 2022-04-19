package com.jks.skygenmod.objects.items;

import com.jks.skygenmod.Main;
import com.jks.skygenmod.proxy.ClientProxy;
import com.jks.skygenmod.util.IHasModel;
import com.jks.skygenmod.util.init.ItemInit;

import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel{
	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		// setCreativeTab(getCreativeTab();
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
