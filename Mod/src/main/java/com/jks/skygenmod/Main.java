package com.jks.skygenmod;

import org.apache.logging.log4j.Logger;

import com.jks.skygenmod.proxy.CommonProxy;
import com.jks.skygenmod.util.Reference;
import com.jks.skygenmod.util.RegistryHandler;
import com.jks.skygenmod.world.gen.WorldGenHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	@Instance
	public static Main instance;
	
	private static Logger logger;
	   
    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        MinecraftForge.TERRAIN_GEN_BUS.register(WorldGenHandler.class);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        RegistryHandler.postInitRegistries(event);
    }
    
    @EventHandler
    public static void serverInit(FMLServerStartingEvent event)
    {
    	RegistryHandler.serverRegistries(event);
    }
    
    public static Logger getLogger()
    {
    	return logger;
    }
}
