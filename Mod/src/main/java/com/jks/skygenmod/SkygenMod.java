package com.jks.skygenmod;

import org.apache.logging.log4j.Logger;

import com.jks.skygenmod.proxy.IProxy;
import com.jks.skygenmod.proxy.modinterop.chunks.IBaseChunkGeneratorProxy;
import com.jks.skygenmod.proxy.modinterop.chunks.VanillaBaseChunkGeneratorProxy;
import com.jks.skygenmod.proxy.modinterop.worldtype.IBaseWorldTypeProxy;
import com.jks.skygenmod.proxy.modinterop.worldtype.VanillaBaseWorldTypeProxy;
import com.jks.skygenmod.util.Reference;
import com.jks.skygenmod.util.handlers.RegistryHandler;
import com.jks.skygenmod.world.gen.WorldGenHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class SkygenMod {
	@Instance
	public static SkygenMod instance;
	
	public static IBaseChunkGeneratorProxy baseChunkGeneratorProxy;
	public static IBaseWorldTypeProxy baseWorldTypeProxy;
	
	private static Logger logger;
	   
    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        logger = event.getModLog();
        if (Loader.isModLoaded("biomesoplenty")) {
        	baseChunkGeneratorProxy = 
        			Class
        			.forName("com.jks.skygenmod.proxy.modinterop.chunks.BOPBaseChunkGeneratorProxy")
        			.asSubclass(IBaseChunkGeneratorProxy.class)
        			.newInstance();
        	baseWorldTypeProxy = 
        			Class
        			.forName("com.jks.skygenmod.proxy.modinterop.worldtype.BOPBaseWorldTypeProxy")
        			.asSubclass(IBaseWorldTypeProxy.class)
        			.newInstance();
        }
        else {
        	baseChunkGeneratorProxy = new VanillaBaseChunkGeneratorProxy();
        	baseWorldTypeProxy = new VanillaBaseWorldTypeProxy();
        }
        
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
